package com.example.techecommerceserver.controller;

import com.example.techecommerceserver.dto.CategoryDto;
import com.example.techecommerceserver.dto.ProductDto;
import com.example.techecommerceserver.exception.CategoryException;
import com.example.techecommerceserver.exception.ProductException;
import com.example.techecommerceserver.model.Category;
import com.example.techecommerceserver.model.Product;
import com.example.techecommerceserver.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Category>> getAllCategory() throws CategoryException {
        return new ResponseEntity<List<Category>>(categoryService.getAllCategory(), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) throws CategoryException {
        Category category1 = categoryService.addCategory(category);
        return new ResponseEntity<Category>(category1, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Integer id, @RequestBody CategoryDto categoryDto) throws CategoryException {
        categoryService.updateCategory(id,categoryDto);
        return new ResponseEntity<Category>(HttpStatus.OK);
    }
    @DeleteMapping("/remove/{categoryId}")
    public ResponseEntity<Category> removeCategoryById(@PathVariable Integer categoryId)
            throws CategoryException {
        return new ResponseEntity<Category>(categoryService.removeCategory(categoryId), HttpStatus.OK);
    }
}
