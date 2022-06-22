package mx.edu.app.uno.web.controllers;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import mx.edu.app.uno.web.domain.Usuario;
import mx.edu.app.uno.web.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH,
        RequestMethod.DELETE })

public class controladorUsuario {
    @Autowired
    private UsuarioService servicio;

    @PostMapping(value = "/add")
    public String add(@RequestBody Usuario usuario) {
        LocalTime fecha = LocalTime.now();
        usuario.setId(fecha.toString().trim());
        return servicio.guardarCliente(usuario);
    }

    @GetMapping
    public List<Usuario> get() {
        return servicio.getUsuario();
    }

    @PutMapping(value = "/update/{id}")
    public String editar(@PathVariable(value = "id") String id, @RequestBody Usuario usuario) {
        return servicio.actualizarCliente(id, usuario);
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id") String id) {
        return servicio.eliminar(id);
    }

    @GetMapping(value = "/{id}")
    public Usuario getUsuarioById(@PathVariable(value = "id") String id)
            throws InterruptedException, ExecutionException {
        return servicio.obtenerUsuario(id);
    }

    @PostMapping(value = "/login")
    public Usuario getUsuarioLogin(@RequestBody Usuario usuario) {
        return servicio.usuarioValido(usuario);
    }

}
