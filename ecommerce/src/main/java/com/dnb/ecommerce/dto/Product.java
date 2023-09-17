package com.dnb.ecommerce.dto;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.dnb.ecommerce.utils.CustomProductIdGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 ** Product Entity or Product Data Class with following fields **
	 * 1. Product Id : String (Primary Key)
	 * 2. Product Name : String (unique, NotBlank)
	 * 3. Product Description : String (NotBlank)
	 * 4. ProductExpiryDate : String (NotBalnk)
	 * 5. Product Price : Float (Positive Value)
*/

@Data
@NoArgsConstructor
@Entity
public class Product {
	// Generating custom id for product id (Id Format : PRO_DATE_NUMERIC)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
	@GenericGenerator(name = "product_seq", strategy = "com.dnb.ecommerce.utils.CustomProductIdGenerator",
	parameters = {@Parameter(name = CustomProductIdGenerator.INCREMENT_PARAM, value = "20"),
			@Parameter(name = CustomProductIdGenerator.VALUE_PREFIX_PARAMETER, value = "PRO_"),
			@Parameter(name = CustomProductIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")})
	String productId;
	@NotBlank(message = "Name can't be blank.")
	@Column(unique = true)
	String productName;
	@NotBlank(message = "Description can't be blank.")
	String productDescription;
	String productCatagory;
	@NotBlank(message = "Date can't blank")
	String productExpiryDate;
	@Min(value = 0, message = "Price can't be negative.")
	float productPrice;
}
