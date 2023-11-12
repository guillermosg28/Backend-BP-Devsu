Feature: Pruebas de la API DEVSU

  Background:
    * def API_URL = 'http://localhost:5051/api/v1'

  Scenario: Crear un nuevo cliente 02
    Given url API_URL + '/clientes'
    And request { personaId: 1, nombre: 'Guillermo Santisteban', genero: 'MASCULINO', edad: 30, identificacion: '123456789', direccion: 'Calle 123', telefono: '123456789', contrasena: '123456' }
    When method post
    Then status 200

  Scenario: Crear un nuevo cliente 01
    Given url API_URL + '/clientes'
    And request { personaId: 2, nombre: 'Gisselle Montenegro', genero: 'FEMENINO', edad: 30, identificacion: '335433', direccion: 'Calle 123', telefono: '123456789', contrasena: '123456' }
    When method post
    Then status 200

  Scenario: Obtener información del cliente
    Given url API_URL + '/clientes/1'
    When method get
    Then status 200

  Scenario: Actualizar información del cliente
    Given url API_URL + '/clientes/1'
    And request { nombre: 'Guillermo Santisteban', genero: 'MASCULINO', edad: 30, identificacion: '123456789', direccion: 'Calle 123', telefono: '123456789', contrasena: '123456', estado: true }
    When method put
    Then status 200

  Scenario: Actualizar estado del cliente
    Given url API_URL + '/clientes/1'
    And request { estado: false }
    When method patch
    Then status 200

  Scenario: Eliminar cliente
    Given url API_URL + '/clientes/2'
    When method delete
    Then status 200

  Scenario: Crear una nueva cuenta de ahorros
    Given url API_URL + '/cuentas'
    And request { clienteId: 1, numeroCuenta: '453463', tipoCuenta: 'AHORROS', saldoInicial: 100.00 }
    When method post
    Then status 200

  Scenario: Crear una nueva cuenta corriente
    Given url API_URL + '/cuentas'
    And request { clienteId: 1, numeroCuenta: '353963', tipoCuenta: 'CORRIENTE', saldoInicial: 100.00 }
    When method post
    Then status 200


  Scenario: Actualizar estado de la cuenta no existente
    Given url API_URL + '/cuentas/100'
    And request { estado: false }
    When method patch
    Then status 400

  Scenario: Crear una nueva transacción
    Given url API_URL + '/movimientos'
    And request { cuentaId: 1, fecha: '2023-11-09', valor: 100 }
    When method post
    Then status 200

  Scenario: Crear una nueva transacción con fondos insuficientes
    Given url API_URL + '/movimientos'
    And request { cuentaId: 1, fecha: '2023-11-09', valor: -5000 }
    When method post
    Then status 400

  Scenario: Generar estado de cuenta
    Given url API_URL + '/reportes'
    And params { fechaInicio: '2023-11-08', fechaFin: '2023-11-09', clienteId: 1 }
    When method get
    Then status 200
