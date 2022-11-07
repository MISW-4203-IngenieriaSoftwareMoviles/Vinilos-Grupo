# Vinilos-Grupo12


## Requerimientos de Software:

- Android Studio Dolphin | 2021.3.1 Patch 1
Build #AI-213.7172.25.2113.9123335, built on September 29, 2022
Runtime version: 11.0.13+0-b1751.21-8125866 amd64
VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
Windows 10 10.0
GC: G1 Young Generation, G1 Old Generation
Memory: 1280M
Cores: 8
Registry:
    external.system.auto.import.disabled=true
    ide.text.editor.with.preview.show.floating.toolbar=false

Non-Bundled Plugins:
    wu.seal.tool.jsontokotlin (3.7.4)

- Java Jdk 11 o superior.

Repositorio del Back:
https://misw-4203-vinilos-grupo12.herokuapp.com/

## Pasos para ejecutar el proyecto:


**1.** Descargar el repositorio con el siguiente comando:


git clone https://github.com/MISW-4203-IngenieriaSoftwareMoviles/Vinilos-Grupo12.git

**2.** Ingresar al directorio del proyecto:
cd Vinilos-Grupo12

**3.** Cambiar en Git a la Rama Main o Release (Ambas se encuentran actualizadas)

**4.** Abrir Android Studio.

**5.** Dentro de Android Studio  navegar, en el menu principal: File -> Open : Se debe seleccionar el directorio donde descargo el repositorio.

**6.** Pasos para ejecutar el proyecto, en el menu principal: Run ->  Run App

**7.** En caso de no ejecutar, debe de "Recompilar el proyecto": Build -> Rebuild Project

**NOTA:*
- En caso de con compilar y ejecutar el proyecto, se debe sincronizar Gradle en el Menú: File -> Sync Project With Gradle Files

## Pasos para ejecutar las pruebas E2E del proyecto:

**1.** Para ejecutar las pruebas E2E de forma individual, se debe ingresar en el paquete java -> com.example.vinilos (androidTest) -> ui: 

**2.** Ejecutar cada una de las siguientes seleccionando cada prueba con click derecho:

    Run 'H001_Test_Album_Principal'
    Run 'HU01_Test_ListadoAlbumes'
    Run 'HU02_Test_DetalleAlbum'
