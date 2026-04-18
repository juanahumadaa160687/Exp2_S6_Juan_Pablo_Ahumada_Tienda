package org.cslt.tienda.services;


import org.cslt.tienda.models.Producto;

import java.util.List;

public interface ProductoService {

    List<Producto> getAllProductos();
    Producto getProductoById(Long id);
    Producto newProducto(Producto producto);
    void deleteProductoById(Long id);
    Producto updateProducto(Long id, Producto producto);

}
