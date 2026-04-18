package org.cslt.tienda.controllers;

import org.cslt.tienda.models.Cliente;
import org.cslt.tienda.repositories.ClienteRepository;
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
            throw new RuntimeException("Error al obtener los clientes: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Long id){
        try {
            return clienteService.getClienteById(id);
        }
        catch (Exception e){
            throw new RuntimeException("Error al obtener el cliente: " + e.getMessage());
        }
    }

    @PostMapping("/new")
    public Cliente createCliente(@RequestBody Cliente cliente){
        return clienteService.newCliente(cliente);
    }

    @PutMapping("/edit/{id}")
    public Cliente updateCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        return clienteService.updateCliente(id, cliente);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCliente(@PathVariable Long id){
        clienteService.deleteClienteById(id);
    }

}
