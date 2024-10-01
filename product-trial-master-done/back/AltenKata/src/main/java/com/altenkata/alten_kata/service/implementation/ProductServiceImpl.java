package com.altenkata.alten_kata.service.implementation;

import com.altenkata.alten_kata.domain.Product;
import com.altenkata.alten_kata.mappers.ProductMapper;
import com.altenkata.alten_kata.model.ProductDTO;
import com.altenkata.alten_kata.repos.ProductRepository;
import com.altenkata.alten_kata.service.ProductService;
import com.altenkata.alten_kata.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(final ProductRepository productRepository, final ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDTO> findAll() {
        final List<Product> products = productRepository.findAll(Sort.by("id"));
        return productMapper.toDTO(products);
    }

    @Override
    public ProductDTO get(final Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        return productMapper.toDTO(product);
    }
    @Override
    public Long create(final ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        return productRepository.save(product).getId();
    }

    @Override
    public void update(final Long id, final ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        productMapper.updateProductFromDto(productDTO, product);
        productRepository.save(product);
    }

   @Override
    public void delete(final Long id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }


}
