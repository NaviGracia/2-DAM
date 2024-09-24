/*Crea una restricción para ciclismo que evite que existan ciclistas 
de menos de 18 años.*/
ALTER TABLE ciclista
ADD CHECK(edad >= 18)

/*Crea una restricción que evite que existan etapas de menos de 10 km.*/
/*Si no pongo 9 no deja*/
ALTER TABLE etapa
ADD CHECK(km >= 9)

/*Crea una restricción que evite que exista un puerto sin etapa.*/
ALTER TABLE puerto
ADD CHECK(netapa IS NOT NULL)

/*Crea una restricción que evite que existan etapas que salgan y 
lleguen al mismo lugar.*/
ALTER TABLE etapa
ADD CHECK(salida<>llegada)

/*Crea una restricción que evite que se inserten más de 20 ciclistas 
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


CREATE TRIGGER



/**/


/**/


