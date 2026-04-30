package org.cslt.tienda.services;


import org.cslt.tienda.models.pago.Pago;

import java.util.List;

public interface PagoService {

    List<Pago> getAllPagos();
    Pago getPagoById(Long id);
    Pago newPago(Pago pago);
    Pago updatePago(Long id, Pago pago);
    void deletePagoById(Long id);
    Pago findByCompra(Long compra_id);

}
