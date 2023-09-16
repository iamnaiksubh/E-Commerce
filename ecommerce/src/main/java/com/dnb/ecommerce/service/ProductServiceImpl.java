package com.dnb.ecommerce.service;

import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dnb.ecommerce.dto.Product;
import com.dnb.ecommerce.exception.IdNotFoundException;
import com.dnb.ecommerce.exception.InvalidIdException;
import com.dnb.ecommerce.repo.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Product createProduct(Product product) throws InvalidNameException {
		Optional<Product> product2 = productRepository.findByProductName(product.getProductName());
		if(product2.isEmpty())
			return productRepository.save(product);
		else
			throw new InvalidNameException("Product with same name exist, name should be unique");
	}
	
	@Override
	public List<Product> getAllProduct() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public Optional<Product> getProductById(String productId) throws IdNotFoundException, InvalidIdException {
		
		if(productRepository.existsById(productId)) {
			Optional<Product> result = productRepository.findById(productId);
			
			if(result.isPresent()) {
				return result;
			}else {
				throw new IdNotFoundException("Product Id Not Found");
			}
			
		}else
			throw new InvalidIdException("Invalid Product Id");
	}

	@Override
	public Optional<Product> updateProduct(Product product) throws InvalidNameException {
			
		Optional<Product> product2 = productRepository.findByProductName(product.getProductName());
		if(product2.isEmpty()){
			productRepository.save(product);
			return Optional.of(product);
		}else {
			throw new InvalidNameException("Prouct name should unique.");
		}
	}

	@Override
	public boolean deleteProductById(String productId) throws IdNotFoundException {
		
		if (productRepository.existsById(productId)) {
			productRepository.deleteById(productId);
			
			if (!productRepository.existsById(productId)) {
				return true;
			}
			
			return false;
		}else {
			throw new IdNotFoundException("Product Id Not Found");
		}
	}

	@Override
	public boolean productIdExist(String productId) {
		if(productRepository.existsById(productId))
			return true;
		return false;
	}

}
