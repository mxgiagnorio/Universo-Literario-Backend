# Java Final Project

## Descripción

Este proyecto es una aplicación de gestión de usuarios desarrollada en Java utilizando Spark Framework para el backend y MySQL para la base de datos. La aplicación permite registrar, autenticar y gestionar usuarios a través de una API REST.

## Prerrequisitos

- [Java JDK 11+](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [MySQL](https://www.mysql.com/downloads/)
- [Visual Studio Code](https://code.visualstudio.com/)
- [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)

## Instalación y Configuración

1. **Clona el repositorio:**

   ```bash
   git clone https://github.com/tuusuario/java-final-project.git
   cd java-final-project
   ```

2. **Configura la base de datos:**

   - Crea una base de datos en MySQL.
   - Actualiza `config.properties` con tu configuración de base de datos:
     ```properties
     MYSQLDATABASE=your_database_name_here
     MYSQLHOST=your_database_host_here
     MYSQLPASSWORD=your_password_here
     MYSQLPORT=your_port_here
     MYSQLUSER=your_user_here
     MYSQL_DATABASE=your_database_name_here
     MYSQL_PRIVATE_URL=mysql://your_user_here:your_password_here@your_private_host_here:your_private_port_here/your_database_name_here
     MYSQL_ROOT_PASSWORD=your_root_password_here
     MYSQL_URL=mysql://your_user_here:your_password_here@your_host_here:your_port_here/your_database_name_here
     SERVER_PORT=your_server_port_here
     ```

3. **Instala las dependencias:**
   - Abre el proyecto en Visual Studio Code.
   - Ve al panel de `JAVA PROJECTS` y asegúrate de que todas las dependencias estén instaladas.

## Uso

1. **Ejecuta la aplicación:**

   - Abre `Main.java` y ejecuta el método `main`.

2. **Endpoints disponibles:**
   - `POST /register`: Registro de nuevos usuarios.
   - `POST /login`: Autenticación de usuarios.
   - `GET /all-users`: Obtener todos los usuarios

## Estructura del Proyecto

- `src/main`: Contiene los archivos fuente del proyecto.
  - `controller`: Controladores para manejar las solicitudes HTTP.
  - `dao`: Clases de acceso a datos.
  - `model`: Clases de modelo de datos.
  - `service`: Lógica de negocio y servicios.
  - `util`: Utilidades y helpers.
- `lib`: Carpeta para mantener las dependencias.
- `config.properties`: Archivo de configuración de base de datos.

## Gestión de Dependencias

Usamos Spark Framework para el backend y MySQL para la base de datos. Puedes gestionar tus dependencias a través del panel `JAVA PROJECTS` en Visual Studio Code. Más detalles [aquí](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## Contribución

1. Haz un fork del proyecto.
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz commit (`git commit -am 'Agrega nueva funcionalidad'`).
4. Sube tu rama (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.
