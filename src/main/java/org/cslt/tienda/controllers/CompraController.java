package org.cslt.tienda.controllers;

import org.cslt.tienda.models.Compra;
import org.cslt.tienda.models.Producto;
import org.cslt.tienda.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @Autowired
    ProductoController productoController;

    @GetMapping("/all")
    public List<Compra> getAllCompras() {
        try {
            System.out.println("Obteniendo todas las compras...");
            return compraService.getAllCompras();
        }
        catch (Exception e) {
            throw new RuntimeException("Error al obtener las compras: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Compra getCompraById(@PathVariable Long id){
        try {
            System.out.println("Compra obtenida: " + compraService.findCompraById(id).getCompra_id());
            return compraService.findCompraById(id);
        }
        catch (Exception e) {
            throw new RuntimeException("Error al obtener la compra con id: " + id);
        }
    }

    @PostMapping("/new")
    public Compra newCompra(@RequestBody Compra compra){
        try {

            for (Producto p : compra.getProducto()){
                if(p.getStock() > 0) {
                    Producto producto = productoController.getProductoById(p.getId_producto());
                    producto.setStock(producto.getStock() - 1);
                    productoController.editProducto(producto.getId_producto(), producto);
                    System.out.println("Stock actualizado para el producto: " + producto.getNombre() + ", nuevo stock: " + producto.getStock());
                }
                else {
                    throw new RuntimeException("No hay stock suficiente para el producto: " + p.getNombre());
                }
            }

            return compraService.newCompra(compra);

        }
        catch (Exception e) {
            throw new RuntimeException("Error al crear compra: " + e.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public Compra editCompra(@PathVariable Long id, @RequestBody Compra compra){
        try {

            System.out.println("Actualizando compra con ID: " + id);
            return compraService.updateCompra(id, compra);
        }
        catch (Exception e) {
            throw new RuntimeException("Error al editar compra: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCompra(@PathVariable Long id) {
        try {
            for(Producto p : compraService.findCompraById(id).getProducto()){
                Producto producto = productoController.getProductoById(p.getId_producto());
                producto.setStock(producto.getStock() + 1);
                productoController.editProducto(producto.getId_producto(), producto);
                System.out.println("Stock actualizado para el producto: " + producto.getNombre() + ", nuevo stock: " + producto.getStock());
            }
            System.out.println("Eliminando compra con ID: " + id);
            compraService.deleteCompraById(id);
        }
        catch (Exception e) {
            throw new RuntimeException("Error al eliminar compra: " + e.getMessage());
        }
    }

}
