package com.dnb.ecommerce.service;

import java.util.List;

import java.util.Optional;

import javax.naming.InvalidNameException;

import org.springframework.stereotype.Service;

import com.dnb.ecommerce.dto.Product;
import com.dnb.ecommerce.exception.ConstraintNotMatchException;
import com.dnb.ecommerce.exception.IdNotFoundException;
import com.dnb.ecommerce.exception.InvalidIdException;

public interface ProductService {
	/**
	 * Create a new product.
	 * @param product : The product to be created.
	 * @return The created product.
	 */
	public Product createProduct(Product product) throws InvalidNameException;
	
	/**
	 * Retrieve a list of all products.
	 * @return A list of all products.
	 */
	public List<Product> getAllProduct();
	
	/**
	 * Retrieve a product by its Id.
	 * @param productId : The Id of the product to search for.
	 * @return An Optional containing the found product if it exists, or an empty Optional if not.
	 */
	public Optional<Product> getProductById(String productId) throws IdNotFoundException, InvalidIdException;
	
	/**
	 * Delete a product by its Id.
	 * @param productId : The Id of the product to be delete.
	 * @return True if the product was successfully deleted, false otherwise.
	 */
	public boolean deleteProductById(String productId) throws IdNotFoundException, ConstraintNotMatchException;
	
	/**
	 * Check if a product with a given Id exists or not.
	 * @param productId : The Id of the product to check.
	 * @return True if the product with the specified Id exists, false otherwise.
	 */
	public boolean productIdExist(String productId);
	
	/**
	 * Update a product by providing a product with updated fields and the Id of the product to be updated.
	 * @param product : The product with updated fields.
	 * @param productId : The Id of the product to update.
	 * @return An Optional containing the updated product if it exists, or an empty Optional if not.
	 */
	Optional<Product> updateProduct(Product product, String productId) throws InvalidNameException;
}
