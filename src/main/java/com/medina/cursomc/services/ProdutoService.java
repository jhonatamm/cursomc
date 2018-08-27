package com.medina.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.medina.cursomc.domain.Categoria;
import com.medina.cursomc.domain.Produto;
import com.medina.cursomc.repositories.CategoriaRepository;
import com.medina.cursomc.repositories.ProdutoRepository;
import com.medina.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired CategoriaRepository categoriaRepository;

	public Produto buscar(Integer id) {

		Optional<Produto> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrado! Id:" + id + ", Tipo: " + Produto.class.getName()));

	}
	
	public Page<Produto> search(String  nome,List<Integer> ids,int page, int linesPerPage,String orderBy,String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.search(nome,categorias,pageRequest);
		
	
	}

}
