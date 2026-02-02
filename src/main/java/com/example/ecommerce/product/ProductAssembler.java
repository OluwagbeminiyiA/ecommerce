package com.example.ecommerce.product;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.awt.print.Pageable;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductAssembler implements RepresentationModelAssembler<Product, EntityModel<ProductView>> {

    @Override
    public EntityModel<ProductView> toModel(Product entity) {
        ProductView productView = new ProductView(entity);


        return EntityModel.of(productView,
                linkTo(methodOn(ProductController.class).findOne(productView.getId())).withSelfRel(),
                linkTo(methodOn(ProductController.class).findAll(PageRequest.of(0, 20))).withRel("products"));
    }
}
