# 🎓 Plasea 3.0 - Sistema de Registro de Reportes Académicos

Proyecto para la gestión de reportes de rendimiento y asistencias académicas en la universidad. Desarrollado con **Spring Boot**, **Thymeleaf**, **JPA/Hibernate** y **MySQL**.

---

## 🛠️ Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

* **Java 17** o superior.
* **MySQL** (puedes usar el servidor que viene con **XAMPP** o una instalación independiente de MySQL Server).
* Tu IDE preferido (IntelliJ IDEA, VS Code, Eclipse o NetBeans).

---

## 🗄️ Configuración de la Base de Datos

1. Abre tu gestor de base de datos (phpMyAdmin, Workbench, o la terminal de MySQL).
2. Crea una base de datos llamada `universidad2`:
   ```sql
   CREATE DATABASE universidad2;
   ```
3. Importa el archivo de base de datos `Plasea_dataBase.sql` que se encuentra en la raíz del proyecto.

4. Configura tus credenciales en el archivo `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/universidad2
   spring.datasource.username=root
   spring.datasource.password=TU_CONTRASEÑA_DE_MYSQL
   ```

---

## 🚀 Cómo Iniciar el Proyecto

### Desde la Terminal / Consola
Entra a la carpeta del proyecto `Plasea-3.0` y ejecuta:

* **En Windows:**
  ```powershell
  .\mvnw.cmd spring-boot:run
  ```
* **En Mac o Linux:**
  ```bash
  ./mvnw spring-boot:run
  ```

### Desde un IDE
1. Abre la carpeta `Plasea-3.0` en tu IDE.
2. Deja que Maven descargue e instale las dependencias.
3. Busca la clase principal en `src/main/java/edu/plasea/parcial/Plasea1Application.java` y dale a **Run / Ejecutar**.

---

## 🌐 Rutas Principales del Sistema

Una vez que el servidor inicie en la consola, abre tu navegador e ingresa a:

* 🏠 **Inicio:** [http://localhost:8082/inicio](http://localhost:8082/inicio)
* 📝 **Formulario de Reportes:** [http://localhost:8082/formulario-reportes](http://localhost:8082/formulario-reportes)
* ⚙️ **Panel de Administración:** [http://localhost:8082/admin/panel](http://localhost:8082/admin/panel)

---

## 📌 Guía de Uso del Formulario de Reportes

> ⚠️ **IMPORTANTE SOBRE LA CÉDULA:**
> El primer campo llamado **"Cédula"** en el formulario de reportes corresponde a la **cédula del PROFESOR**, no a la del estudiante.

**Flujo correcto para guardar un reporte:**
1. Digita la cédula de un docente (ejemplos de prueba en la base de datos: `23205067`, `88238995`, `88238998`, `88239001`).
2. Presiona el botón **Buscar**. Esto cargará las carreras asociadas a ese docente.
3. Selecciona la **Carrera** ➡️ **Asignatura** ➡️ **Curso**.
4. En la lista desplegable de **Alumno**, el sistema filtrará automáticamente a los estudiantes inscritos en ese curso para seleccionarlo y asignarle la nota o el reporte.
