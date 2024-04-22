package com.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.exception.ProductException;
import com.app.model.Category;
import com.app.model.Product;
import com.app.repository.CategoryRepository;
import com.app.repository.ProductRepository;
import com.app.request.CreateProductRequest;
@Service
public class ProductServiceImplementation implements ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Product createProduct(CreateProductRequest req) {
		Category topLevel=categoryRepository.findByName(req.getTopLevelCategory());
		if(topLevel==null) {
		
			Category topLevelCategory=new Category();
			topLevelCategory.setName(req.getTopLevelCategory());
			topLevelCategory.setLevel(1);
			topLevel=categoryRepository.save(topLevelCategory);
		}
		Category secondLevel=categoryRepository.
		findByNameAndParent(req.getSecondLevelCategory(),topLevel.getName());
		if(secondLevel==null) {
		Category secondLavelCategory=new Category();
		secondLavelCategory.setName(req.getSecondLevelCategory());
		secondLavelCategory.setParentCategory(topLevel);
		secondLavelCategory.setLevel(2);
		secondLevel=categoryRepository.save(secondLavelCategory);
		}
		Category thirdLevel=categoryRepository.
		findByNameAndParent(req.getThirdLevelCategory(),secondLevel.getName());
		if(thirdLevel==null) {
		Category thirdLevelCategory=new Category();
		thirdLevelCategory.setName(req.getThirdLevelCategory());
		thirdLevelCategory.setParentCategory(secondLevel);
		thirdLevelCategory.setLevel(3);
		thirdLevel=categoryRepository.save(thirdLevelCategory);
		}
		Product product =new Product(); product.setTitle(req.getTitle()); product.setColor(req.getColor());
		product.setDescription(req.getDescription());
		product.setDiscountedPrice (req.getDiscountedPrice()); product.setDiscountPersent(req.getDiscountPersent());
		product.setImageUrl(req.getImageUrl());
		product.setBrand(req.getBrand());
		product.setPrice(req.getPrice());
		product.setSizes(req.getSize());
		product.setQuantity(req.getQuantity());
		product.setCategory(thirdLevel);
		product.setCreatedAt(LocalDateTime.now());
		Product savedProduct =productRepository.save(product);



		// TODO Auto-generated method stub
		return savedProduct;
	}

	@Override
	public String deleteProduct(Long productid) throws ProductException {
		// TODO Auto-generated method stub
		Product product=findProductById(productid);
		product.getSizes().clear();
		productRepository.delete(product);
		return "Product deleted Successfully";
		
	}

	@Override
	public Product updateProduct(Long productid, Product product1) throws ProductException {
		// TODO Auto-generated method stub
		Product product=findProductById(productid);
		if(product1.getQuantity()!=0)
		{
			product.setQuantity(product1.getQuantity());
		}
		return productRepository.save(product);
	}

	@Override
	public Product findProductById(Long id) throws ProductException {
		// TODO Auto-generated method stub
		Optional<Product>opt=productRepository.findById(id);
		if(opt.isPresent())
		{
			return opt.get();
		}

		throw new ProductException("Product not found with id "+id);
	}

	@Override
	public List<Product> findProductByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice,
			Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable=PageRequest.of(pageNumber, pageSize);
		List<Product>products=productRepository.filterProducts(category, minPrice, minDiscount, maxPrice, sort);
		if(!colors.isEmpty())
		{
			products=products.stream().filter(p->colors.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor()))).collect(Collectors.toList());
		}
		if(stock!=null) {
			if(stock.equals("in_stock")){
			products=products.stream().filter(p -> p.getQuantity()>0).collect(Collectors.toList());
			}
			
			else if (stock.equals("out_of_stock")) {
			products=products.stream().filter(p -> p.getQuantity()<1).collect(Collectors.toList());
			}
			}
			int startIndex=(int)pageable.getOffset();
			int endIndex=Math.min(startIndex + pageable.getPageSize(), products.size());
			List<Product> pageContent=products.subList(startIndex, endIndex);
			Page<Product> filteredProducts=new PageImpl<>(pageContent,pageable,products.size());
			return filteredProducts;

			
		
	}

	@Override
	public List<Product> findAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

}
