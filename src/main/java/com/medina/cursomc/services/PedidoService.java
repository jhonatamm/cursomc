package com.medina.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medina.cursomc.domain.ItemPedido;
import com.medina.cursomc.domain.PagamentoComBoleto;
import com.medina.cursomc.domain.Pedido;
import com.medina.cursomc.domain.enums.EstadoPagamento;
import com.medina.cursomc.repositories.ItemPedidoRepository;
import com.medina.cursomc.repositories.PagamentoRepository;
import com.medina.cursomc.repositories.PedidoRepository;
import com.medina.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired BoletoServices boletoServices;
	
	@Autowired PagamentoRepository pagamentoRepository;
	
	@Autowired ProdutoService produtoService;
	
	@Autowired ItemPedidoRepository itemPedidoRepository;
	
	@Autowired ClienteService clienteService;
	
	@Autowired private EmailService emailService;
	
	public Pedido buscar(Integer id) {
		
		Optional<Pedido> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id:" + id+", Tipo: "+ Pedido.class.getName()));
		
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagamentoComBoleto = (PagamentoComBoleto) obj.getPagamento();
			boletoServices.preencherPagamentoComBoleto(pagamentoComBoleto,obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.buscar(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		//System.out.println(obj);
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}

}
