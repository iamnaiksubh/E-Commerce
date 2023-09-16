package com.dnb.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnb.ecommerce.dto.Product;
import com.dnb.ecommerce.exception.IdNotFoundException;
import com.dnb.ecommerce.exception.InvalidIdException;
import com.dnb.ecommerce.payload.request.ProductRequest;
import com.dnb.ecommerce.service.ProductService;
import com.dnb.ecommerce.utils.RequestToEntityMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	RequestToEntityMapper mapper;

	@PostMapping("/create")
	public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequest productRequest) throws InvalidIdException {
		Product product = mapper.getProductEntityObject(productRequest);
		try {
			return new ResponseEntity(productService.createProduct(product), HttpStatus.CREATED);
		} catch (InvalidNameException e) {
			throw new InvalidIdException("Product name should be unique");
		}
	}

	@GetMapping("/{productId}")
	public ResponseEntity<?> getProductById(@PathVariable("productId") String productId) throws IdNotFoundException, InvalidIdException {
		try {
			return ResponseEntity.ok(productService.getProductById(productId).get());
		} catch (IdNotFoundException e) {
			throw new IdNotFoundException("Product Id doesn't exist");
		} catch (InvalidIdException e) {
			throw new InvalidIdException("Product Id Not Found");
		}
	}

	@GetMapping("/getAllProduct")
	public ResponseEntity<?> getAllProduct() {
		List<Product> products = productService.getAllProduct();
		
		if(products.isEmpty())
			return ResponseEntity.ok(HttpStatus.NOT_FOUND);
		
		return ResponseEntity.ok(products);
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<?> deleteProductById(@PathVariable("productId") String productId) throws IdNotFoundException {
		try {
			productService.deleteProductById(productId);
		} catch (IdNotFoundException e) {
			throw new IdNotFoundException("Product Id Not FOund");
		}
		return ResponseEntity.ok("Product Deleted Successfully");
	}
	
	@PostMapping("/{productId}")
	public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable("productId") String productId) throws InvalidNameException, IdNotFoundException {
		Optional<Product> updatedProduct;
		if(productService.productIdExist(productId))
			updatedProduct = productService.updateProduct(product);
		else
			throw new IdNotFoundException("Product Id Doesn't exist");
		return new ResponseEntity(updatedProduct, HttpStatus.OK);
	}
}
