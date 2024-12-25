# PROP Grup 31.3  
Projecte de Programació, Grup 31, Subgrup 31.3  
**Professor:** Carles Arnal Castelló  

## Membres del Grup  

- Omar Cornejo  
- Hug Capdevila  
- Levon Asatryan  
- Nazarena Ponce  

---

## Estructura del Directori  

### DOCS  
Aquest directori conté tota la documentació del projecte:  

- **Casos d'ús**: Inclou una imatge que representa el diagrama de casos d'ús.  
- **Diagrama estàtic complet** del model conceptual de dades.  
- **Javadoc**: Carpeta que inclou la documentació generada automàticament amb Javadoc per a les classes implementades. Se ejecuta con: javadoc -d "Entrega 1/DOCS/JAVADOC" -sourcepath "Entrega 1/FONTS/src" -subpackages main

### EXE  
Aquest directori conté els fitxers executables (*.class*) de totes les classes necessàries per provar les funcionalitats principals implementades.  

### FONTS  
Aquest directori inclou el codi font de les classes de domini associades a les funcionalitats implementades fins ara.  

- També inclou els tests JUnit.  
- Tots els fitxers estan organitzats en subdirectoris que segueixen l'estructura de *packages*, per tal d'assegurar que el codi sigui recompilable directament.  

### lib  
Directori que conté les llibreries externes utilitzades per als tests JUnit.  

---

## Provar el Programa  

Per executar el programa, cal situar-se al directori `/FONTS`, on es troba l'script `run_drivers_and_test.sh`. Aquest script permet compilar i executar els tests i els drivers implementats fins al moment.  

---

## Dates de Lliurament  

- **Primera entrega:** dilluns, 18 de novembre de 2024.  


## Juegos de Prueba
Este directorio contiene un archivo con los juegos de prueba diseñados para verificar las funcionalidades implementadas en el sistema.
- **juegos.txt**: Archivo que incluye casos de prueba detallados para las distintas funcionalidades del sistema. Cada caso está descrito con su entrada, proceso y salida esperada.  