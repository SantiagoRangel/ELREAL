Instrucciones para ejecución del programa:

En el documento ServiciosIteracion2 están todos los URL para poder acceder a todos los servicios ofrecidos por la aplicación,
en este documento también se encuentra un ejemplo de los jsons que hay que usar en estos servicios. Además, hay un archivo
ServiciosIteracion2.postman_collection en el cual se prueban todos los jsons y URL'S del documento previamente mencionado.
Para probar los escenarios de prueba mencionados en las instrucciones, están los archivos EscenarioPruebasIntegridadConFK, 
EscenarioPruebasRestriccionesChequeo y EscenarioPruebasUnicidadTuplas que dicen cómo se deben probar y con cuáles JSONS. Estos
tres archivos tambien vienen con sus respectivas pruebas de postman.

NOTA: Cabe recordar que hay que correr el archivo .sql antes de realizar cada una de las 4 pruebas json o si se van a realizar
manualmente.



Validación usuario:

Hay algunos servicios que sólo pueden ser accedidos por ciertos usuarios, los cuales son clientes, representantes(representan un restaurante) 
y administradores. Para validar esto, creamos la tabla ENLINEA en la que se encuentra el "usuario actualmente en linea" (solo puede haber uno). Lo que se debe
hacer si se quiere que un "administrador" esté en linea, se debe ejecutar el servicio de INGRESO con un json de un administrador previamente inscrito. 
Posteriormente, todos los servicios que requieran un administrador se van a poder ejecutar. En cualquier momento se puede volver a 
ejecutar este servicio con un json de un cliente o un representante si se necesita que se cambie de "usuario actualmente en linea". 
En el documento de ServiciosIteracion2 está la URL de este servicio de ingreso y los jsons a los respectivos usuarios. Los archivos de postman ya
contemplan y aplican este cambio de usuario cuando se necesite un usuario específico en las pruebas.

NOTA: Cabe recordar que hay que correr el archivo .sql antes de realizar los ingresos a cualquiera de los tipos de cliente.

NOTA: Si el usuario se equivoca al ingresar el json de un usuario o si este no está registrado, se borra de la tabla la persona que estuviera enlinea,
si es que había una, hasta que la persona ingrese correctamente el json de algun usuario registrado.



Preferencias Cliente:

Para manejar las preferencias de los clientes, asumimos que pueden haber 3 tipos de preferencias. De comida, de precio y de zona. En nuestra aplicación,
se pueden hacer post a estas preferencias, delete o update. En el archivo ServiciosIteracion2 se explica cuales URL'S y JSONS se deben usar. Las preferencias 
de comida son importante ya que es con estas que hacemos un order by especial en las consultas que se necesita un ordenamiento de acuerdo al cliente que accede 
a la información