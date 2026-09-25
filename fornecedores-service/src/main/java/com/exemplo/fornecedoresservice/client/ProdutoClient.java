package com.exemplo.fornecedoresservice.client;

import com.exemplo.fornecedoresservice.dto.ProdutoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Cliente Feign para o produtos-service, no mesmo modelo usado pelo
 * vendas-service. O "name" e o nome registrado no Eureka: o Feign resolve
 * o endereco pelo Discovery, sem URL fixa no codigo.
 */
@FeignClient(name = "produtos-service")
public interface ProdutoClient {

    @GetMapping("/produtos")
    List<ProdutoDTO> listarTodos();
}
