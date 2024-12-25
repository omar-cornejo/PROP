# Directori controllers  

> Path absolut: `/FONTS/src/main/domain/controllers`  

## Descripció del Directori  
Aquest directori conté el codi dels controladors, responsables de gestionar les operacions entre les diferents entitats de la capa de domini.  

## Classes del Directori  

### **CtrlDomain**  
Aquesta classe actua com a punt central del controlador de domini. Implementa el patró *Singleton* per assegurar que només hi hagi una instància. Gestiona operacions clau com:  
- Autenticació d'usuaris.  
- Gestió de productes, incloent-hi la seva creació, modificació, eliminació i obtenció d'atributs.  
- Gestió de similituds entre productes, per exemple, afegint, eliminant o modificant valors de similitud.  
- Creació i gestió d'estanteries, incloent-hi l'organització de productes mitjançant algorismes d'optimització.  

### **CtrlProduct**  
Controlador encarregat de gestionar els productes d'un usuari. Ofereix funcionalitats com:  
- Afegir productes amb un ID únic al sistema.  
- Modificar o eliminar productes existents.  
- Obtenir informació detallada sobre un producte o obtenir la llista d'IDs de productes d'un usuari.  

### **CtrlShelf**  
Controlador encarregat de gestionar les estanteries dels usuaris. Les seves funcionalitats inclouen:  
- Crear i eliminar estanteries.  
- Afegir o eliminar productes d'una estanteria específica.  
- Obtenir la llista de productes en una estanteria i verificar si una estanteria existeix per a un usuari.  

### **CtrlUser**  
Controlador destinat a la gestió dels usuaris. Implementa el patró *Singleton* i ofereix les següents funcionalitats:  
- Registre de nous usuaris i autenticació d'usuaris existents.  
- Canvi del nom d'usuari o la contrasenya d'un usuari autenticat.  
- Gestió de la matriu de similituds per usuari, incloent-hi l'afegit o eliminació de productes i l'assignació de similituds entre productes.  
- Recuperació de detalls sobre l'usuari autenticat.  

## Relació entre les Classes  
- **CtrlDomain** és el controlador principal que delega les tasques específiques a **CtrlProduct**, **CtrlShelf** i **CtrlUser**.  
- **CtrlProduct** interactua amb els productes dels usuaris, mentre que **CtrlShelf** gestiona la seva organització en estanteries.  
- **CtrlUser** gestiona la informació d'usuaris i també manté la matriu de similituds associada a cada usuari.  
