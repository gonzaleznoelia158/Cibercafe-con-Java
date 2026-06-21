package cibercafe.controllers;

import cibercafe.modelo.Cliente;
import cibercafe.dao.ClienteDAO;

public class ClienteController {
    
    private ClienteDAO clienteDAO;
    
    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }
    
    public void registrarCliente(String nombre, String apellido, String telefono) {
        Cliente cli = new Cliente();
        cli.setNombre(nombre);
        cli.setApellido(apellido);
        cli.setTelefono(telefono);
        clienteDAO.insertar(cli);
    }
}
