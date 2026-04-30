package org.cslt.tienda.services;

import org.cslt.tienda.models.pago.Pago;
import org.cslt.tienda.repositories.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired
    PagoRepository pagoRepository;

    @Override
    public List<Pago> getAllPagos() {
        return pagoRepository.findAll();
    }

    @Override
    public Pago getPagoById(Long id) {
        return pagoRepository.findById(id).orElse(null);
    }

    @Override
    public Pago newPago(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public Pago updatePago(Long id, Pago pago) {
        Pago pagoEntity = pagoRepository.findById(id).orElse(null);

        if(pagoEntity != null){
            pagoEntity.setTotal_pago(pago.getTotal_pago());
            pagoEntity.setFecha_pago(pago.getFecha_pago());
            pagoEntity.setCompra(pago.getCompra());

            pagoRepository.save(pagoEntity);

            return pagoEntity;
        }
        return null;
    }

    @Override
    public void deletePagoById(Long id) {
        pagoRepository.deleteById(id);
    }

    @Override
    public Pago findByCompra(Long compra_id) {
        return pagoRepository.findByCompra(compra_id);
    }

}
