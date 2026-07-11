package com.example.product_service.Service.Impl;

import com.example.product_service.DTO.ProductRequest;
import com.example.product_service.DTO.ProductResponse;
import com.example.product_service.Entity.Product;
import com.example.product_service.Mapper.ProductMapper;
import com.example.product_service.Repository.ProductRepository;
import com.example.product_service.Service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository ;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public ProductResponse createProduct(@RequestBody  ProductRequest request) {

        Product product = ProductMapper.toEntity(request);
        Product savedProduct = productRepository.save(product);

        return  ProductMapper.toResponse(savedProduct);

    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return List.of();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        return null;
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
