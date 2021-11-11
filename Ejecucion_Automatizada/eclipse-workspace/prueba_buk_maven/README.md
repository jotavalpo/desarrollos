El proyecto esta hecho en Eclipse IDE for Java Developers Version: 2020-03 (4.15.0)

El arquetipo usado es Maven con Java y TestNG. Sus dependencias versiones se encuentran en archivo pom.xml

La version usada de Java es la 7 (1.7)

Esta bajo modelo page object, donde el folder src/test/java contiene las clases con los casos de prueba (los que se ejecutan) y por otro lado src/main/java contiene las clases con la navegacion y flujo. Existen otros folder donde se agrupan algunas funciones genericas

Contiene extended report para reportes en html y pdf, para acceder a ellos luego de ejecutar una prueba, se debe ir a la ruta C:\desarrollos\Ejecucion_Automatizada\Evidencias y buscar el proyecto en cuestion

Pasos para ejecutar las pruebas:

Descargar proyecto en la raiz del disco (unidad C:)
Ir a la carpeta C:\desarrollos\Ejecucion_Automatizada\Prerrequisitos y ejecutar los archivos Hub.bat, luego NodeChrome.bat y NodeFirefox.bat respectivamente
Abrir Eclipse e importar proyecto descargado
Esperar a que se descarguen dependencias necesarias y no existan errores
Cuando proyecto este cargado OK, se puede ejecutar un caso de manera individual usando TestNG (click derecho en el nombre del caso de prueba, click en Run As->TestNG Test) o tambien de usando Maven (click derecho en el nombre del proyecto, click en Run As->Maven...cualquier opcion) o ejecutar maven mediante CLI

Gracias!
