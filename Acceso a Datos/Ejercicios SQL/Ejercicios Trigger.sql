/*1. Crea una restricción para ciclismo que evite que existan ciclistas 
de menos de 18 años.*/
ALTER TABLE ciclista
ADD CHECK(edad >= 18)

/*2. Crea una restricción que evite que existan etapas de menos de 10 km.*/
/*Si no pongo 9 no deja*/
ALTER TABLE etapa
ADD CHECK(km >= 9)

/*3. Crea una restricción que evite que exista un puerto sin etapa.*/
ALTER TABLE puerto
ADD CHECK(netapa IS NOT NULL)

/*4. Crea una restricción que evite que existan etapas que salgan y 
lleguen al mismo lugar.*/
ALTER TABLE etapa
ADD CHECK(salida<>llegada)

/*5. Crea una restricción que evite que se inserten más de 20 ciclistas 
por equipo.*/
CREATE OR REPLACE FUNCTION public.max20equip()
RETURNS trigger
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $BODY$
BEGIN
IF (SELECT COUNT(*) FROM ciclista WHERE nomeq=new.nomeq) > 19 THEN
	RAISE NOTICE 'No puede haber más de 20 en un equipo';
	RETURN NULL;
ELSE
	RETURN NEW;
END IF;
END;
$BODY$;


CREATE TRIGGER max_20_equip
	BEFORE INSERT
	ON public.ciclista
	FOR EACH ROW
	execute FUNCTION public.max20equip()

/*6. Crea una restricción que evite que se modifique el nombre de 
un ciclista. Trata de hacerlo sin permisos.*/
CREATE OR REPLACE FUNCTION public.negar_modif_nom_cicli()
RETURNS trigger
LANGUAGE 'plpgsql'
AS $BODY$
BEGIN
	IF (OLD.nombre <> NEW.nombre) 
	THEN
		RAISE NOTICE 'No está permitido la modificación del nombre'
		RETURN NULL;
	ELSE
		RETURN NEW;
	END IF;
END;
$BODY$;


CREATE OR REPLACE TRIGGER negar_modif_nom_ciclis
	BEFORE UPDATE of nombre
	ON public.ciclista
	FOR EACH ROW
	execute FUNCTION public.negar_modif_nom_cicli()

/*7. Crea una restricción que evite que se creen o eliminen equipos*/
CREATE OR REPLACE FUNCTION public.fc_negar_creac_elim_equipo()
RETURNS trigger
LANGUAGE 'plpgsql'
COST 100
VOLATILE NOT LEAKPROOF
AS $BODY$
BEGIN
	RAISE NOTICE 'No está permitido la creación o eliminación de equipos';
	RETURN NULL;
END;
$BODY$;


CREATE TRIGGER tr_negar_creac_elim_equipo
	BEFORE INSERT OR DELETE
	ON public.equipo
	FOR EACH ROW
	execute FUNCTION public.fc_negar_creac_elim_equipo()

/*8. Deshabilita la restricción que impide que se creen o eliminen nuevos 
equipos y crea una que sólo permita la EXISTENCIA de equipos con un mínimo 
de 3 ciclistas (para toda nueva operación sobre la bd, no revises si 
antiguamente se cumplía)*/
CREATE OR REPLACE FUNCTION public.fc_crear_equipo_min_3_cicli()
RETURNS trigger
LANGUAGE 'plpgsql'
AS $BODY$
BEGIN
	IF (TG_OP = 'INSERT') AND (SELECT COUNT(*) FROM ciclista cl WHERE cl.nomeq = NEW.nomeq) < 2 THEN
		RAISE NOTICE 'No está permitido la creación de un ciclista si su equipo tiene menos de 3 ciclistas (incluyendose)';
		RETURN NULL;
	END IF;
	IF (TG_OP = 'DELETE') AND (SELECT COUNT(*) FROM ciclista cl WHERE cl.nomeq = NEW.nomeq) < 4 THEN
		RAISE NOTICE 'No está permitido la eliminación de un ciclista si su equipo se queda con menos de 3 ciclistas';
		RETURN NULL;
	END IF;
	IF (TG_OP = 'UPDATE') THEN
		IF (SELECT COUNT(*) FROM ciclista cl WHERE cl.nomeq = OLD.nomeq) < 4 THEN
			RAISE NOTICE 'No está permitido la modificación del equipo de un ciclista si su antiguo equipo se queda con menos de 3 ciclistas';
			RETURN NULL;
		END IF;
		IF (SELECT COUNT(*) FROM ciclista cl WHERE cl.nomeq = NEW.nomeq) < 2 THEN
			RAISE NOTICE 'No está permitido la modificación del equipo de un ciclista si su nuevo equipo se queda con menos de 3 ciclistas';
			RETURN NULL;
		END IF;
	END IF;
	RETURN NEW;
END;
$BODY$;


CREATE TRIGGER tr_crear_equipo_min_3_cicli
	BEFORE INSERT OR DELETE OR UPDATE OF nomeq
	ON public.ciclista
	FOR EACH ROW
	execute FUNCTION public.fc_crear_equipo_min_3_cicli()

/*9. Crea una restricción que evite que se modifique la edad de un 
ciclista por menos de la que tiene.*/
CREATE OR REPLACE FUNCTION public.fc_negar_modif_menos_edad_cicli()
RETURNS trigger
LANGUAGE 'plpgsql'
AS $BODY$
BEGIN
	IF (OLD.edad > NEW.edad) 
	THEN
		RAISE NOTICE 'No está permitido la modificación de la edad a un valor menor'
		RETURN NULL;
	ELSE
		RETURN NEW;
	END IF;
END;
$BODY$;


CREATE OR REPLACE TRIGGER tr_negar_modif_menos_edad_cicli
	BEFORE UPDATE OF edad
	ON public.ciclista
	FOR EACH ROW
	execute FUNCTION public.fc_negar_modif_menos_edad_cicli()

/*10. a)Crea un campo en la tabla equipo que mantenga el número de ciclistas que 
tiene cada equipo (necesitaréis un campo nuevo nciclistas en equipo). 
*/
ALTER TABLE equipo
ADD nciclistas int
/*
b)Una vez hecho, rellénalo con los datos actuales. Pongo la solución del 
update al final del documento
*/
UPDATE equipo eq 
SET (nciclistas) = (
	SELECT count(*) FROM  ciclista cl
  	WHERE eq.nomeq=cl.nomeq
    GROUP BY nomeq
)
/*
c)Realiza el/los disparador(es) necesarios para mantener este campo con los 
valores adecuados.*/
CREATE OR REPLACE FUNCTION public.fc_actual_nciclis()
RETURNS trigger
LANGUAGE 'plpgsql'
AS $BODY$
BEGIN
	IF (TG_OP = 'INSERT') OR (TG_OP = 'UPDATE') THEN
		UPDATE equipo
		SET nciclistas = nciclistas + 1
		WHERE nomeq = NEW.nomeq;
	ELSE 
		UPDATE equipo
		SET nciclistas = nciclistas - 1
		WHERE nomeq = NEW.nomeq;
	END IF;
	IF(TG_OP = 'UPDATE') THEN
		UPDATE equipo
		SET OLD.nciclistas = nciclistas - 1
		WHERE nomeq = OLD.nomeq;
	END IF;
	RETURN NEW;
END;
$BODY$;


CREATE OR REPLACE TRIGGER tr_actual_nciclis
	AFTER INSERT OR DELETE OR UPDATE of nomeq 
	ON public.ciclista
	FOR EACH ROW
	execute FUNCTION public.fc_actual_nciclis()

/*11. Crea el/los disparador(es) necesarios para mantener un campo en la tabla 
grupo que mantenga el número de usuarios que participan. 
(Necesitaréis un campo nuevo nparticipantes en grupo)*/
CREATE OR REPLACE FUNCTION public.actualizar_num_usu_grupo()
RETURNS trigger
LANGUAGE 'plpgsql'
AS $BODY$
BEGIN
		UPDATE grupo SET nparticipantes = (SELECT COUNT(*) FROM usuariogrupo WHERE NEW.idgrupo = idgrupo);
		RETURN NEW;
END;
$BODY$;


CREATE OR REPLACE TRIGGER actualizar_num_usu_grupo
	AFTER INSERT OR UPDATE OF idgrupo
	ON public.usuariogrupo
	FOR EACH ROW
	execute FUNCTION public.actualizar_num_usu_grupo()

	
/*12. Logra a través de una función en pl/pgsql llamada anyos_ciclista: 
a) sumar un año a aquellos ciclistas que no hayan ganado ninguna etapa 
*/
CREATE OR REPLACE FUNCTION public.anyos_ciclista()
RETURNS trigger
LANGUAGE 'plpgsql'
AS $BODY$
BEGIN
		IF (SELECT COUNT(*) FROM etapa WHERE dorsal = NEW.dorsal) = 0 THEN
			UPDATE ciclista SET edad = edad + 1;
		ELSE
			RETURN NEW;
		END IF;
END;
$BODY$;
/*
b) restar uno a aquellos que hayan ganado más de una 
*/
CREATE OR REPLACE FUNCTION public.anyos_ciclista()
RETURNS trigger
LANGUAGE 'plpgsql'
AS $BODY$
BEGIN
		IF (SELECT COUNT(*) FROM etapa WHERE dorsal = NEW.dorsal) = 0 THEN
			UPDATE ciclista SET edad = edad + 1 WHERE dorsal = NEW.dorsal;
		ELSE
			UPDATE ciclista SET edad = edad - 1 WHERE dorsal = NEW.dorsal;
		END IF;
END;
$BODY$;
/*
c)sumar 1000 años a aquellos ciclistas que hayan llevado más que nadie
algún mallot (si alguien llevó 50 veces el rojo y otro llevó 20 veces el 
rojo y alguien llevó 70 veces el amarillo, sumo 1000 años sólo al ciclista 
del amarillo).
*/
CREATE OR REPLACE FUNCTION public.anyos_ciclista()
RETURNS trigger
LANGUAGE 'plpgsql'
AS $BODY$
BEGIN
		IF (SELECT COUNT(*) FROM etapa WHERE dorsal = NEW.dorsal) = 0 THEN
			UPDATE ciclista SET edad = edad + 1 WHERE dorsal = NEW.dorsal;
		ELSE
			UPDATE ciclista SET edad = edad - 1 WHERE dorsal = NEW.dorsal;
		END IF;
		IF (SELECT MAX())
END;
$BODY$;
/*
d) sumar 100 años al ciclista que más veces llevó cada mallot*/
CREATE OR REPLACE FUNCTION public.actualizar_num_usu_grupo()
RETURNS trigger
LANGUAGE 'plpgsql'
AS $BODY$
BEGIN
		UPDATE grupo SET nparticipantes = (SELECT COUNT(*) FROM usuariogrupo WHERE NEW.idgrupo = idgrupo);
		RETURN NEW;
END;
$BODY$;


CREATE OR REPLACE TRIGGER actualizar_num_usu_grupo
	AFTER INSERT OR UPDATE OF idgrupo
	ON public.usuariogrupo
	FOR EACH ROW
	execute FUNCTION public.actualizar_num_usu_grupo()


