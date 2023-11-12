package dev.guillermosg.msmovements.infrastructure.adapters.output.persistence;

import dev.guillermosg.msmovements.application.ports.output.CuentaOutputPort;
import dev.guillermosg.msmovements.domain.model.Cuenta;
import dev.guillermosg.msmovements.domain.model.CuentaRequest;
import dev.guillermosg.msmovements.domain.model.UpdateStatusAccountRequest;
import dev.guillermosg.msmovements.infrastructure.adapters.output.persistence.entity.CuentaEntity;
import dev.guillermosg.msmovements.infrastructure.adapters.output.persistence.mapper.CuentaPersistenceMapper;
import dev.guillermosg.msmovements.infrastructure.adapters.output.persistence.repository.CuentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CuentaPersistenceAdapter implements CuentaOutputPort {

    private final CuentaRepository cuentaRepository;
    private final CuentaPersistenceMapper cuentaPersistenceMapper;

    /**
     * @param cuenta
     */
    @Override
    public void save(CuentaRequest cuenta) {
        CuentaEntity entity = new CuentaEntity();
        entity.setNumeroCuenta(cuenta.getNumeroCuenta());
        entity.setTipoCuenta(cuenta.getTipoCuenta());
        entity.setSaldoInicial(cuenta.getSaldoInicial());
        entity.setSaldoActual(cuenta.getSaldoInicial());
        entity.setEstado(true);
        entity.setClienteId(cuenta.getClienteId());

        cuentaRepository.save(entity);

    }

    /**
     * @param cuentaId
     * @return CuentaEntity
     */
    @Override
    public Optional<CuentaEntity> findById(Integer cuentaId) {
        return cuentaRepository.findById(cuentaId.longValue());
    }

    /**
     * @param clienteId
     * @return
     */
    @Override
    public List<Cuenta> findByClienteId(Integer clienteId) {
        return cuentaRepository.findByClienteId(clienteId).stream().map(cuentaPersistenceMapper::toCuenta).collect(Collectors.toList());
    }

    /**
     * @param cuentaId
     * @param tipoMovimiento
     * @param valor
     */
    @Override
    public void actualizarSaldo(Integer cuentaId, String tipoMovimiento, BigDecimal valor) {
        Optional<CuentaEntity> cuenta = cuentaRepository.findById(cuentaId.longValue());
        if (cuenta.isPresent()) {
            CuentaEntity cuentaEntity = cuenta.get();
            if (tipoMovimiento.equals("RETIRO")) {
                cuentaEntity.setSaldoActual(cuentaEntity.getSaldoActual().subtract(valor.abs()));
            } else {
                cuentaEntity.setSaldoActual(cuentaEntity.getSaldoActual().add(valor));
            }
            cuentaRepository.save(cuentaEntity);
        }
    }

    /**
     * @return List<Cuenta>
     */
    @Override
    public List<Cuenta> findAll() {
        return cuentaRepository.findAll().stream().map(cuentaPersistenceMapper::toCuenta).collect(Collectors.toList());
    }

    /**
     * @param request
     */
    @Override
    public void updateStatusAccount(UpdateStatusAccountRequest request) {
        Optional<CuentaEntity> cuenta = cuentaRepository.findById(request.getCuentaId().longValue());
        if (cuenta.isPresent()) {
            CuentaEntity cuentaEntity = cuenta.get();
            cuentaEntity.setEstado(request.getEstado());
            cuentaRepository.save(cuentaEntity);
        }
    }

}
