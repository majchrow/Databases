package majchrowski.dawid.spring.controller;

import majchrowski.dawid.spring.bean.Invoice;
import majchrowski.dawid.spring.bean.OrderDetails;
import majchrowski.dawid.spring.bean.Product;
import majchrowski.dawid.spring.classes.ProductOrder;
import majchrowski.dawid.spring.repository.InvoiceRepository;
import majchrowski.dawid.spring.repository.OrderDetailsRepository;
import majchrowski.dawid.spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class InvoiceController {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    ProductRepository productRepository;

    @CrossOrigin
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/orders",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @CrossOrigin
    @RequestMapping(
            method = RequestMethod.POST,
            path = "/orders",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Invoice addInvoice(@RequestBody List<ProductOrder> productOrders) {
        Invoice invoice = invoiceRepository.save(new Invoice());
        for (ProductOrder productOrder : productOrders) {
            int quantity = productOrder.getQuantity();
            Product product = productRepository.getOne(productOrder.getProductID());
            product.setUnitsOnStock(product.getUnitsOnStock() - quantity);
            OrderDetails orderDetails = new OrderDetails(invoice, product, quantity);
            orderDetailsRepository.save(orderDetails);
        }
        return invoice;
    }

}