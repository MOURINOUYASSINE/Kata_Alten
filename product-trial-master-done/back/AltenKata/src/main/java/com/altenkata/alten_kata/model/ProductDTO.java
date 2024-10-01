package com.altenkata.alten_kata.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class ProductDTO {

    private Long id;

    @Size(max = 255)
    @NotBlank
    @NotNull
    private String code;

    @Size(max = 5000)
    @NotBlank
    @NotNull
    private String name;

    @Size(max = 255)
    private String description;

    @Size(max = 255)
    private String image;

    @Size(max = 255)
    private String category;

    @NotNull
    private Double price;

    @NotNull
    private Double quantity;

    @Size(max = 255)
    private String internalReference;

    private Long shellId;

    private Double rating;

    private InventoryStatus inventoryStatus;

    private LocalDateTime dateCreated;

    private LocalDateTime lastUpdated;
}
