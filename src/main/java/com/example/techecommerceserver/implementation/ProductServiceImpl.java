package com.example.techecommerceserver.implementation;

import com.example.techecommerceserver.dto.ProductDto;
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
	public Product updateProduct(Integer id, ProductDto productDto) throws ProductException {
//		Optional<Product> opt = pRepo.findById(id);
//		if (opt.isPresent()) {
//			return pRepo.save(product);
//
//		} else {
//			throw new ProductException("Product not updated");
//		}
		Product opt = pRepo.findById(id).orElseThrow(() -> new ProductException("For id " +id));
		Product product1 = mapFromDtoToProduct(productDto, opt);
		pRepo.save(product1);
		return product1;
	}

	@Override
	public Product viewProduct(Integer productId) throws ProductException {
//		Optional<Product> opt = pRepo.findById(productId);
//		if (opt.isPresent()) {
//			return opt.get();
//		} else {
//			throw new ProductException("Product not found with product id - " + productId);
//		}
		Product opt = pRepo.findById(productId).orElseThrow(() -> new ProductException("For id " +productId));
		return opt;

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

	private Product mapFromDtoToProduct(ProductDto productDto, Product product) {
		product.setProductName(productDto.getProductName());
		product.setImage(productDto.getImage());
		product.setColor(productDto.getColor());
		product.setDescription(productDto.getDescription());
		product.setManufacturer(productDto.getManufacturer());
//		product.setSize(productDto.getSize());
		product.setPrice(productDto.getPrice());
		product.setQuantity(productDto.getQuantity());
		product.setNumberSell(productDto.getNumberSell());
		product.setCategory(cRepo.getOne(productDto.getCategoryId()));
		return product;
	}

}
