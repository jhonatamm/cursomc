package com.medina.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.medina.cursomc.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {
		Categoria c1 = new Categoria(1,"informatica");
		Categoria c2 = new Categoria(2,"Escritorio");
		
		List<Categoria> clist = new ArrayList<>();
		clist.add(c1);
		clist.add(c2);
		
		return clist;
	}

}
