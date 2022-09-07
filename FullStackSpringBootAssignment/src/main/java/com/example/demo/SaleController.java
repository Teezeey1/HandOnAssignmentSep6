package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Sale;
import com.example.demo.service.SaleService;

@RequestMapping("/api/sale")
@RestController
public class SaleController {
	
	@Autowired
	SaleService saleService;
	
	/**
	 * save method in JpaReposiotry might fail, handle it by returning internal server error
	 *  **/
	@GetMapping("getall")
	public ResponseEntity<Object> getAll(){
		try {
			var SaleList = saleService.getAll();
			return ResponseEntity.status(HttpStatus.OK).body(SaleList);
		}
		
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fail to getall");
		}
	}
	
	@GetMapping("getbyid")
	public ResponseEntity<Object> getById(@RequestBody Sale s){
		try {
			//result is of type Optional<Sale>
			var result = saleService.findSaleById(s);
			//when we have a Sale by ID
			if(result.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(result);
			}
			//When we do not have a sale by ID
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID do not exist");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID do not exist");
		}
	}
	
	//create a Sale
	@PostMapping("createsale")
	public ResponseEntity<Object> createSale(@RequestBody Sale s){
		try {
			//result has type Sale
			var result = saleService.createSale(s);
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}
		//when we fail to createSale
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail to create");
		}
	}
	
	@PutMapping("updatesale")
	public ResponseEntity<Object> updateSale(@RequestBody Sale s){
		try {
			//result has type Sale
			var result = saleService.updateSaleById(s);
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("update fail");
		}
	}
	
	@DeleteMapping("deletesale")
	public ResponseEntity<Object> deleteSale(@RequestBody Sale s){
		try {
			saleService.deleteSaleById(s);
			//if delete is success
			String deleteStatus = "delete successful";
			return ResponseEntity.status(HttpStatus.OK).body(deleteStatus);
		}
		//when we fail to delete
		catch(Exception e) {
			String deleteStatus = "delete fail";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(deleteStatus);
		}
	}
	
	 
	/**
	 * First get the itemID for the sale RequestBody,
	 * then try to locate if the sale exist in the database,
	 * if exist, use the setter to set sold_to to a buyer email
	 * 
	 * if sold_to is not null, then it is classify as sold
	 * if sold_to is null, then the item is still in stock
	 *  **/
	@PatchMapping("patchsoldto/{id}") 
	public ResponseEntity<Object> patchSoldTo(@RequestBody Sale s,@PathVariable int id){
			//result has type Optional<Sale>
			//my findsalebyId take in a Sale object
			Sale temp = new Sale();
			temp.setItem_id(id);
			var result = saleService.findSaleById(temp);
			
			//check if we can find by ID
			if (result.isPresent()) {
				//check to see if the sale has null sold_to
				if(result.get().getSold_to()==null) {
					//check to see if the requestbody has null sold_to
					if(s.getSold_to()==null) {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("requestbody sold_to is null");
					}
					
					//updating result's sold_to from databse into the sold_to of requestbody
					//first set the requestbody's sold_to to the result's sold_to
					result.get().setSold_to(s.getSold_to());
					//save the update sale back into databse
					saleService.createSale(result.get());
					return ResponseEntity.status(HttpStatus.OK).body(result);
				}
				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("result sold_to is not null");	
			}
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("result is empty");	

	}
	
}

