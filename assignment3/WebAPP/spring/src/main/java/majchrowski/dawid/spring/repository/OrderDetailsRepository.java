package majchrowski.dawid.spring.repository;

import majchrowski.dawid.spring.bean.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

}