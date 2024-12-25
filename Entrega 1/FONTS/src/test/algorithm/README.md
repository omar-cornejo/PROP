# Directori algorithms  

> Path absolut: `/FONTS/src/test/algorithm`  

## Descripció del Directori  
Aquest directori conté els tests per a tots els algorismes i estructures de dades utilitzades per a l'organització i gestió de productes en estanteries.  

## Classes del Directori  

### **TestBruteForceAlgorithm**  
- Verifica la funcionalitat de l'algorisme de força bruta per organitzar productes en estanteries òptimes.  
- Casos provats:  
  - Càlcul del cost de les combinacions.  
  - Validació de paràmetres d'entrada.  

### **TestTwoAproxAlgorithm**  
- Prova l'algorisme basat en aproximació per assegurar la seva precisió i comportament amb diferents conjunts de dades.  
- Casos provats:  
  - Resultats amb diferents matrius de similitud.  
  - Validació de paràmetres d'entrada.  

### **TestShelfOrganizer**  
- Valida la implementació genèrica de l'interfície `ShelfOrganizer` i la seva extensibilitat.  
- Casos provats:  
  - Validació de matrius de similitud i llistes de productes.  
  - Test sobre implementacions personalitzades de l'interfície.  

### **TestEdge**  
- Prova la representació i ordenació de les arestes d'un graf ponderat.  
- Casos provats:  
  - Comparació d'arestes per cost.  
  - Validació dels valors associats als nodes connectats.  

### **TestUnionFind**  
- Verifica les operacions bàsiques de la classe `UnionFind`, com la compressió de camins i la unió per mida.  
- Casos provats:  
  - Operació de cerca (*find*).  
  - Operació d'unió (*union*).  