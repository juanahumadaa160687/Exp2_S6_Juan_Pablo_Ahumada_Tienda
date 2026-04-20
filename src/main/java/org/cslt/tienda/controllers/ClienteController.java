package org.cslt.tienda.controllers;

import org.cslt.tienda.models.Cliente;
import org.cslt.tienda.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")

public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/all")
    public List<Cliente> getAllClientes(){
        try {
            return clienteService.getAllClientes();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener clientes: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Long id){
        try {
            System.out.println("Cliente obtenido: " + clienteService.getClienteById(id).getName());
            return clienteService.getClienteById(id);
        }
        catch (Exception e){
            throw new RuntimeException("Error al obtener el cliente: " + e.getMessage());
        }
    }

    @PostMapping("/new")
    public Cliente createCliente(@RequestBody Cliente cliente){
        try {
            System.out.println("Recibido cliente: " + cliente.getName());

            return clienteService.newCliente(cliente);
        }
        catch (Exception e){
            throw new RuntimeException("Error al crear el cliente: " + e.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public Cliente updateCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        try {
            System.out.println("Actualizando cliente: " + clienteService.getClienteById(id).getName());
            return clienteService.updateCliente(id, cliente);
        }
        catch (Exception e){
            throw new RuntimeException("Error al actualizar cliente: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCliente(@PathVariable Long id){
        try {
            System.out.println("Eliminando cliente: " + clienteService.getClienteById(id).getName());
            clienteService.deleteClienteById(id);
        }
        catch (Exception e){
            throw new RuntimeException("Error al eliminar el cliente: " + e.getMessage());
        }
    }

}
