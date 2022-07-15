# TFG-RichardAlbanFernandez
Repositorio a modo de backup para mi TFG del Grado en Ingeniería Informática de la UVa

## Despliegue local
Para desplegar la aplicación de manera local se debe tener instalado Docker.

Una vez se tenga el software necesario, lo primero es clonar el repositorio, para ello, se debe ejecutar la siguiente instrucción desde una terminal de línea de comandos:

`git clone https://github.com/Richard-11/TFG-RichardAlbanFernandez.git`

A continuación, se debe ejecutar el siguiente comando y esperar a que termine:

`docker compose --profile local up -d`

¡Listo! El proyecto estará desplegado localmente. 
* Para acceder a la web de manera local utiliza la siguiente url: http://localhost:4200. 
* Para acceder al bot de Telegram, utiliza el siguiente enlace: https://t.me/TutoriasInfUVaBot

A continuación, se muestra la instrucción necesaria para detener el despliegue local:

`docker compose --profile local down --rmi all`

Con este comando se detendrán los contenedores y se eliminarán tanto los contenedores como las imagenes creadas. 
