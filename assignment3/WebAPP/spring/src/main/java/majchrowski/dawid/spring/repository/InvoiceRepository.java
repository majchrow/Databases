package majchrowski.dawid.spring.repository;

import majchrowski.dawid.spring.bean.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

}