package org.cslt.tienda.services;


import org.cslt.tienda.models.Compra;

import java.util.List;

public interface CompraService {

    List<Compra> getAllCompras();
    Compra findCompraById(Long id);
    Compra newCompra(Compra compra);
    Compra updateCompra(Long id, Compra compra);
    void deleteCompraById(Long id);

}
