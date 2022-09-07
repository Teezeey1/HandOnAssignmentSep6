package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Sale;
import com.example.demo.repository.SaleRepository;

@Service
public class SaleServiceImpl implements SaleService{

	@Autowired
	SaleRepository saleRepository;
	
	@Override
	public List<Sale> getAll() {
		return saleRepository.findAll();
	}

	@Override
	public Optional<Sale> findSaleById(Sale s) {
		return saleRepository.findById(s.getItem_id());
	}
	
	/** 
	 * handle update by create new sale if previous sale do not exist,
	 * and update existing one if item_id match up
	 * **/
	@Override
	public Sale updateSaleById(Sale s) {
		return saleRepository.save(s);
	}

	@Override
	public void deleteSaleById(Sale s) {
		saleRepository.deleteById(s.getItem_id());
	}

	@Override
	public Sale createSale(Sale s) {
		return saleRepository.save(s);
	}
	
	
}
