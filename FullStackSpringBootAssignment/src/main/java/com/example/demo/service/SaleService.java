package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Sale;

public interface SaleService {
	List<Sale> getAll();
	Optional<Sale> findSaleById(Sale s);
	Sale updateSaleById(Sale s );
	void deleteSaleById(Sale s);
	Sale createSale(Sale s);
}
