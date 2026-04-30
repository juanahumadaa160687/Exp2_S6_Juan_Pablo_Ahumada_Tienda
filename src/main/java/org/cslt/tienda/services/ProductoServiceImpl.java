package org.cslt.tienda.services;

import org.cslt.tienda.models.producto.Producto;
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
        Producto existingProducto = productoRepository.findById(id).orElse(null);

        if (existingProducto != null) {
            existingProducto.setNombre(producto.getNombre());
            existingProducto.setDescripcion(producto.getDescripcion());
            existingProducto.setPrecio(producto.getPrecio());
            existingProducto.setStock(producto.getStock());

            productoRepository.save(existingProducto);

            return existingProducto;
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
