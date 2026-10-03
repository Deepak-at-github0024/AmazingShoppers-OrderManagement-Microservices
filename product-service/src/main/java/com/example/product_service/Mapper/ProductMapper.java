package com.example.product_service.Mapper;

import com.example.product_service.DTO.ProductRequest;
import com.example.product_service.DTO.ProductResponse;
import com.example.product_service.Entity.Product;

public class ProductMapper {

    public static Product toEntity(ProductRequest request) {

        Product product = new Product();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setCategory(request.getCategory());

        return product;
    }

    public static ProductResponse toResponse(Product product) {

        ProductResponse response = new ProductResponse();

        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());
        response.setCategory(product.getCategory());
        response.setCreatedAt(product.getCreatedAt());
        response.setUpdatedAt(product.getUpdatedAt());

        return response;
    }

    public static void updateEntity(Product existingProduct , ProductRequest request)
    {
        if (request.getName() != null) {
            existingProduct.setName(request.getName());
        }

        if (request.getCategory() != null) {
            existingProduct.setCategory(request.getCategory());
        }

        if (request.getPrice() != null) {
            existingProduct.setPrice(request.getPrice());
        }

        if (request.getDescription() != null) {
            existingProduct.setDescription(request.getDescription());
        }

        if (request.getQuantity() != null) {
            existingProduct.setQuantity(request.getQuantity());
        }
    }


}
