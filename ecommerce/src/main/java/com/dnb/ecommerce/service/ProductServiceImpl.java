package com.dnb.ecommerce.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dnb.ecommerce.dto.Product;
import com.dnb.ecommerce.exception.ConstraintNotMatchException;
import com.dnb.ecommerce.exception.IdNotFoundException;
import com.dnb.ecommerce.exception.InvalidIdException;
import com.dnb.ecommerce.repo.ProductRepository;
import com.dnb.ecommerce.utils.DateUtils;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	DateUtils dateUtils;
	
	@Override
	public Product createProduct(Product product) throws InvalidNameException {
		// Fetching the product with the product name
		Optional<Product> product2 = productRepository.findByProductName(product.getProductName());
		if(product2.isEmpty()) // No product exist with mention product name i.e, product name is unique
			return productRepository.save(product);
		else { 
			// Product exist with mention product name i.e, product name is not unique
			throw new InvalidNameException("Product with same name exist, name should be unique");
		}
	}
	
	@Override
	public List<Product> getAllProduct() {
		// Fetching all products
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public Optional<Product> getProductById(String productId) throws IdNotFoundException, InvalidIdException {
		// Checking if product exist by this Id or not
		if(productRepository.existsById(productId)) {
			Optional<Product> result = productRepository.findById(productId);
			
			// If product exist then returning it
			if(result.isPresent()) {
				return result;
			}else {
				throw new IdNotFoundException("Product Id Not Found");
			}
			
		}else
			throw new InvalidIdException("Invalid Product Id"); // No product with Id exist
	}

	@Override
	public Optional<Product> updateProduct(Product product, String productId) throws InvalidNameException {
			
		// Fetching product by product name
		Optional<Product> product2 = productRepository.findByProductName(product.getProductName());
		
		// Checking the Id, if matches means same product is being update and have the same product name
		if(product2.isPresent() && product2.get().getProductId().equals(productId)){
			productRepository.save(product);
			return Optional.of(product);
		}else if(product2.isPresent()) { // means product with same name exist and have different Id (not unique name)
			throw new InvalidNameException("Product Name should be unique");
		}else {
		// means name is unique
		productRepository.save(product);
		return Optional.of(product);
		}
	}

	@Override
	public boolean deleteProductById(String productId) throws IdNotFoundException, ConstraintNotMatchException {
		// Checking if product exist by Id or not
		if (productRepository.existsById(productId)) {
			// Fetching the expiry date of the product
			String dateFormat = "dd/MM/yyyy";
			LocalDate expiryDate = dateUtils.getLocalDate(productRepository.findById(productId).get().getProductExpiryDate(), dateFormat);
			LocalDate currentDate = dateUtils.getLocalDate(dateUtils.getCurrentDate(dateFormat), dateFormat);
			
			
			
			// If expiry date is before the current date, then it can be deleted
			if(expiryDate.isBefore(currentDate)){
				productRepository.deleteById(productId);
				if (!productRepository.existsById(productId)) {
					return true;
				}
			}else {
				throw new ConstraintNotMatchException("Expire Date Should Be Before Current Date To Delete Product");
			}
			
			return false;
		}else {
			throw new IdNotFoundException("Product Id Not Found");
		}
	}

	@Override
	public boolean productIdExist(String productId) {
		// Checking if product exist by Id or not
		if(productRepository.existsById(productId))
			return true;
		return false;
	}

}
