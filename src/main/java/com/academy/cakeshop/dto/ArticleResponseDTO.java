package com.academy.cakeshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponseDTO {
    private String articleName;
    private Double price;
    private Long productId;
    private String productName;
}
