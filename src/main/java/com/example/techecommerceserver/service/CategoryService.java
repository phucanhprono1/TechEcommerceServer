package com.example.techecommerceserver.service;

import com.example.techecommerceserver.dto.CategoryDto;
import com.example.techecommerceserver.dto.ProductDto;
import com.example.techecommerceserver.exception.CategoryException;
import com.example.techecommerceserver.model.Category;
import com.example.techecommerceserver.model.Product;

import java.util.List;

public interface CategoryService {
    public Category removeCategory(Integer categoryId) throws  CategoryException;

    public List<Category> getAllCategory() throws CategoryException;

    public Category addCategory(Category category) throws CategoryException;

    public Category updateCategory(Integer id, CategoryDto categoryDto) throws CategoryException;
}
