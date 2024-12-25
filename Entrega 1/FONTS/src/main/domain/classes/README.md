> Path absolut: `/FONTS/src/main/domain/classes`  

## Descripció del Directori  
En aquest directori es troben totes les classes principals que defineixen els elements bàsics del sistema, com ara productes, usuaris, estanteries i la matriu de similituds.  

## Classes del Directori  

### **Product**  
Representa un producte amb un ID únic, un nom i un preu.  
- **Atributs:**  
  - `id`: Identificador únic del producte.  
  - `name`: Nom del producte.  
  - `price`: Preu del producte.  
- **Funcionalitats clau:**  
  - Consultar i modificar el preu del producte.  
  - Consultar l'ID i el nom del producte.  
  - Representar el producte com a text per a propòsits de depuració.  

### **Shelf**  
Representa una estanteria que conté productes identificats pels seus IDs.  
- **Atributs:**  
  - `name`: Nom únic de la estanteria.  
  - `products`: Llista d'IDs dels productes que conté la estanteria.  
- **Funcionalitats clau:**  
  - Afegir, eliminar i consultar productes a la estanteria.  
  - Obtenir el nom i la mida de la estanteria.  
  - Representar la estanteria com a text.  

### **SimilarityMatrix**  
Representa una matriu de similituds entre productes.  
- **Atributs:**  
  - `idToIndex`: Mapa que associa IDs de producte a índexs dins de la matriu.  
  - `indexToId`: Mapa que associa índexs de la matriu als IDs de producte.  
  - `idToSimilarities`: Mapa que emmagatzema les similituds de cada producte amb altres productes.  
- **Funcionalitats clau:**  
  - Afegir i eliminar productes de la matriu.  
  - Establir i consultar la similitud entre dos productes.  
  - Obtenir la matriu de similitud com una estructura de dades bidimensional.  

### **User**  
Representa un usuari amb un nom complet, un nom d'usuari únic i una contrasenya.  
- **Atributs:**  
  - `name`: Nom complet de l'usuari.  
  - `username`: Nom d'usuari únic.  
  - `password`: Contrasenya de l'usuari.  
- **Funcionalitats clau:**  
  - Autenticar l'usuari amb el nom d'usuari i la contrasenya.  
  - Canviar el nom d'usuari i la contrasenya.  
  - Obtenir informació sobre l'usuari.  