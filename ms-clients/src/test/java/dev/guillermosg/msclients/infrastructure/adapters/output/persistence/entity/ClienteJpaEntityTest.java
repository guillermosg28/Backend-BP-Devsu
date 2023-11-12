package dev.guillermosg.msclients.infrastructure.adapters.output.persistence.entity;

import dev.guillermosg.msclients.infrastructure.adapters.output.persistence.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class ClienteJpaEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    ClienteRepository repository;

    @Test
    void should_find_no_clients_if_repository_is_empty(){
        Iterable<ClienteEntity> clients = repository.findAll();
        assertThat(clients).isEmpty();
    }

    @Test
    void should_create_a_patient(){
        ClienteEntity c1 = new ClienteEntity();
        c1.setClienteId(1L);
        c1.setNombre("Guillermo");
        c1.setEdad(30);
        c1.setGenero("MASCULINO");
        c1.setIdentificacion("123456789");
        c1.setDireccion("Calle 123");
        c1.setContrasena("123456");
        c1.setEstado(true);

        ClienteEntity cliente = repository.save(c1);

        assertThat(cliente)
                .hasFieldOrPropertyWithValue("clienteId", 1L)
                .hasFieldOrPropertyWithValue("contrasena", "123456")
                .hasFieldOrPropertyWithValue("estado", true)
                .hasFieldOrPropertyWithValue("nombre", "Guillermo")
                .hasFieldOrPropertyWithValue("edad", 30)
                .hasFieldOrPropertyWithValue("genero", "MASCULINO")
                .hasFieldOrPropertyWithValue("identificacion", "123456789")
                .hasFieldOrPropertyWithValue("direccion", "Calle 123");
    }

    @Test
    void should_find_all_clients(){
        ClienteEntity c1 = new ClienteEntity();
        ClienteEntity c2 = new ClienteEntity();

        entityManager.persist(c1);
        entityManager.persist(c2);

        Iterable clients = repository.findAll();

        assertThat(clients).hasSize(2).contains(c1, c2);

    }


    @Test
    void should_delete_all_clients(){

        ClienteEntity c1 = new ClienteEntity();
        ClienteEntity c2 = new ClienteEntity();

        entityManager.persist(c1);
        entityManager.persist(c2);

        repository.deleteAll();
        assertThat(repository.findAll()).isEmpty();
    }
}
