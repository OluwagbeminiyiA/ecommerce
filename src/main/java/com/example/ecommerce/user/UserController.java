package com.example.ecommerce.user;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    private final UserRepository userRepository;
    private final UserAssembler assembler;

    public UserController(UserRepository userRepository, UserAssembler assembler) {
        this.userRepository = userRepository;
        this.assembler = assembler;
    }

    @GetMapping("/users")
    CollectionModel<EntityModel<User>> findAll() {
        List<EntityModel<User>> users = userRepository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(users);
    }

    @GetMapping("/users/{id}")
    EntityModel<User> findOne(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return assembler.toModel(user);
    }

    @PostMapping("/users")
    ResponseEntity<EntityModel<User>> save(@RequestBody User newUser) {
        User savedUser = userRepository.save(newUser);

        return ResponseEntity
                .created(linkTo(methodOn(UserController.class)).toUri())
                .body(assembler.toModel(savedUser));
    }


}
