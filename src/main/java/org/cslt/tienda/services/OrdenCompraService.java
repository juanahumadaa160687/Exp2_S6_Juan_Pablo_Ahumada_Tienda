package org.cslt.tienda.services;

import org.cslt.tienda.models.ordencompra.OrdenCompra;

import java.util.List;

public interface OrdenCompraService {

    List<OrdenCompra> getAllOrdenCompra();
    OrdenCompra getOrdenCompraById(Long id);
    OrdenCompra newOrdenCompra(OrdenCompra ordenCompra);
    void deleteOrdenCompraById(Long id);
}
