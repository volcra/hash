#Hash
=====

El proposito de #Hash (#) es combinar las herramientas de desarrollo Web en un solo lugar. #Hash será desarrollo para la JVM con lenguajes dinámicos y no dinámicos como Groovy y JavaScript o Scala y Java respectivamente. El objetivo es unificar el numero de herramientas necesarias para desarrollar proyectos Web enfocado al manejo de recursos estáticos. Se pretende incluir compiladores de código como CoffeeScript a JavaScript o Less a CSS, por mencionar algunos. Así como minificadores de código como Yahoo Compressor o Google Closure.

\#Hash funcionará de dos maneras. Como plugin que complementará el ciclo de vida de herramientas como Maven, Ant o Gradle. O como línea de comandos de manera independiente.

Compiladores y comandos serán distribuidos usando en el repositrio de Maven. El repositorio de Maven ha demostrado ser uno de los principales puntos de distribución de librerias Java.

Distribución
============

Línea de Comandos
-----------------

\#Hash será distribuido como linea de comandos, la distribución será zip o tar. Su instalación debe ser simple y similar a lo que otras herramientas en Java actualmente necesitan.

Esta tipo de distribución solo contendrá la herramienta principal. Compiladores y herramientas adicionales serán instaladas manualmente a necesidad del desarrollador.

### Instalación

#### Prerequisitos

1. Java 7

#### Pasos

1. Bajar el zip o tar en el repositorio de distribución.
2. Descomprimir zip o tar en el directorio deseado para instalación.
3. Agregar HASH_HOME con el directorio de instalación.
4. Agregar HASH_HOME a la variable de ambiente PATH.
5. Verificar instalación.

    Windows

        shell$> # -version
        #Hash version 1.0
        JVM: 1.7.0_10 (Oracle Corporation 23.6-b04)
        OS: Windows 7 6.1 amd64

    Unix

        shell$> #hash -version
        #Hash version 1.0
        JVM: 1.7.0_10 (Oracle Corporation 23.6-b04)
        OS: Linux GNU/Linux

6. Instalar algunos de los compiladores. i.e. CoffeeScript compiler

    Windows

        shell$> # install coffee

    Unix

        shell$> #hash install coffee

7. Verficar instalación

        shell$> #coffee -version

Plugin
------

\#Hash podrá ser integrado con Maven, Gradle o Ant. En Maven y Gradle como plugins y en Ant como Ant Tasks.

Roadmap
=======

Version 0.1
-----------

* Compilador de CoffeeScript, línea de comando
* Compilador de Less, línea de comando

Version 0.2
-----------

* CoffeeScript, Plugin de Maven
* Less, Plugin de Maven