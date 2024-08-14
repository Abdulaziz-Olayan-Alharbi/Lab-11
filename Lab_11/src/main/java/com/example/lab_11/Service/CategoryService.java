package com.example.lab_11.Service;


import com.example.lab_11.Api.ApiException;
import com.example.lab_11.Model.Category;
import com.example.lab_11.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public void updateCategory(Integer id,Category category) {
        Category c = categoryRepository.findCategoriesById(id);
        if(c == null) {
            throw new ApiException("Category not found");
        }
        c.setName(category.getName());
        categoryRepository.save(c);
    }

    public void deleteCategory(Integer id) {
        Category c = categoryRepository.findCategoriesById(id);
        if(c == null) {
            throw new ApiException("Category not found");
        }
        categoryRepository.delete(c);
    }





















}
