package org.cslt.tienda.services;

import org.cslt.tienda.models.Producto;
import org.cslt.tienda.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto getProductoById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto updateProducto(Long id, Producto producto) {
        for(Producto p : getAllProductos()){
            if (p.getId_producto().equals(id)){
                p.setNombre(producto.getNombre());
                p.setPrecio(producto.getPrecio());
                p.setStock(producto.getStock());
                p.setDescripcion(producto.getDescripcion());
                return productoRepository.save(p);
            }
        }
        return null;
    }

    @Override
    public Producto newProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void deleteProductoById(Long id) {
        productoRepository.deleteById(id);
    }

}
