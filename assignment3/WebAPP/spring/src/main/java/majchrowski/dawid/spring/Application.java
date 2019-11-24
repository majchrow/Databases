package majchrowski.dawid.spring;

import majchrowski.dawid.spring.bean.Product;
import majchrowski.dawid.spring.repository.ProductRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {
		return () -> {
			productRepository.save(new Product("Product1", 1, 23.5));
			productRepository.save(new Product("Product2", 15, 1.));
			productRepository.save(new Product("Product3", 0, 1500.));
			productRepository.save(new Product("Product4", 5, 325.));
			productRepository.save(new Product("Product5", 3, 11.));
			productRepository.save(new Product("Product6", 100, 25.));

		};
	}

}
