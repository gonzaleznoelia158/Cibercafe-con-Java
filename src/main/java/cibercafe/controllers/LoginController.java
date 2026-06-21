package cibercafe.controllers;

import cibercafe.dao.UsuarioDAO;
import cibercafe.modelo.Usuario;

public class LoginController {
    
    private UsuarioDAO usuarioDAO;

    public LoginController() {
        this.usuarioDAO = new  UsuarioDAO();
    }
    
    public boolean login(String nombreUsuario, String contrasenia) {
        Usuario us = usuarioDAO.buscarPorCredenciales(nombreUsuario, contrasenia);
        if (us != null) {
            return true;
        } else {
            return false;
        }
    }
    
    public void registrarUsuario(String nombreUsuario, String contrasenia) {
    Usuario u = new Usuario();
    u.setNombreUsuario(nombreUsuario);
    u.setContrasenia(contrasenia);
    u.setRol("empleado");
    usuarioDAO.insertar(u);
}
}
