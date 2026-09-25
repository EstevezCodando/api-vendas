package com.exemplo.fornecedoresservice.config;

import com.exemplo.fornecedoresservice.model.Fornecedor;
import com.exemplo.fornecedoresservice.repository.FornecedorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Popula o banco H2 em memoria com cinco fornecedores assim que a aplicacao sobe.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final FornecedorRepository fornecedorRepository;

    public DataInitializer(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    @Override
    public void run(String... args) {
        fornecedorRepository.save(new Fornecedor("Tech Distribuidora LTDA", "12.345.678/0001-90"));
        fornecedorRepository.save(new Fornecedor("Info Supply Comercio", "23.456.789/0001-01"));
        fornecedorRepository.save(new Fornecedor("Alpha Componentes SA", "34.567.890/0001-12"));
        fornecedorRepository.save(new Fornecedor("Norte Importadora ME", "45.678.901/0001-23"));
        fornecedorRepository.save(new Fornecedor("Central Perifericos EIRELI", "56.789.012/0001-34"));
    }
}
