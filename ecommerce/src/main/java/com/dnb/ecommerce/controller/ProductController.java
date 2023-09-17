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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnb.ecommerce.dto.Product;
import com.dnb.ecommerce.exception.ConstraintNotMatchException;
import com.dnb.ecommerce.exception.IdNotFoundException;
import com.dnb.ecommerce.exception.InvalidIdException;
import com.dnb.ecommerce.payload.request.ProductRequest;
import com.dnb.ecommerce.service.ProductService;
import com.dnb.ecommerce.utils.RequestToEntityMapper;

import jakarta.validation.Valid;

/*
 **REST API endPoints for CRUD operations**
	 * 1. Create a Product
	 * 2. Retrieve All Products
	 * 3. Retrieve a Product by ID
	 * 4. Update a Product
	 * 5. Delete a Product
*/

@RestController
@RequestMapping("api")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	RequestToEntityMapper mapper;

	/** createProduct method takes productRequest parameter and validate it **/
	@PostMapping("/products/create")
	public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequest productRequest) throws InvalidNameException {
		Product product = mapper.getProductEntityObject(productRequest); // mapping productRequest to product entity object
		try {
			return new ResponseEntity(productService.createProduct(product), HttpStatus.CREATED);
		} catch (InvalidNameException e) {
			throw new InvalidNameException("Product name should be unique");
		}
	}

	/** getProductById method return the product corresponding to given productId **/
	@GetMapping("/products/{productId}")
	public ResponseEntity<?> getProductById(@PathVariable("productId") String productId) throws IdNotFoundException, InvalidIdException {
		try {
			return ResponseEntity.ok(productService.getProductById(productId).get());
		} catch (IdNotFoundException e) {
			throw new IdNotFoundException("Product Id doesn't exist");
		} catch (InvalidIdException e) {
			throw new InvalidIdException("Product Id Not Found");
		}
	}

	/** getAllProduct method return all products **/
	@GetMapping("/products")
	public ResponseEntity<?> getAllProduct() {
		List<Product> products = productService.getAllProduct();
		
		if(products.isEmpty())
			return ResponseEntity.ok(HttpStatus.NOT_FOUND);
		
		return ResponseEntity.ok(products);
	}

	/** deleteProductById method delete the product corresponding to given productId **/
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<?> deleteProductById(@PathVariable("productId") String productId) throws IdNotFoundException, ConstraintNotMatchException {
		
			try {
				productService.deleteProductById(productId);
			} catch (IdNotFoundException e) {
				throw new IdNotFoundException("Product Id Not Found");
			} catch (ConstraintNotMatchException e) {
				throw new ConstraintNotMatchException("Expire Date Should Be Before Current Date To Delete Product");
			}
		
		return ResponseEntity.ok("Product Deleted Successfully");
	}
	
	/** updateProduct method update the particular product which have same productId with the productRequest fields **/
	@PutMapping("/products/{productId}")
	public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductRequest productRequest, @PathVariable("productId") String productId) throws InvalidNameException, IdNotFoundException {
		Optional<Product> updatedProduct;
		if(productService.productIdExist(productId)) {
			Product product = mapper.getProductEntityObject(productRequest);
			product.setProductId(productId);
			updatedProduct = productService.updateProduct(product, productId);
		}
		else
			throw new IdNotFoundException("Product Id Doesn't exist");
		
		return new ResponseEntity(updatedProduct, HttpStatus.OK);
	}
}
