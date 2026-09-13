package com.example.product_service.Service.Impl;

import com.example.product_service.DTO.ProductRequest;
import com.example.product_service.DTO.ProductResponse;
import com.example.product_service.Entity.Product;
import com.example.product_service.Exception.ProductNotFoundException;
import com.example.product_service.Mapper.ProductMapper;
import com.example.product_service.Repository.ProductRepository;
import com.example.product_service.Service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository ;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public ProductResponse createProduct(@RequestBody  ProductRequest request) {

       boolean existByNameAndCategory = productRepository.existsByNameAndCategory(request.getName(),request.getCategory()) ;

        if(existByNameAndCategory)
        {
            throw new ProductNotFoundException(
                    "Product Already Exists with the name "+request.getName()+" and category "+request.getCategory());
        }

        Product product = ProductMapper.toEntity(request);
        Product savedProduct = productRepository.save(product);

        return  ProductMapper.toResponse(savedProduct);

    }

    @Override
    public List<ProductResponse> getAllProducts() {

        List<Product> allProducts = productRepository.findAll();

        return allProducts.stream().map(ProductMapper::toResponse).toList() ;
    }

    @Override
    public ProductResponse getProductById(Long id) {

        Optional<Product> productById = productRepository.findById(id);

      return productById.stream().map(ProductMapper::toResponse).findAny()
              .orElseThrow(()-> new ProductNotFoundException("Product Not Available with id "+id) );
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {

        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty())
        {
            throw new ProductNotFoundException("Product Not Available with id "+id);
        }

        Product existingProduct = product.get();

        existingProduct.setName(request.getName());
        existingProduct.setCategory(request.getCategory());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setQuantity(request.getQuantity());

        productRepository.save(existingProduct);

        ProductResponse pr = new ProductResponse(existingProduct.getId(), existingProduct.getName(),
                existingProduct.getDescription(), existingProduct.getQuantity(), existingProduct.getPrice(),
                existingProduct.getCategory());



        return pr ;
    }

    @Override
    public void deleteProduct(Long id) {

        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent())
        {
            productRepository.deleteById(id);
        }
        else
            throw new ProductNotFoundException("Product Not Available with id "+id);


    }
}
