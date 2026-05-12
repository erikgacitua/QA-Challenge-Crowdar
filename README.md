# QA Automation Framework - Challenge Crowdar

Este proyecto es un framework de automatización híbrido diseñado para validar capas de **API (REST)** y **UI (Web)** de forma robusta y escalable.

## 🛠️ Tecnologías y Arquitectura
- **Lenguaje:** Java 17
- **Core:** Selenium WebDriver & RestAssured
- **Test Runner:** TestNG
- **Diseño:** Page Object Model (POM)
- **Gestión:** Maven Wrapper

---

## 📝 Casos de Prueba Automatizados

A continuación se detallan los escenarios cubiertos en este desafío:

### 1. Caso UI: Login Exitoso y Validación de Productos
- **Escenario:** Verificar que un usuario estándar pueda iniciar sesión y visualizar la lista de productos.
- **Pasos:** 1. Navegar a SauceDemo.
    2. Ingresar credenciales válidas.
    3. Validar redirección al inventario.
    4. Verificar que existan productos disponibles.

### 2. Caso UI: Flujo de Error en Login (Evidencia de Falla)
- **Escenario:** Validar la respuesta del sistema ante credenciales incorrectas y captura de evidencia.
- **Pasos:**
    1. Ingresar usuario inexistente.
    2. Intentar iniciar sesión.
    3. Verificar mensaje de error.
       *(Este test está diseñado para fallar/validar error y generar captura en la carpeta /screenshots).*

### 3. Caso API: Consumo de Endpoint de Usuarios (GET)
- **Escenario:** Validar que el servicio de ReqRes retorna la lista de usuarios correctamente.
- **Pasos:**
    1. Realizar petición GET a `/api/users`.
    2. Validar código de estado HTTP 200.
    3. Validar que el cuerpo de la respuesta (JSON) no sea nulo y contenga datos.

---

## 🚀 Instrucciones de Ejecución

El proyecto incluye **Maven Wrapper**, por lo que no requiere instalación previa de Maven.

### Preparación (Solo Mac/Linux)
Otorgue permisos de ejecución:
`chmod +x mvnw`

### Ejecución de Pruebas
**Suite Completa (Chrome):**
`./mvnw clean test -Dbrowser=chrome`

**Ejecución en Safari:**
`./mvnw test -Dbrowser=safari`

**Solo API:** `./mvnw test "-Dtest=*Api*"`

**Solo Web:** `./mvnw test "-Dtest=*Sauce*" -Dbrowser=chrome`

---

## 📸 Gestión de Evidencias
- Las capturas de pantalla se generan automáticamente en la carpeta raíz `/screenshots`.
- Se organizan en subcarpetas `/PASSED` y `/FAILED` según el resultado del test.

---

## 📝 Notas de Entrega
- El repositorio es **público** para facilitar su revisión.
- Se recomienda usar `./mvnw clean` antes de cada ejecución.