package com.medina.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.medina.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoServices {
	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagamentoComBoleto, Date instanteDoPedido) {
	
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagamentoComBoleto.setDataPagamento(cal.getTime());
		
	}

}
