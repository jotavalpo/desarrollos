El proyecto esta hecho en Eclipse IDE for Java Developers Version: 2020-03 (4.15.0).

El arquetipo usado es Maven con Java y TestNG. Sus dependencias y versiones se encuentran en el archivo pom.xml.

La version usada de Java es la 7 (1.7).

Esta bajo modelo page object, donde el folder src/test/java contiene las clases con los casos de prueba (los que se ejecutan) y por otro lado src/main/java contiene las clases con la navegacion, localizadores y flujo. Existen otros folder donde se agrupan algunas funciones genericas.

Pasos para ejecutar las pruebas:

- Usando git console o cmd debe posicionarse en la unidad C (se debe tener instalado git en el equipo local).
- Ejecutar el comando "git clone https://github.com/jotavalpo/desarrollos.git" para descargar el proyecto al equipo local. 

- Luego, abrir Eclipse, hacer click en File->Import->General->Existing Projects into Workspace->Next->Browse..., buscar la carpeta "C:\desarrollos\Ejecucion_Automatizada\eclipse-workspace\prueba_buk_maven", seleccionar el proyecto y dar click en Finish.

- El proyecto deberia visualizarse en Eclipse. En ocasiones, queda con errores debido a la falta de dependencias de Maven. Para solventar esto, se debe forzar a la descarga y/o actualizacion de dependencias, esto se hace haciendo click derecho en el nombre del proyecto, luego
ir a Maven->Update Project..., clickear la opcion Force Update of Snapshots/Releases y luego click en OK. Esperar a que se actualicen dependencias. Con esto los errores desapareceran.

- Luego, ir a la carpeta C:\desarrollos\Ejecucion_Automatizada\Prerrequisitos y ejecutar los archivos Hub.bat, luego NodeChrome.bat y NodeFirefox.bat respectivamente.

- Cuando proyecto este cargado OK, se puede ejecutar un caso de manera individual usando TestNG (click derecho en el nombre del caso de prueba, click en Run As->TestNG Test) o tambien de usando Maven (click derecho en el nombre del proyecto, click en Run As->Maven test, etc.) o ejecutar maven mediante CLI.

- Para ver los reportes de la ejecucion, ir a la carpeta "C:\desarrollos\Ejecucion_Automatizada\Evidencias\Buk\1" y acceder a cualquiera de las dos carpetas (HTML o PDF).

NOTA: Para que los reportes posean las capturas de pantalla con las evidencias correctas, debe estar activa la ventana que este ejecutando el navegador, es decir, que la ejecucion del browser este visible. Si bien, la prueba se puede ejecutar en segundo plano (gracias al Selenium Remote Webdriver) Extended Report necesita el navegador visible para sacar pantallazos.

IMPORTANTE: Para que el proyecto se ejecute correctamente, se debe respetar la estructura C:\desarrollos\Ejecucion_Automatizada... 
