# 🚀 Reto 3 – Automatización de APIs con RestAssured
# Diplomado en Automatización Inteligente de Pruebas de Software

Este repositorio contiene la solución al **Reto 3 del curso**, cuyo objetivo es crear un proyecto de **automatización de pruebas de APIs** utilizando **RestAssured**, **JUnit** y **Maven**, aplicando la estructura aprendida en clase y usando buenas prácticas básicas.

---

## 🧪 Objetivo del proyecto

- Automatizar pruebas sobre una API REST pública
- Validar respuestas, campos y reglas de negocio básicas
- Ejecutar las pruebas de forma simple usando Maven
- Mantener una estructura clara y reutilizable

API utilizada para las pruebas:  
👉 **https://jsonplaceholder.typicode.com**

---

## 📁 Estructura del proyecto

```text
api-automation/
├── pom.xml
└── src
    └── test
        └── java
            └── tests
                └──  BaseTest.java
                    ├── ApiTest.java
                    └── Reto3Test.java
                        ├── testGetListaUsuarios
                        ├── testGetUsuarioPorId
                        ├── testValidacionCamposUsuario
                        ├── testValidacionUsuarioInexistente
                        └── testValidacionGeneral


