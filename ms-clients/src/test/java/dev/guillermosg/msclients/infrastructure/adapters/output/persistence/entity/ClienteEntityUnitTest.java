package dev.guillermosg.msclients.infrastructure.adapters.output.persistence.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.Assert.assertEquals;

/**
 * ClienteEntityUnitTest
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClienteEntityUnitTest {

    @Autowired
    private TestEntityManager entityManager;

    private ClienteEntity clienteEntity;

    @BeforeEach
    void setup() {
        clienteEntity = createAndPersistCliente(1L, "Guillermo", "123456", true, "MASCULINO", 30, "123456789",
                "Calle 123", "123456789");
    }

    @Test
    void testClientEntity() {
        assertEquals(1L, clienteEntity.getClienteId().longValue());
        assertEquals("Guillermo", clienteEntity.getNombre());
        assertEquals("123456", clienteEntity.getContrasena());
        assertEquals(true, clienteEntity.isEstado());
        assertEquals("MASCULINO", clienteEntity.getGenero());
        assertEquals(30, clienteEntity.getEdad());
        assertEquals("123456789", clienteEntity.getIdentificacion());
        assertEquals("Calle 123", clienteEntity.getDireccion());
        assertEquals("123456789", clienteEntity.getTelefono());

    }

    private ClienteEntity createAndPersistCliente(Long clienteId, String nombre, String contrasena, boolean estado,
            String genero, int edad, String identificacion, String direccion, String telefono) {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setClienteId(clienteId);
        cliente.setNombre(nombre);
        cliente.setContrasena(contrasena);
        cliente.setEstado(estado);
        cliente.setGenero(genero);
        cliente.setEdad(edad);
        cliente.setIdentificacion(identificacion);
        cliente.setDireccion(direccion);
        cliente.setTelefono(telefono);
        return entityManager.persistAndFlush(cliente);
    }

}
