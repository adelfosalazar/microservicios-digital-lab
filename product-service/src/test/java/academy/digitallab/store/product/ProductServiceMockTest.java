package academy.digitallab.store.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import academy.digitallab.store.product.entity.Category;
import academy.digitallab.store.product.entity.Product;
import academy.digitallab.store.product.repository.ProductRepository;
import academy.digitallab.store.product.service.ProductService;
import academy.digitallab.store.product.service.ProductServiceImpl;

@SpringBootTest
public class ProductServiceMockTest {

	private ProductService productService;
	
	@Mock
	private ProductRepository productRepository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		productService = new ProductServiceImpl(productRepository);
		
		Product product = Product.builder()
				.id(1L)
				.description("dell")
				.name("compu")
				.stock(5d)
				.category(Category.builder().id(1L).build())
				.price(1000d)
				.build();
		
		Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));
		
		Mockito.when(productRepository.save(product)).thenReturn(product);
	}
	
	@Test
	public void getProduct() {
		Optional<Product> product = Optional.ofNullable(productService.getProduct(1L));
		assertEquals("compu", product.get().getName());		
	}
	
	@Test
	public void updateStockProduct() {
		Product product = productService.updateStock(1L, 8d);
		
		assertEquals(13, product.getStock());
	}
}
