package com.dnb.ecommerce.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dnb.ecommerce.dto.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, String>{
	/**
	 * Retrieve a product by its name.
	 * @param productName : The name of the product to search for.
	 * @return An Optional containing the found product if it exists, or an empty Optional if not.
	 */
	public Optional<Product> findByProductName(String productName);
}
