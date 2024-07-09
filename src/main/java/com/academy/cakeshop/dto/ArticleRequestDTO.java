package com.academy.cakeshop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ArticleRequestDTO {

    @NotBlank(message = "Article name is mandatory")
    private String articleName;

    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be a positive value")
    private Double price;

    @NotNull(message = "Product ID is mandatory")
    private Long productId;
}
