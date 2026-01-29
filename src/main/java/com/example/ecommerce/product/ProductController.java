package com.example.ecommerce.product;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
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
    private final PagedResourcesAssembler<ProductView> pagedResourceAssembler;

    public ProductController(ProductRepository productRepository, ProductAssembler productAssembler, PagedResourcesAssembler<ProductView> pagedResourceAssembler) {
        this.productRepository = productRepository;
        this.productAssembler = productAssembler;
        this.pagedResourceAssembler = pagedResourceAssembler;
    }



    @GetMapping("/products")
    PagedModel<EntityModel<ProductView>> findAll(Pageable pageable){
        Page<Product> page = productRepository.findAll(pageable);

        Page<ProductView> productViewPage = page.map(
                ProductView::new
        );

        return pagedResourceAssembler
                .toModel(productViewPage);
    }

    @GetMapping("/products/{id}")
    EntityModel<ProductView> findOne(@PathVariable Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return productAssembler.toModel(product);
    }

    @Transactional
    @PostMapping("/products")
    ResponseEntity<EntityModel<ProductView>> saveProduct(@RequestBody Product newProduct){

        newProduct.setCreatedAt(LocalDateTime.now());
        newProduct.setUpdatedAt(LocalDateTime.now());
        Product savedProduct = productRepository.save(newProduct);

        return ResponseEntity
                .created(linkTo(methodOn(ProductController.class).findOne(savedProduct.getId())).toUri())
                .body(productAssembler.toModel(savedProduct));
    }

    @Transactional
    @PatchMapping("/products/{id}")
    ResponseEntity<?> update(@RequestBody Product newProduct, @PathVariable Long id) {
        Product oldProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        if (newProduct.getName() != null && !newProduct.getName().isBlank()) {
            oldProduct.setName(newProduct.getName());
            oldProduct.setUpdatedAt(LocalDateTime.now());
        }

        if (newProduct.getCategory() != null && !newProduct.getCategory().isBlank()) {
            oldProduct.setCategory(newProduct.getCategory());
            oldProduct.setUpdatedAt(LocalDateTime.now());

        }

        if (newProduct.getPrice() != null) {
            oldProduct.setPrice(newProduct.getPrice());
            oldProduct.setUpdatedAt(LocalDateTime.now());

        }

        if (newProduct.getStockQuantity() != null) {
            oldProduct.setStockQuantity(newProduct.getStockQuantity());
            oldProduct.setUpdatedAt(LocalDateTime.now());

        }

        if (newProduct.getDescription() != null && !newProduct.getDescription().isBlank()){
            oldProduct.setDescription(newProduct.getDescription());
            oldProduct.setUpdatedAt(LocalDateTime.now());
        }

        Product saved = productRepository.save(oldProduct);

        return ResponseEntity
                .ok(saved);
    }

    @DeleteMapping("/products/{id}")
    ResponseEntity<?> delete(@PathVariable Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        productRepository.delete(product);

        return ResponseEntity.noContent().build();
    }


}
