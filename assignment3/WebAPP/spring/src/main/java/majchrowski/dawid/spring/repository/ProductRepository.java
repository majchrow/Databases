package majchrowski.dawid.spring.repository;

import majchrowski.dawid.spring.bean.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}