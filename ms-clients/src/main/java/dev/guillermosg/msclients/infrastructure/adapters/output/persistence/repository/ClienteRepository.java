package dev.guillermosg.msclients.infrastructure.adapters.output.persistence.repository;


import dev.guillermosg.msclients.infrastructure.adapters.output.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Cliente repository.
 */

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    /**
     * Find by cliente id cliente entity.
     *
     * @param clienteId the cliente id
     * @return the cliente entity
     */
    ClienteEntity findByClienteId(Long clienteId);
}
