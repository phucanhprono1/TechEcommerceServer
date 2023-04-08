package com.example.techecommerceserver.implementation;

import com.example.techecommerceserver.dto.CategoryDto;
import com.example.techecommerceserver.exception.CategoryException;
import com.example.techecommerceserver.exception.ProductException;
import com.example.techecommerceserver.model.Category;
import com.example.techecommerceserver.model.Product;
import com.example.techecommerceserver.repository.CategoryRepo;
import com.example.techecommerceserver.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.catalog.CatalogException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    public List<Category> getAllCategory() {
        List <Category> listCategory = categoryRepo.findAll();
        return listCategory;
    }
    @Override
    public Category addCategory(Category category) throws CategoryException {
        Optional<Category> cate = categoryRepo.findById(category.getCategoryId());
        if (cate.isEmpty()) {
            Category category1 = categoryRepo.save(category);
            if (category1 != null) {
                return category1;
            } else {
                throw new CategoryException("Category not added");
            }
        }
        else {
            throw new CategoryException("Category existed");
        }


    }
    @Override
    public Category updateCategory(Integer id, CategoryDto categoryDto) throws CategoryException{
        Category opt = categoryRepo.findById(id).orElseThrow(() -> new CategoryException("For id " +id));
        Category category = mapFromDtoToCategory(categoryDto, opt);
        categoryRepo.save(category);
        return category;
    }

    @Override
    public Category removeCategory(Integer categoryId) throws CategoryException {
        Category p = categoryRepo.findById(categoryId).orElseThrow(() -> new CategoryException("Category not found"));
        categoryRepo.delete(p);
        return p;

    }

    private Category mapFromDtoToCategory(CategoryDto categoryDto, Category category) {
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(categoryDto.getCategoryName());
        return category;
    }
}
