package com.example.ecommerce.product;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductAssembler implements RepresentationModelAssembler<Product, EntityModel<ProductView>> {

    @Override
    public EntityModel<ProductView> toModel(Product entity) {
        ProductView productView = new ProductView();

        productView.setId(entity.getId());
        productView.setCreatedAt(entity.getCreatedAt());
        productView.setDescription(entity.getDescription());
        productView.setName(entity.getName());
        productView.setPrice(entity.getPrice());
        productView.setStockQuantity(entity.getStockQuantity());
        productView.setUpdatedAt(entity.getUpdatedAt());

        return EntityModel.of(productView,
                linkTo(methodOn(ProductController.class).findOne(productView.getId())).withSelfRel(),
                linkTo(methodOn(ProductController.class).findAll()).withRel("products"));
    }
}
