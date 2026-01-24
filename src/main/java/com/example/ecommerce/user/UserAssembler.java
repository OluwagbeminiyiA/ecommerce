package com.example.ecommerce.user;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserAssembler implements RepresentationModelAssembler<User, EntityModel<UserView>> {
    @Override
    public EntityModel<UserView> toModel(User user) {

        UserView userView = new UserView();

        userView.setId(user.getId());
        userView.setName(user.getName());
        userView.setEmail(user.getEmail());
        userView.setRole(user.getRole());
        userView.setCreatedAt(user.getCreatedAt());

        return EntityModel.of(userView,
                linkTo(methodOn(UserController.class).findOne(user.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).findAll()).withRel("Users"));
    }
}
