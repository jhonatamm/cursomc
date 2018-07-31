package com.medina.cursomc.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.medina.cursomc.domain.Categoria;
import com.medina.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value ="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable  Integer id) {
		
		Categoria obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);

	}
	@RequestMapping(value="/test", method= RequestMethod.GET)
	public String test() {
		return "ok ok ok ok ok";
	}

}
