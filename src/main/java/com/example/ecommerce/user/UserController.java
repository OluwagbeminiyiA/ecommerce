package com.example.ecommerce.user;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    private final UserRepository userRepository;
    private final UserAssembler assembler;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, UserAssembler assembler, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.assembler = assembler;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/users")
    CollectionModel<EntityModel<UserView>> findAll() {
        List<EntityModel<UserView>> users = userRepository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(users);
    }

    @GetMapping("/users/{id}")
    EntityModel<UserView> findOne(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return assembler.toModel(user);
    }

    @PostMapping("/users")
        ResponseEntity<EntityModel<UserView>> save(@RequestBody User newUser) {
        if (newUser.getPassword() == null || newUser.getPassword().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        String password = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(password);
        User savedUser = userRepository.save(newUser);

        return ResponseEntity
                .created(linkTo(methodOn(UserController.class)).toUri())
                .body(assembler.toModel(savedUser));
    }


}
