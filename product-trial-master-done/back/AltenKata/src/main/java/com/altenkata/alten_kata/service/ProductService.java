package com.altenkata.alten_kata.service;

import com.altenkata.alten_kata.model.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> findAll();
    ProductDTO get(final Long id);
    Long create(final ProductDTO productDTO);
    void update(final Long id, final ProductDTO productDTO);
    void delete(final Long id);

}
