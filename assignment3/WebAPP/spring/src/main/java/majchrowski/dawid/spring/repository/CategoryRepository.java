package majchrowski.dawid.spring.repository;

import majchrowski.dawid.spring.bean.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {



}