package com.medina.cursomc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errorList = new ArrayList<>();



	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		// TODO Auto-generated constructor stub
	}
	public List<FieldMessage> getErros() {
		return errorList;
	}

	public void addError(String fieldName, String message) {
		errorList.add(new FieldMessage(fieldName,message));
	}
}
