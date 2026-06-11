
# SistemaCiberCafé

**Sistema de gestión para cibercafé** desarrollado en Java con arquitectura MVC + DAO,
interfaz gráfica Swing y persistencia en MySQL mediante JDBC.


## Características

- Registro, edición y eliminación de clientes
- Gestión del estado de computadoras (disponible / ocupada / en mantenimiento)
- Inicio y cierre de sesiones con cálculo automático de costo por tiempo
- Venta de productos con control de stock en tiempo real
- Panel de reportes: ingresos del día, sesiones activas y productos más vendidos
- Validaciones de formularios en todas las ventanas
- Arquitectura MVC + DAO con separación clara de capas


## Tecnologías

Java -
MySQL -
Apache Maven



## Instalación

1. Clonar el repositorio:

   git clone https://github.com/gonzaleznoelia158/SistemaCiberCafe.git

2. Importar el proyecto en NetBeans o en VSCode como proyecto Maven existente.

3. Crear la base de datos en MySQL:

   mysql -u root -P 3307 -p < database/schema.sql

4. Verificar la conexión en `src/main/java/util/ConexionDB.java`:

   String URL = "jdbc:mysql://localhost:3307/sistemaciber_db";

5. Ejecutar el proyecto desde NetBeans o VSCode.


## Estructura MVC
```
SistemaCiberCafe/
├── src/main/java/
│   ├── model/        # Clases modelo
│   ├── dao/          # Acceso a datos con JDBC
│   ├── view/         # Ventanas y paneles Swing
│   └── util/         # ConexionDB y utilitarias
├── database/
│   └── schema.sql    # Script de creación de la BD
└── pom.xml           # Dependencias Maven
```
## Autores

- [@gonzaleznoelia158](https://www.github.com/gonzaleznoelia158)
- [@Fabrizio-art](https://www.github.com/Fabrizio-art)
- [@UzuluDD](https://www.github.com/UzuluDD)
- [@f3rn1ndo112](https://www.github.com/f3rn1ndo112)
- [@roy luis garro](https://www.github.com/royluis1)

