package org.cslt.tienda.services;

import org.cslt.tienda.models.Cliente;
import org.cslt.tienda.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente newCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente updateCliente(Long id, Cliente cliente) {
        for (Cliente existingCliente : clienteRepository.findAll()) {
            if (Objects.equals(existingCliente.getCliente_id(), id)) {
                existingCliente.setName(cliente.getName());
                existingCliente.setEmail(cliente.getEmail());
                existingCliente.setPhone(cliente.getPhone());
                existingCliente.setAddress(cliente.getAddress());
                existingCliente.setNro_documento(cliente.getNro_documento());
                return clienteRepository.save(existingCliente);
            }
        }
        return null;
    }

    @Override
    public void deleteClienteById(Long id) {
        clienteRepository.deleteById(id);
    }

}
