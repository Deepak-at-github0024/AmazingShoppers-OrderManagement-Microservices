package com.example.product_service.Controller;

import com.example.product_service.DTO.ProductRequest;
import com.example.product_service.DTO.ProductResponse;
import com.example.product_service.Service.Impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductServiceImpl productService ;

    @GetMapping("/test")
    public String test() {
        return "Product Service is running";
    }

    @PostMapping("/createProduct")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest)
    {
        ProductResponse response =   productService.createProduct(productRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/allProducts")
    public ResponseEntity<List<ProductResponse>> getAllProducts()
    {
       List<ProductResponse> productResponse =  productService.getAllProducts();

        return  ResponseEntity.ok(productResponse);
    }

   @GetMapping("/byId/{id}")
    public ResponseEntity<ProductResponse> productsById(@PathVariable Long id)
   {
       ProductResponse byId = productService.getProductById(id);
       return  ResponseEntity.ok(byId);
   }

   @PutMapping("/updateProducById/{id}")
    public ResponseEntity<ProductResponse> updateProductByID(@PathVariable Long id , @RequestBody ProductRequest pr)
   {
       ProductResponse prd = productService.updateProduct(id,pr) ;

       return  ResponseEntity.ok(prd);
   }

 @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteProductById (@PathVariable Long id)
 {
     productService.deleteProduct(id);

     return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
 }

}