package com.example.ecommerce.product;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductAssembler productAssembler;

    public ProductController(ProductRepository productRepository, ProductAssembler productAssembler) {
        this.productRepository = productRepository;
        this.productAssembler = productAssembler;
    }


    @GetMapping("/products")
    CollectionModel<EntityModel<ProductView>> findAll() {
        List<EntityModel<ProductView>> products = productRepository.findAll()
                .stream()
                .map(productAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(products);
    }

    @GetMapping("/products/{id}")
    EntityModel<ProductView> findOne(@PathVariable Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return productAssembler.toModel(product);
    }

    @PostMapping("/products")
    ResponseEntity<EntityModel<ProductView>> saveProduct(@RequestBody Product newProduct){

        newProduct.setCreatedAt(LocalDateTime.now());
        Product savedProduct = productRepository.save(newProduct);

        return ResponseEntity
                .created(linkTo(methodOn(ProductController.class).findOne(savedProduct.getId())).toUri())
                .body(productAssembler.toModel(savedProduct));
    }

}
