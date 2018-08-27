package com.medina.cursomc;


import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.medina.cursomc.domain.Categoria;
import com.medina.cursomc.domain.Cidade;
import com.medina.cursomc.domain.Cliente;
import com.medina.cursomc.domain.Endereco;
import com.medina.cursomc.domain.Estado;
import com.medina.cursomc.domain.ItemPedido;
import com.medina.cursomc.domain.Pagamento;
import com.medina.cursomc.domain.PagamentoComBoleto;
import com.medina.cursomc.domain.PagamentoComCartao;
import com.medina.cursomc.domain.Pedido;
import com.medina.cursomc.domain.Produto;
import com.medina.cursomc.domain.enums.EstadoPagamento;
import com.medina.cursomc.domain.enums.TipoCliente;
import com.medina.cursomc.repositories.CategoriaRepository;
import com.medina.cursomc.repositories.CidadeRepository;
import com.medina.cursomc.repositories.ClienteRepository;
import com.medina.cursomc.repositories.EnderecoRepository;
import com.medina.cursomc.repositories.EstadoRepository;
import com.medina.cursomc.repositories.ItemPedidoRepository;
import com.medina.cursomc.repositories.PagamentoRepository;
import com.medina.cursomc.repositories.PedidoRepository;
import com.medina.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	

	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		
		
		
	}
	
	
}
