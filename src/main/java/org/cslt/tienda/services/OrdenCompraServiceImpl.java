package org.cslt.tienda.services;

import org.cslt.tienda.models.ordencompra.OrdenCompra;
import org.cslt.tienda.repositories.OrdenCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenCompraServiceImpl implements OrdenCompraService {

    @Autowired
    OrdenCompraRepository ordenCompraRepository;

    @Override
    public List<OrdenCompra> getAllOrdenCompra() {

        return ordenCompraRepository.findAll();
    }

    @Override
    public OrdenCompra getOrdenCompraById(Long id) {

        return ordenCompraRepository.findById(id).orElse(null);
    }

    @Override
    public OrdenCompra newOrdenCompra(OrdenCompra ordenCompra) {

        return ordenCompraRepository.save(ordenCompra);
    }

    @Override
    public void deleteOrdenCompraById(Long id) {

        ordenCompraRepository.deleteById(id);
    }

}
