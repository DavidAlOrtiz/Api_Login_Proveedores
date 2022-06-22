package mx.edu.app.uno.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import mx.edu.app.uno.web.domain.Entidad;
import mx.edu.app.uno.web.service.EntidaService;

@RestController
@RequestMapping("/entidades")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH,
        RequestMethod.DELETE })
public class CtrlEntidad {
    @Autowired
    EntidaService entidadService;

    @GetMapping
    public ResponseEntity get() {
        return new ResponseEntity(entidadService.getEntidades(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable String id) {
        return new ResponseEntity(entidadService.getEntidad(id), HttpStatus.OK);
    }
}
