# Drivers del Package `drivers`

## Descripció del Package
El package `drivers` conté programes de prova interactius per validar la funcionalitat de les classes principals del sistema. Aquests drivers proporcionen menús interactius que permeten realitzar proves pràctiques per explorar el comportament de les classes implementades.  

---

## Classes del Package

### **`CtrlDomainDriver`**
- **Descripció:**  
  Driver principal per provar la funcionalitat de la classe `CtrlDomain`. Proporciona funcionalitats relacionades amb la gestió d'usuaris, productes, estanteries i algorismes d'organització.  
- **Funcionalitats Principals:**  
  - **Gestió d'Usuaris:** Registre, inici de sessió, canvi de nom d'usuari i contrasenya.  
  - **Gestió de Productes:** Afegir, eliminar i modificar productes associats a usuaris.  
  - **Gestió d'Estanteries:** Crear, gestionar i organitzar estanteries.  
  - **Gestió de Similituds:** Configurar i consultar similituds entre productes.  
  - **Execució d'Algorismes:** Organitzar productes mitjançant algorismes com *Two Approximation*.  

---

### **`ProductDriver`**
- **Descripció:**  
  Prova de la funcionalitat de la classe `Product`. Permet interactuar amb objectes que representen productes amb ID, nom i preu.  
- **Funcionalitats Principals:**  
  - Crear productes amb ID, nom i preu.  
  - Consultar atributs com ID, nom i preu.  
  - Actualitzar el preu del producte.  
  - Mostrar la representació en text del producte.  

---

### **`ShelfDriver`**
- **Descripció:**  
  Driver per gestionar i provar la funcionalitat de la classe `Shelf`. Una estanteria conté una llista de productes identificats pels seus IDs.  
- **Funcionalitats Principals:**  
  - Crear i gestionar estanteries.  
  - Afegir i eliminar productes d'una estanteria.  
  - Consultar el nombre de productes i buidar completament una estanteria.  

---

### **`SimilarityMatrixDriver`**
- **Descripció:**  
  Prova de la funcionalitat de la classe `SimilarityMatrix`. Aquesta classe permet gestionar la similitud entre productes.  
- **Funcionalitats Principals:**  
  - Crear una matriu de similitud.  
  - Afegir productes a la matriu.  
  - Establir, consultar i eliminar similituds entre productes.  
  - Visualitzar la matriu completa de similituds.  

---

### **`UserDriver`**
- **Descripció:**  
  Driver per validar la funcionalitat de la classe `User`. Prova operacions com l'autenticació, la gestió de noms d'usuari i contrasenyes.  
- **Funcionalitats Principals:**  
  - Crear un usuari amb nom complet, nom d'usuari i contrasenya.  
  - Autenticar un usuari amb credencials.  
  - Actualitzar noms d'usuari i contrasenyes.  
  - Consultar atributs com el nom complet, nom d'usuari i contrasenya.  
  - Mostrar els detalls de l'usuari amb una representació en text.  

---

## Com utilitzar els drivers
1. Executa el programa principal de cada classe per inicialitzar un menú interactiu.  
2. Segueix les instruccions proporcionades per explorar les funcionalitats implementades.  
3. En cas d'errors (com entrades no vàlides), es mostraran missatges explicatius per facilitar el diagnòstic.  

**Nota:** Aquests drivers estan pensats exclusivament per a proves i no han de ser utilitzats en entorns de producció.  
