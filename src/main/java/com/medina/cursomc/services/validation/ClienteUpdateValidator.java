package com.medina.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.medina.cursomc.domain.Cliente;
import com.medina.cursomc.domain.enums.TipoCliente;
import com.medina.cursomc.dto.ClienteDTO;
import com.medina.cursomc.repositories.ClienteRepository;
import com.medina.cursomc.resources.exceptions.FieldMessage;
import com.medina.cursomc.services.validation.utils.BR;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired HttpServletRequest request;
	@Autowired ClienteRepository repo;
	@Override
	public void initialize(ClienteUpdate ann) {

	}

	@Override
	public boolean isValid(ClienteDTO objDTo, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		

		Cliente aux = repo.findByEmail(objDTo.getEmail());
		if(aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email","Email ja existente"));
		}
		// inclua os testes aqui, inserindo erros na lista
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();

		}
		return list.isEmpty();

	}
}