package com.multi.shop.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	private int id;
    private String name;
    private String content;
    private int price;
    private CompanyDTO company;
    private String img;
    private String status;
    private LocalDateTime createDate;
    private String createPerson;
    private LocalDateTime modifyDate;
    private String modifyPerson;
    private List<AttachmentDTO> attachments;

}
