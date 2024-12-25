# Directori data structures  

> Path absolut: `/FONTS/src/main/domain/classes/algorithm/datastructures`  

## Descripció del Directori  
Aquest directori inclou estructures de dades especialitzades que són utilitzades pels algorismes d'organització de productes.  

## Classes del Directori  

### **Edge**  
Representa una aresta en un graf ponderat, modelant la relació entre dos productes amb un cost associat (similitud).  
- **Atributs:**  
  - `cost`: Pes o cost de la aresta, que indica la similitud entre dos productes.  
  - `product1`: Identificador del primer producte.  
  - `product2`: Identificador del segon producte.  
- **Funcionalitats clau:**  
  - Comparar arestes segons el seu cost (ordenació ascendent).  
  - Obtenir informació detallada dels productes connectats i el cost de la connexió.  

### **UnionFind**  
Implementa una estructura de dades Union-Find (o Disjoint Set Union) per gestionar subconjunts disjunts d'elements.  
- **Atributs:**  
  - `nodes`: Array que representa els nodes. Els valors negatius indiquen la mida del conjunt.  
- **Funcionalitats clau:**  
  - **Find:** Troba la representació del conjunt al qual pertany un element utilitzant compresió de camins (*path compression*).  
  - **Union:** Uneix dos conjunts diferents amb optimització per mida (*union by size*).  
- **Aplicació:**  
  - Es fa servir en algorismes de graf com el Kruskal per trobar arbres generadors mínims (MST).  

