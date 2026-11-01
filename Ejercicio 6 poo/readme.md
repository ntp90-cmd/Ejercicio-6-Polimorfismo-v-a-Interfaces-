## Obed tot-26971

# Gestor de integraciones

Proyecto del curso CC2008 — Programación Orientada a Objetos de la Universidad del Valle de Guatemala.

Ejercicio 6: Polimorfismo vía interfaces, Temario C.  
Semestre II, 2026.


## Descripción

El programa simula un sistema que administra conexiones con servicios externos de una plataforma de comercio electrónico: pasarelas de pago, proveedores logísticos y certificadores de facturas electrónicas.

Permite consultar las integraciones desde un menú de consola y ejecutar operaciones según las capacidades de cada servicio.

## Funcionalidades

- Carga inicial de 10 integraciones.
- Listado de integraciones activas.
- Búsqueda por identificador.
- Ordenamiento por latencia promedio, de menor a mayor.
- Simulación de cobros y reintegros.
- Generación de guías de envío y documentos XML de ejemplo.
- Consulta del estado de conexión.
- Búsqueda por nombre y endpoint.
- Validación de opciones y montos ingresados.

Si se solicita un reintegro a un proveedor logístico, el programa ignora la operación y permite continuar utilizando el menú. 