# Directori EXE

> Path absolut: /EXE

## Descripció del directori
Aquest directori conté tots els codis compilats del sistema

## Elements del directori

- *Directori build*:
  Conté els fitxers generats després de la compilació del projecte, com els fitxers .class.

- *Directori out:*
  Hi ha l'executable de totes les classes del model (exceptions, funcions i tipus) i els tests en format .class.


### Creació i Actualització del Fitxer JAR

Per crear o actualitzar el fitxer programa.jar amb un manifest específic, utilitzeu:

jar cfm programa.jar MANIFEST.MF -C build .


### Execució del Programa

Per executar el programa, utilitzeu el següent comandament:

java -jar programa.jar