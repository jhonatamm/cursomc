package com.medina.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;

import com.medina.cursomc.domain.Cliente;
import com.medina.cursomc.domain.Cliente;
import com.medina.cursomc.dto.ClienteDTO;
import com.medina.cursomc.repositories.ClienteRepository;
import com.medina.cursomc.services.exceptions.DataIntegrityException;
import com.medina.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		
		Optional<Cliente> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id:" + id+", Tipo: "+ Cliente.class.getName()));
		
	}
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj,obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("não é possivel excluir por que há entidades relacionas");
		}
			
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(int page, int linesPerPage,String orderBy,String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	public Cliente fromDTO(ClienteDTO objDto) {
		
		return new Cliente(objDto.getId(),objDto.getNome(),objDto.getEmail(),null,null);
		
	}
	
	private void updateData(Cliente newObj,Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
