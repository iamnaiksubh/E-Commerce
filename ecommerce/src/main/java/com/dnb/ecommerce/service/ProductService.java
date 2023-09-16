package com.dnb.ecommerce.service;

import java.util.List;

import java.util.Optional;

import javax.naming.InvalidNameException;

import org.springframework.stereotype.Service;

import com.dnb.ecommerce.dto.Product;
import com.dnb.ecommerce.exception.IdNotFoundException;
import com.dnb.ecommerce.exception.InvalidIdException;

public interface ProductService {
	public Product createProduct(Product product) throws InvalidNameException;
	public List<Product> getAllProduct();
	public Optional<Product> getProductById(String productId) throws IdNotFoundException, InvalidIdException;
	public Optional<Product> updateProduct(Product product) throws InvalidNameException;
	public boolean deleteProductById(String productId) throws IdNotFoundException;
	public boolean productIdExist(String productId);
}
