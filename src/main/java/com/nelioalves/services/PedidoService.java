package com.nelioalves.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nelioalves.domain.ItemPedido;
import com.nelioalves.domain.PagamentoComBoleto;
import com.nelioalves.domain.Pedido;
import com.nelioalves.domain.Produto;
import com.nelioalves.domain.enums.EstadoPagamento;
import com.nelioalves.repositories.ItemPedidoRepository;
import com.nelioalves.repositories.PagamentoRepository;
import com.nelioalves.repositories.PedidoRepository;

@Service
public class PedidoService {
	
	//para fazer a injeção de dependencia
	@Autowired
	private PedidoRepository repo;
	@Autowired
	private BoletoService boletoService;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired 
	private ProdutoService produtoService;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private ClienteService clienteService;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new com.nelioalves.services.exceptions.ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			Optional<Produto> p = Optional.of(produtoService.find(ip.getProduto().getId()));
			ip.setProduto(p.get());
			ip.setPreco(p.get().getPreco());
			ip.setPedido(obj);
			}
		itemPedidoRepository.saveAll(obj.getItens());
		System.out.println(obj);
		return obj;
	}
}
