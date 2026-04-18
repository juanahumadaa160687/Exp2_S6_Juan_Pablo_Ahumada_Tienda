package org.cslt.tienda.services;


import org.cslt.tienda.models.Cliente;

import java.util.List;

public interface ClienteService {

    List<Cliente> getAllClientes();
    Cliente getClienteById(Long id);
    Cliente newCliente(Cliente cliente);
    Cliente updateCliente(Long id, Cliente cliente);
    void deleteClienteById(Long id);

}
