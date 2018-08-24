package com.medina.cursomc.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;


import com.medina.cursomc.domain.Cidade;
import com.medina.cursomc.domain.Cliente;
import com.medina.cursomc.domain.Endereco;
import com.medina.cursomc.domain.enums.TipoCliente;
import com.medina.cursomc.dto.ClienteDTO;
import com.medina.cursomc.dto.ClienteNewDTO;
import com.medina.cursomc.repositories.CidadeRepository;
import com.medina.cursomc.repositories.ClienteRepository;
import com.medina.cursomc.repositories.EnderecoRepository;
import com.medina.cursomc.services.exceptions.DataIntegrityException;
import com.medina.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired EnderecoRepository enderecoRepository;
	
	@Autowired CidadeRepository cidadeRepository;
	
	public Cliente find(Integer id) {
		
		Optional<Cliente> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id:" + id+", Tipo: "+ Cliente.class.getName()));
		
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj= repo.save(obj);
		enderecoRepository.saveAll(obj.getEndereco());
		return obj;
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
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null,objDto.getNome(),objDto.getEmail(),objDto.getCpfOuCnpj(),TipoCliente.toEnum(objDto.getTipo()));
		//Cidade cid = new Cidade(objDto.getCidadeId(),null,null);
		Cidade cid1 = findCidade(objDto.getCidadeId());
		Endereco end = new Endereco(null,objDto.getLogradouro(),objDto.getNumero(),objDto.getComplemento(),objDto.getBairro(),objDto.getCep(),cli,cid1);
		cli.getEndereco().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if(objDto.getTelefone2()!= null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if(objDto.getTelefone3()!= null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}
	
	private void updateData(Cliente newObj,Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	private Cidade findCidade(Integer id) {
		
		Optional<Cidade> obj = cidadeRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id:" + id+", Tipo: "+ Cidade.class.getName()));
		
	}
	
	
	

}
