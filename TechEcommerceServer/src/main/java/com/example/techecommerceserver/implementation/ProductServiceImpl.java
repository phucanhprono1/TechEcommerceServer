package com.example.techecommerceserver.implementation;

import com.example.techecommerceserver.exception.ProductException;
import com.example.techecommerceserver.model.Category;
import com.example.techecommerceserver.model.Product;
import com.example.techecommerceserver.repository.CategoryRepo;
import com.example.techecommerceserver.repository.ProductRepo;
import com.example.techecommerceserver.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepo pRepo;

	@Autowired
	private CategoryRepo cRepo;

	@Override
	public List<Product> viewAllProduct() throws ProductException {
		List<Product> products = pRepo.findAll();
		if (products.size() > 0) {
			return products;
		} else {
			throw new ProductException("Products not found");
		}
	}

	@Override
	public Product addProduct(Product product) throws ProductException {
		Product pro = pRepo.save(product);
		if (pro != null) {
			return pro;
		} else {
			throw new ProductException("Product not added");
		}

	}

	@Override
	public Product updateProduct(Product product) throws ProductException {
		Optional<Product> opt = pRepo.findById(product.getProductId());
		if (opt.isPresent()) {
			return pRepo.save(product);

		} else {
			throw new ProductException("Product not updated");
		}

	}

	@Override
	public Product viewProduct(Integer productId) throws ProductException {
		Optional<Product> opt = pRepo.findById(productId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new ProductException("Product not found with product id - " + productId);
		}
	}

	@Override
	public List<Product> viewProductByCategory(Integer categoryId) throws ProductException {
		Optional<Category> category = cRepo.findById(categoryId);
		if (category.isPresent()) {
			return category.get().getProductList();
		} else {
			throw new ProductException("Product not found with category id - " + categoryId);
		}

	}

	@Override
	public Product removeProduct(Integer productId) throws ProductException {
		Product p = pRepo.findById(productId).orElseThrow(() -> new ProductException("Product not found"));
		pRepo.delete(p);
		return p;

	}

}
