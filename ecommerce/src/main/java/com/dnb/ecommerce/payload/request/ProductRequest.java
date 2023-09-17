package com.dnb.ecommerce.payload.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/*
 ** ProductRequest Data Class with following fields **
	 * 1. Product Name : String (unique, NotBlank)
	 * 2. Product Description : String (NotBlank)
	 * 3. ProductExpiryDate : String (NotBalnk)
	 * 4. Product Price : Float (Positive Value)
*/

@Data
public class ProductRequest {
	@NotBlank(message = "Name can't be blank.")
	private String productName;
	@NotBlank(message = "Description can't be blank.")
	private String productDescription;
	private String productCatagory;
	@NotBlank(message = "Date can't blank")
	@jakarta.validation.constraints.Pattern(regexp = "^(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/\\d{4}$") // validation for date (Format : dd/mmm/yy)
	private String productExpiryDate;
	@Min(value = 0, message = "Price can't be negative.")
	float productPrice;
}
