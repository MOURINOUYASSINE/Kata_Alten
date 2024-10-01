package com.altenkata.alten_kata.service;

import com.altenkata.alten_kata.domain.Product;
import com.altenkata.alten_kata.mappers.ProductMapper;
import com.altenkata.alten_kata.model.ProductDTO;
import com.altenkata.alten_kata.repos.ProductRepository;
import com.altenkata.alten_kata.service.implementation.ProductServiceImpl;
import com.altenkata.alten_kata.util.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        product = new Product();
        product.setId(1L);
        productDTO = new ProductDTO();
        productDTO.setId(1L);
    }



    @Test
    void get_ShouldReturnProductDTO_WhenProductExists() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productMapper.toDTO(product)).thenReturn(productDTO);

        ProductDTO result = productService.get(1L);

        assertEquals(productDTO.getId(), result.getId());
        verify(productRepository).findById(1L);
    }

    @Test
    void get_ShouldThrowNotFoundException_WhenProductDoesNotExist() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> productService.get(1L));
        verify(productRepository).findById(1L);
    }

    @Test
    void create_ShouldReturnProductId() {
        when(productMapper.toEntity(productDTO)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);

        Long result = productService.create(productDTO);

        assertEquals(product.getId(), result);
        verify(productMapper).toEntity(productDTO);
        verify(productRepository).save(product);
    }

    @Test
    void update_ShouldUpdateProduct_WhenProductExists() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productMapper.toEntity(productDTO)).thenReturn(product);
        
        productService.update(1L, productDTO);

        verify(productMapper).updateProductFromDto(productDTO, product);
        verify(productRepository).save(product);
    }
    @Test
    void findAll_ShouldReturnProductDTOList() {
        // Arrange: Set up mock behavior
        when(productRepository.findAll(any(Sort.class))).thenReturn(Arrays.asList(product));
        when(productMapper.toDTO(anyList())).thenReturn(Collections.singletonList(productDTO));

        // Act: Call the method under test
        List<ProductDTO> result = productService.findAll();

        // Assert: Verify the result and interactions
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(productDTO.getId(), result.get(0).getId());

        verify(productRepository).findAll(any(Sort.class));
        verify(productMapper).toDTO(anyList());
    }

    @Test
    void update_ShouldThrowNotFoundException_WhenProductDoesNotExist() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> productService.update(1L, productDTO));
        verify(productRepository).findById(1L);
    }

    @Test
    void delete_ShouldDeleteProduct_WhenProductExists() {
        when(productRepository.existsById(1L)).thenReturn(true);

        productService.delete(1L);

        verify(productRepository).deleteById(1L);
    }

    @Test
    void delete_ShouldThrowNotFoundException_WhenProductDoesNotExist() {
        when(productRepository.existsById(1L)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> productService.delete(1L));
        verify(productRepository).existsById(1L);
    }
}
