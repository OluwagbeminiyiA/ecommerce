package com.example.ecommerce.product;

import com.example.ecommerce.category.Category;
import com.example.ecommerce.category.CategoryRepository;
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
    private final CategoryRepository categoryRepository;

    public ProductController(ProductRepository productRepository, ProductAssembler productAssembler, PagedResourcesAssembler<ProductView> pagedResourceAssembler, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productAssembler = productAssembler;
        this.pagedResourceAssembler = pagedResourceAssembler;
        this.categoryRepository = categoryRepository;
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
    ResponseEntity<EntityModel<ProductView>> saveProduct(@RequestBody ProductCreateRequest newProductRequest){

        Product product = new Product(
                newProductRequest.getName(),
                newProductRequest.getDescription(),
                newProductRequest.getPrice(),
                newProductRequest.getStockQuantity(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        for(String str: newProductRequest.getCategory()){
            Category category = categoryRepository.findByName(str).orElseGet(()-> categoryRepository.save(new Category(str)));

            ProductCategory productCategory = new ProductCategory(product, category);

            product.getCategories().add(productCategory);
            category.getProducts().add(productCategory);
        }

        Product savedProduct = productRepository.save(product);

        return ResponseEntity
                .created(linkTo(methodOn(ProductController.class).findOne(savedProduct.getId())).toUri())
                .body(productAssembler.toModel(savedProduct));
    }

    @Transactional
    @RequestMapping(value = "/products/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    ResponseEntity<?> update(@RequestBody Product newProduct, @PathVariable Long id) {
        Product oldProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        if (newProduct.getName() != null && !newProduct.getName().isBlank()) {
            oldProduct.setName(newProduct.getName());
            oldProduct.setUpdatedAt(LocalDateTime.now());
        }

        if (newProduct.getCategories() != null && !newProduct.getCategories().isEmpty()) {
            oldProduct.getCategories().addAll(newProduct.getCategories());
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
