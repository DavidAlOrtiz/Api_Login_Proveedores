package mx.edu.app.uno.web.controllers;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import mx.edu.app.uno.web.domain.Proveedor;
import mx.edu.app.uno.web.domain.Usuario;
import mx.edu.app.uno.web.service.ProveedorService;

@RestController
@RequestMapping("/proveedor")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH,
        RequestMethod.DELETE })
public class CtrProveedor {

    @Autowired
    ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity get() {
        return new ResponseEntity(proveedorService.getProvedores(), HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity add(@RequestBody Proveedor proveedor) {
        LocalTime fecha = LocalTime.now();
        proveedor.setId(fecha.toString().trim());
        return new ResponseEntity(proveedorService.guardarProveedor(proveedor), HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity editar(@PathVariable(value = "id") String id, @RequestBody Proveedor proveedor) {
        return new ResponseEntity(proveedorService.actualizarProveedor(id, proveedor), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") String id) {
        return new ResponseEntity(proveedorService.eliminar(id), HttpStatus.OK);
    }

}
