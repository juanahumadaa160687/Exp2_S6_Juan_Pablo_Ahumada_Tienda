package org.cslt.tienda.services;

import org.cslt.tienda.models.OrdenCompra;
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
    public OrdenCompra updateOrdenCompra(Long id, OrdenCompra ordenCompra) {
        for(OrdenCompra compra : getAllOrdenCompra()){
            if(compra.getId().equals(id)){
                compra.setId(id);
                compra.setCompra(ordenCompra.getCompra());
                compra.setFecha_orden(ordenCompra.getFecha_orden());
                compra.setCliente(ordenCompra.getCliente());
                compra.setNro_orden(ordenCompra.getNro_orden());
                return ordenCompraRepository.save(compra);
            }
        }
        return null;
    }

    @Override
    public void deleteOrdenCompraById(Long id) {
        ordenCompraRepository.deleteById(id);
    }

}
