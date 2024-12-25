# Directori FONTS  
> Path absolut: `/FONTS`  

## Descripció del Directori  
Aquest directori conté tot el codi del projecte organitzat per *packages*.  

## Elements del Directori  

- **Directori `src`:**  
  Inclou tots els fitxers de codi font del sistema, organitzats a partir de *packages*.  

- **Directori `build`:**  
  Conté els fitxers generats després de la compilació del projecte, com els fitxers `.class`.  

## Execució dels Drivers i Tests  

Per executar els drivers i tests del sistema, utilitzeu l'script `run_drivers_and_test.sh` amb les següents opcions: ./run_drivers_and_test.sh --help  
 

### Veure l'ajuda i totes les opcions disponibles:  
```bash
./run_drivers_and_test.sh --help
Usage:
  ./run_drivers_and_test.sh [option]

Options:
  -d, --driver <DriverName>  Run a specific driver
  -t, --test                 Run all tests using TestMaster
  -l, --list                 List available drivers
  -h, --help                 Show this help message

Examples:
  ./run_drivers_and_test.sh --driver CtrlDomainDriver
  ./run_drivers_and_test.sh --test
  ./run_drivers_and_test.sh --list
omar@omar-ASUS-TUF-Gaming-F15-FX506