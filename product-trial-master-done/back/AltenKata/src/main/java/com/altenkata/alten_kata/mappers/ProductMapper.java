package com.altenkata.alten_kata.mappers;

import com.altenkata.alten_kata.domain.Product;
import com.altenkata.alten_kata.model.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

     Product toEntity(ProductDTO productDTO);
     List<Product> toEntity(List<ProductDTO> productDTO);
     ProductDTO toDTO(Product product);
     List<ProductDTO> toDTO(List<Product> product);
    @Mapping(target = "id", ignore = true)
    void updateProductFromDto(ProductDTO productDTO, @MappingTarget Product product);

}
