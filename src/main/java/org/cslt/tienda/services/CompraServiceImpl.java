package org.cslt.tienda.services;

import org.cslt.tienda.models.Compra;
import org.cslt.tienda.repositories.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraServiceImpl  implements CompraService {

    @Autowired
    CompraRepository compraRepository;

    @Override
    public List<Compra> getAllCompras() {
        return compraRepository.findAll();
    }

    @Override
    public Compra findCompraById(Long id) {
        return compraRepository.findById(id).orElse(null);
    }

    @Override
    public Compra newCompra(Compra compra) {
        return compraRepository.save(compra);
    }

    @Override
    public Compra updateCompra(Long id, Compra compra) {
        for(Compra existingCompra : compraRepository.findAll()){
            if(existingCompra.getId_compra().equals(id)){
                existingCompra.setNro_compra(compra.getNro_compra());
                existingCompra.setProducto(compra.getProducto());
                return compraRepository.save(existingCompra);
            }
        }
        return null;
    }

    @Override
    public void deleteCompraById(Long id) {
        compraRepository.deleteById(id);
    }

}
