package com.example.ecommerce.category;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/categories")
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @GetMapping("/categories/{id}")
    public Category findById(@PathVariable Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @PostMapping("/categories")
    public Category save(@RequestBody Category category) {
        return categoryRepository.save(category);
    }
}
