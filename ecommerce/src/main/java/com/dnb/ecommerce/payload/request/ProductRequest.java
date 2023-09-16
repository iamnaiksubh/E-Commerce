package com.dnb.ecommerce.payload.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductRequest {
	@NotBlank(message = "Name can't be blank.")
	private String productName;
	@NotBlank(message = "Description can't be blank.")
	private String productDescription;
	private String productCatagory;
	@NotBlank(message = "Date can't blank")
	private String productExpiryDate;
	@Min(value = 0, message = "Price can't be negative.")
	float productPrice;
}
