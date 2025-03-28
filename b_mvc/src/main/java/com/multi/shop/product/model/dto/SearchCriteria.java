package com.multi.shop.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {
	
	private String name;
	private String company;
	private int minPrice;
	private int maxPrice;

}
