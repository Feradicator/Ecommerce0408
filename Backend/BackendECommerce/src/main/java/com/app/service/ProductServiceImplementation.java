package com.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Comparator;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
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
import org.springframework.cache.annotation.Cacheable;

@Service
@Transactional
public class ProductServiceImplementation implements ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Product createProduct(CreateProductRequest req) {
		Category topLavel=categoryRepository.findByName(req.getTopLavelCategory());
		if(topLavel==null) {
		
			Category topLavelCategory=new Category();
			topLavelCategory.setName(req.getTopLavelCategory());
			topLavelCategory.setLavel(1);
			topLavel=categoryRepository.save(topLavelCategory);
		}
		Category secondLavel=categoryRepository.
		findByNameAndParent(req.getSecondLavelCategory(),topLavel.getName());
		if(secondLavel==null) {
		Category secondLavelCategory=new Category();
		secondLavelCategory.setName(req.getSecondLavelCategory());
		secondLavelCategory.setParentCategory(topLavel);
		secondLavelCategory.setLavel(2);
		secondLavel=categoryRepository.save(secondLavelCategory);
		}
		Category thirdLavel=categoryRepository.
		findByNameAndParent(req.getThirdLavelCategory(),secondLavel.getName());
		if(thirdLavel==null) {
		Category thirdLavelCategory=new Category();
		thirdLavelCategory.setName(req.getThirdLavelCategory());
		thirdLavelCategory.setParentCategory(secondLavel);
		thirdLavelCategory.setLavel(3);
		thirdLavel=categoryRepository.save(thirdLavelCategory);
		}
		Product product =new Product();
		product.setTitle(req.getTitle()); product.setColor(req.getColor());
		product.setDescription(req.getDescription());
		product.setDiscountedPrice (req.getDiscountedPrice()); product.setDiscountPersent(req.getDiscountPersent());
		product.setImageUrl(req.getImageUrl());
		product.setBrand(req.getBrand());
		product.setPrice(req.getPrice());
		product.setSizes(req.getSize());
		product.setQuantity(req.getQuantity());
		product.setCategory(thirdLavel);
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
			Product product = opt.get();
			// Explicitly initialize the ratings collection within the same Hibernate session
			Hibernate.initialize(product.getRatings());
			return product;
		}

		throw new ProductException("Product not found with id "+id);
	}

	@Override
	public List<Product> findProductByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	
	public Page<Product> getAllProduct(
	        String category, List<String> colors, List<String> sizes, Integer minPrice,
	        Integer maxPrice, Integer minDiscount, String sort, String stock,
	        Integer pageNumber, Integer pageSize) {

	    System.out.println("🔥 DB Call Happened (NORMAL / NO LIMIT)");

	    Pageable pageable = PageRequest.of(pageNumber, pageSize);

	    // Fetch base filtered products from DB
	   List<String> colorFilter = 
	            (colors == null || colors.isEmpty()) ? null : colors;


	    // Fetch base filtered products from DB
	    List<Product> products = productRepository.filterProducts(
	    	    category, minPrice, maxPrice, minDiscount, colorFilter
	    	);

	    // manual sorting
	    if (sort.equals("price_low")) {
	        products.sort(Comparator.comparing(Product::getDiscountedPrice));
	    } else if (sort.equals("price_high")) {
	        products.sort(Comparator.comparing(Product::getDiscountedPrice).reversed());
	    } else {
	        products.sort(Comparator.comparing(Product::getCreatedAt).reversed());
	    }
	    // Apply color filter
	    if (!colors.isEmpty()) {
	        products = products.stream()
	            .filter(p -> colors.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor())))
	            .collect(Collectors.toList());
	    }

	    // Apply stock filter
	    if (stock != null) {
	        if (stock.equals("in_stock")) {
	            products = products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
	        } else if (stock.equals("out_of_stock")) {
	            products = products.stream().filter(p -> p.getQuantity() < 1).collect(Collectors.toList());
	        }
	    }

	    int startIndex = (int) pageable.getOffset();
	    if (startIndex >= products.size()) {
	        return new PageImpl<>(Collections.emptyList(), pageable, products.size());
	    }

	    int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());
	    List<Product> pageContent = products.subList(startIndex, endIndex);

	    return new PageImpl<>(pageContent, pageable, products.size());
	}

	@Override
	
	public Page<Product> getAllProductLimit(String category, List<String> colors, List<String> sizes, Integer minPrice,
			Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable=PageRequest.of(pageNumber, pageSize);
		Pageable limit10 = PageRequest.of(0, 10);

		List<Product>products=productRepository.filterProducts10(category, minPrice, maxPrice,minDiscount, sort,limit10);
		
		System.out.println(products);
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
			int startIndex = (int) pageable.getOffset();
if (startIndex >= products.size()) {
    return new PageImpl<>(Collections.emptyList(), pageable, products.size());
}

int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());
List<Product> pageContent = products.subList(startIndex, endIndex);

return new PageImpl<>(pageContent, pageable, products.size());
	

			
		
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	@Override
	public List<Product> searchProduct(String query) {
		List<Product> products=productRepository.searchProduct(query);
		return products;
	}
	@Override
	public List<Product> recentlyAddedProduct() {
		
		return productRepository.findTop10ByOrderByCreatedAtDesc();
	}

}
