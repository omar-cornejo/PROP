# Directori test  

> Path absolut: `/FONTS/src/test`  

## Descripció del Directori  
Aquest directori conté els tests de totes les classes programades al projecte, incloent-hi tests per als components de domini, algorismes, i les estructures de dades utilitzades.  

## Classes del Directori  

### **TestMaster**  
- Agrupa i executa tots els tests del projecte de forma conjunta.  
- Conté referències a tots els tests implementats per facilitar la seva execució global.  

#### **TestProduct**  
- Verifica la creació, modificació i consulta d'atributs de la classe `Product`.  
- Casos provats:  
  - Constructor amb valors vàlids i invàlids.  
  - Modificació del preu del producte.  
  - Representació del producte com a text.  

#### **TestShelf**  
- Prova la funcionalitat de la classe `Shelf`, incloent-hi l'afegit, eliminació i consulta de productes.  
- Casos provats:  
  - Creació d'estanteries amb productes inicials.  
  - Gestió d'elements duplicats i no existents.  
  - Esborrar productes i buidar la estanteria.  

#### **TestSimilarityMatrix**  
- Verifica les operacions sobre la matriu de similituds, com afegir productes, establir valors de similitud, i eliminar productes.  
- Casos provats:  
  - Establiment i consulta de valors vàlids i invàlids.  
  - Gestió d'IDs inexistents o duplicats.  

#### **TestUser**  
- Prova la funcionalitat de la classe `User`, incloent-hi l'autenticació i la modificació de credencials.  
- Casos provats:  
  - Autenticació amb credencials correctes i incorrectes.  
  - Modificació del nom d'usuari i contrasenya.  
  - Constructor amb dades vàlides i invàlides.  
