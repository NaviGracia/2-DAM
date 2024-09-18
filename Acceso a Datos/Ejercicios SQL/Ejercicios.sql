/*1. Obtener el código, el tipo, el color y el premio de todos los maillots que hay.*/
SELECT * FROM maillot;

/*2. Obtener el dorsal y el nombre de los ciclistas cuya edad sea menor o igual que 25 años.*/
SELECT dorsal, nombre FROM ciclista
WHERE edad <= 25;

/*3. Obtener el nombre y la altura de todos los puertos de categoría ‘E’ (Especial).*/
SELECT nompuerto, altura FROM puerto
WHERE categoria = 'E';

/*4. Obtener el valor del atributo netapa de aquellas etapas con salida y llegada en la misma ciudad.*/
SELECT netapa FROM etapa
WHERE salida = llegada;

/*5. ¿Cuántos ciclistas hay?*/
SELECT COUNT(dorsal) FROM ciclista

/*6. ¿Cuántos ciclistas hay con edad superior a 25 años?.*/
SELECT COUNT(dorsal) FROM ciclista
WHERE edad > 25;

/*7. ¿Cuántos equipos hay?.*/
SELECT COUNT(nomeq) FROM equipo;

/*8. Obtener la media de edad de los ciclistas.*/
SELECT AVG(edad) FROM ciclista

/*9. Obtener la altura mínima y máxima de los puertos de montaña.*/
SELECT MIN(altura), MAX(altura) FROM puerto

/*10. Obtener el nombre y la categoría de los puertos ganados por ciclistas del equipo ‘Banesto’.*/
SELECT puerto.nompuerto, puerto.categoria FROM puerto inner join ciclista using(dorsal)
WHERE ciclista.nomeq = 'Banesto';

/*11. Obtener el nombre del cada puerto indicando el número (netapa) y los kilómetros de la etapa en 
la que se encuentra el puerto. */
SELECT puerto.nompuerto, etapa.netapa, etapa.km FROM puerto inner join etapa using(netapa);

/*12. Obtener el nombre y el director de los equipos a los que pertenezca algún ciclista mayor de 33 
años*/
SELECT eq.nomeq, eq.director FROM equipo eq inner join ciclista cl using(nomeq)
WHERE cl.edad > 33;

/*13. Obtener el nombre de los ciclistas con el color de cada maillot que hayan llevado. */
SELECT cl.nombre, maillot.color FROM ciclista cl inner join llevar using(dorsal) inner join maillot using(codigo);

/*14. Obtener pares de nombre de ciclista y número de etapa tal que ese ciclista haya ganado esa 
etapa habiendo llevado el maillot de color ‘Amarillo’ al menos una vez.*/
SELECT DISTINCT cl.nombre, et.netapa FROM etapa et inner join ciclista cl using (dorsal) inner join llevar using(dorsal) inner join maillot using(codigo)
WHERE maillot.color = 'Amarillo';

/*15. Obtener el valor del atributo netapa de las etapas que no comienzan en la misma ciudad en que 
acabó la anterior etapa*/
WITH EtapasPrevias AS (
    SELECT 
        netapa,
        llegada,
		salida,
        LAG(llegada, 1) OVER (ORDER BY netapa) AS llegada_anterior
    FROM etapa
)
SELECT netapa
FROM EtapasPrevias
WHERE salida != llegada_anterior;
----------------------------------------------
SELECT e2.netapa FROM etapa e1, etapa e2
WHERE e1.llegada <> e2.salida AND e1.netapa + 1 = e2.netapa;

/*16. Obtener el valor del atributo netapa y la ciudad de salida de aquellas etapas que no tengan 
puertos de montaña. */
SELECT netapa, salida FROM etapa
WHERE NOT EXISTS(SELECT puerto.netapa FROM puerto WHERE puerto.altura > 1000 AND etapa.netapa = puerto.netapa)
ORDER BY netapa; 

/*17. Obtener la edad media de los ciclistas que han ganado alguna etapa. */
SELECT AVG(edad) FROM ciclista
WHERE EXISTS(SELECT dorsal FROM etapa WHERE etapa.dorsal = ciclista.dorsal);

/*18. Selecciona el nombre de los puertos con una altura superior a la 
altura media de todos los puertos*/
SELECT nompuerto FROM puerto
WHERE altura > (SELECT AVG(altura) FROM puerto);

/*19. Obtener el nombre de la ciudad de salida y de llegada de las etapas 
donde estén los puertos con mayor pendiente. */
SELECT etapa.salida, etapa.llegada FROM etapa inner join puerto using(netapa)
WHERE puerto.pendiente = (SELECT MAX(pendiente) FROM puerto);

/*20. Obtener el dorsal y el nombre de los ciclistas que han ganado los puertos de mayor altura.*/
SELECT cl.dorsal, cl.nombre FROM ciclista cl inner join puerto using(dorsal)
WHERE puerto.altura = (SELECT MAX(altura) FROM puerto);

/*21. Obtener el nombre del ciclista más joven. */
SELECT nombre FROM ciclista 
WHERE edad = (SELECT MIN(edad) FROM ciclista);

/*22. Obtener el nombre del ciclista más joven que ha ganado al menos una etapa.*/
WITH CiclistasEtapaGanada AS (
    SELECT cl.nombre, cl.edad, COUNT(et.dorsal) FROM ciclista cl inner join etapa et using (dorsal)
	GROUP BY cl.nombre, cl.edad
	HAVING COUNT(et.dorsal) >= 1
)
SELECT nombre
FROM CiclistasEtapaGanada
WHERE edad = (SELECT MIN(edad) FROM CiclistasEtapaGanada);

/*23. Obtener el nombre de los ciclistas que han ganado más de un puerto. */
SELECT cl.nombre FROM ciclista cl
WHERE (SELECT COUNT(*) AS cont FROM puerto WHERE cl.dorsal = puerto.dorsal) > 1;

/*24. Obtener el valor del atributo netapa de aquellas etapas tales que 
todos los puertos que están en ellas tienen más de 700 metros de altura.*/
SELECT netapa FROM puerto
GROUP BY netapa
HAVING EVERY(altura > 700)
ORDER BY netapa;
-----------------------------------------------------------------------------
SELECT e1.netapa FROM etapa e1
WHERE NOT EXISTS (SELECT * FROM puerto p1 WHERE p1.altura <= 700 AND e1.netapa = p1.netapa)
ORDER BY e1.netapa;

/*25. Obtener el nombre y el director de los equipos tales que todos sus 
ciclistas son mayores de 25 años*/
SELECT DISTINCT eq.nomeq, eq.director FROM equipo eq inner join ciclista cl using(nomeq)
GROUP BY eq.nomeq, eq.director
HAVING EVERY(cl.edad > 25)
ORDER BY eq.nomeq, eq.director;

/*26. Obtener el dorsal y el nombre de los ciclistas tales que todas las 
etapas que han ganado tienen más de 170 km (es decir que sólo han ganado 
etapas de más de 170 km). */
SELECT DISTINCT cl.dorsal, cl.nombre FROM ciclista cl inner join etapa et using(dorsal)
GROUP BY cl.dorsal, cl.nombre
HAVING EVERY(et.km > 170)
ORDER BY cl.dorsal, cl.nombre;

/*27. Obtener el nombre de los ciclistas que han ganado todos los puertos de 
una etapa y además han ganado esa misma etapa*/
SELECT cl.nombre FROM ciclista cl
WHERE EXISTS(SELECT et.dorsal FROM etapa et inner join puerto pt using(netapa)
			WHERE et.dorsal = cl.dorsal AND pt.dorsal = et.dorsal)

/*28. Obtener el nombre de los equipos tales que todos sus corredores han 
llevado algún maillot o han ganado algún puerto.*/
SELECT eq.nomeq FROM equipo eq
WHERE EXISTS(SELECT cl.dorsal FROM ciclista cl inner join puerto pt using(dorsal)
				WHERE cl.nomeq = eq.nomeq) 
	OR EXISTS(SELECT cl.nomeq FROM ciclista cl inner join llevar le using(dorsal)
				WHERE cl.nomeq = eq.nomeq) 

/*29. Obtener el código y el color de aquellos maillots que sólo han sido 
llevados por ciclistas de un mismo equipo*/
SELECT DISTINCT ml.codigo, ml.color 
FROM maillot ml inner join llevar l using(codigo) inner join ciclista c using(dorsal)
WHERE NOT EXISTS(SELECT * FROM llevar l2, ciclista c2
WHERE c2.dorsal=l2.dorsal AND c2.nomeq<>c.nomeq AND l2.codigo = l.codigo)


/*30. Obtener el nombre de aquellos equipos tales que sus ciclistas sólo hayan ganado puertos de 1ª 
categoría.*/


/*31. Obtener el valor del atributo netapa de aquellas etapas que tienen 
puertos de montaña indicando cuántos tiene*/
SELECT et.netapa, COUNT(pt.nompuerto) FROM etapa et inner join puerto pt using(netapa)
WHERE pt.altura >= 500
GROUP BY et.netapa
ORDER BY et.netapa;

/*32. Obtener el nombre de los equipos que tengan ciclistas indicando 
cuántos tiene cada uno*/
SELECT eq.nomeq, COUNT(cl.dorsal) FROM equipo eq inner join ciclista cl using(nomeq)
GROUP BY eq.nomeq
ORDER BY eq.nomeq;

/*33. Obtener el nombre de todos los equipos indicando cuántos ciclistas 
tiene cada uno*/
SELECT eq.nomeq, COUNT(cl.dorsal) FROM equipo eq left join ciclista cl using(nomeq)
GROUP BY eq.nomeq
ORDER BY eq.nomeq;

/*34. Obtener el director y el nombre de los equipos que tengan más de 3 
ciclistas y cuya edad media sea inferior o igual a 30 años. */
SELECT eq.director, eq.nomeq FROM equipo eq inner join ciclista cl using(nomeq)
GROUP BY eq.director, eq.nomeq
HAVING COUNT(cl.dorsal) > 3 AND AVG(cl.edad) <= 30
ORDER BY eq.director
;

/*35. Obtener el nombre de los ciclistas que pertenezcan a un equipo que tenga 
más de cinco corredores y que hayan ganado alguna etapa indicando cuántas 
etapas ha ganado*/
SELECT cl.nombre, COUNT(et.dorsal) 
FROM equipo eq inner join ciclista cl using(nomeq) inner join etapa et using(dorsal)
GROUP BY cl.nombre
HAVING (SELECT COUNT(cl2.dorsal) FROM ciclista cl2 WHERE cl2.nomeq = 
		(SELECT cl3.nomeq FROM ciclista cl3 WHERE cl3.nombre = cl.nombre)) > 5
ORDER BY cl.nombre;

/*36. Obtener el nombre de los equipos y la edad media de sus ciclistas de 
aquellos equipos que tengan la media de edad máxima de todos los equipos*/
SELECT nombre, media FROM (
	SELECT eq.nomeq AS nombre, AVG(edad) AS media 
	FROM ciclista cl inner join equipo eq using(nomeq)
	GROUP BY eq.nomeq)
GROUP BY nombre
HAVING MAX(media) = media
ORDER BY nombre;

/*37. Obtener el director de los equipos cuyos ciclistas han llevado más días maillots de cualquier 
tipo. Nota: cada tupla de la relación Llevar indica que un ciclista ha llevado un maillot un día */


/**/


/**/


/**/


/**/

