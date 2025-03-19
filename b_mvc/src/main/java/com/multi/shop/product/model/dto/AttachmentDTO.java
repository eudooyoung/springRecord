package com.multi.shop.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentDTO implements java.io.Serializable {
	
	private long no;
    private int refProductNo;
    private String originalName;
    private String savedName;
    private String savePath;
    private String thumbnailPath;
    private String status;
    private LocalDateTime createDate;
    private String createPerson;
    private LocalDateTime modifyDate;
    private String modifyPerson;

	
}
