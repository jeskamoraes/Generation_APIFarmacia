package com.generation.farmacia.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.farmacia.model.Categoria;
import com.generation.farmacia.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	/* BUSCAR POR NOME DO PRODUTO */
	public List<Produto> findAllByNomeProdutoContainingIgnoreCase(String nomeProduto);

	/* BUSCAR POR NOME DO PRODUTO E NOME DO LABORATÓRIO */
	public List<Produto> findByNomeProdutoAndLaboratorio(String nomeProduto, String laboratorio);

	/* BUSCAR POR NOME DO PRODUTO OU NOME DO LABORATÓRIO */
	public List<Produto> findByNomeProdutoOrLaboratorio(String nomeProduto, String laboratorio);

	/*
	 * BUSCAR PRODUTOS ENTRE VALORES INFORMADOS NOS PARÂMETROS (VALORINICIAL, VALOR
	 * FINAL)
	 * 
	 * Anottation @Query -> Permite executar uma consulta no Spring utilizando
	 * código SQL nativo
	 * 
	 * :inicio -> Parâmetro da consulta SQL (Valor inicial)
	 * 
	 * :final -> Parâmetro da consulta SQL (Valor final)
	 * 
	 * nativeQuery = true -> Indica que o código da consulta é o SQL nativo que é
	 * compatível com qualquer SGBD Relacional
	 * 
	 * @Param("inicio") -> Mapeia o parâmetro :inicio da consulta na variável
	 * BigDecimal inicio que é o primeiro parâmetro do método buscarProdutosEntre
	 * 
	 * @Param("fim") -> Mapeia o parâmetro :fim da consulta na variável BigDecimal
	 * fim que é o segundo parâmetro do método buscarProdutosEntre
	 * 
	 * Method Query equivalente: public List<Produto> findByPrecoBetween(BigDecimal
	 * inicio, BigDecimal fim);
	 */
	@Query(value = "select * from tb_produto where preco between :inicio and :fim", nativeQuery = true)
	public List<Produto> buscarProdutosEntre(@Param("inicio") BigDecimal inicio, @Param("fim") BigDecimal fim);
}
