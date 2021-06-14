# Superheros service API REST: challenge By mindata

## Índice

El proyecto ***superheros*** utiliza el patron de arquitectura MVC

<a name="instalacion"></a>
## Instalación

- se debe clonar el proyecto desde github : https://github.com/aguilarjuan/mindata.git
- desde el directorio donde se instalo el projecto debe ejecutar el comando : docker-compose up
- luego se creara un contenedor docker de la aplicacion que escuchara en la direccion: http://localhost:8080/
- tambien se creara un contenedor de Redis para utilizarla como memoria cache
- la mejor forma para probar la API rest es utilizando la herramienta Postman, usted puede utilizar directamente
- una casuística ya configurada solo debe importar el archivo .json que se encuentra en la ruta interna: ***src/test/resources/postman*** e exportarlo a la herramienta Postman

- [acceso a la base de la aplicacion] la aplicacion utiliza una base en memoria (H2) para persistir los datos  
  
- [acceso a la documentacion de la API RESt]  http://localhost:8080/swagger-ui.html#/
  
- [suguridad de la API REST] la aplicacion esta protegida mediante usuario/password para Autenticar tambien utiliza JSON WEB TOKEN el cual tiene una expiracion de 1 minuto, luego de eso se debera volver a generar el token

- para apagar el proyecto debe entrar a la raiz del projecto y ejecutar el comando: docker-compose down

<a name="Endpoint de autenticacion"></a>
## autenticacion

- utlizar el Endpoint: http://localhost:8080/api/v1/authentication/token?user=jucaguilar&password=user123
- se debe enviar como query param un usuario/password VALIDO, utilize los valores user = jucaguilar y password=user123 (los cuales tienen los permisis para consumir el servicio)



