/*1. Crea una restricción para cancion que evite títulos de menos de 2 caracteres*/
ALTER TABLE cancion ADD CHECK (titulo LIKE '__%')

/*2. Crea una restricción que evite que se inserte un artista sin grupo.*/
BEGIN;
	INSERT INTO artista VALUES('1', 'Yo');
	INSERT INTO pertence VALUES('1', '2','Guitarrista');
COMMIT;

DELETE FROM pertence WHERE dni = '1'
SELECT * FROM CLUB
SELECT * FROM grupo

ERROR:  Key (cod)=(1) is not present in table "grupo".insert or update on table "pertence" violates foreign key constraint "fk_cod_grupo" 

ERROR:  insert or update on table "pertence" violates foreign key constraint "fk_cod_grupo"
SQL state: 23503
Detail: Key (cod)=(1) is not present in table "grupo".


/*3. Evita que un club tenga más de un grupo*/
check_1club_1grupo

/*4. Todos los clubes deben de tener algún grupo.*/
YA HECHO


/*3. Evita que un club tenga más de un grupo*/


/*3. Evita que un club tenga más de un grupo*/


/*3. Evita que un club tenga más de un grupo*/




