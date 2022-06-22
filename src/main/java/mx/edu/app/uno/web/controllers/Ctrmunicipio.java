package mx.edu.app.uno.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.app.uno.web.domain.Municipio;
import mx.edu.app.uno.web.service.MunicipioService;

@RestController
@RequestMapping("/municipios")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH,
        RequestMethod.DELETE })
public class Ctrmunicipio {

    @Autowired
    MunicipioService municipiosService;

    @GetMapping()
    public ResponseEntity get() {
        List<Municipio> municipios = new ArrayList<Municipio>();
        municipios.add(new Municipio());
        return new ResponseEntity(municipiosService.getMunicipios(), HttpStatus.OK);
    }

    @GetMapping("/municipio/{id}")
    public ResponseEntity getMunicipio(@PathVariable String id) {
        List<Municipio> municipios = new ArrayList<Municipio>();
        municipios.add(new Municipio());
        return new ResponseEntity(municipiosService.getMunicipio(id), HttpStatus.OK);
    }
}
