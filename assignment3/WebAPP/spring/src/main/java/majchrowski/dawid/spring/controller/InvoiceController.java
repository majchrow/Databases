package majchrowski.dawid.spring.controller;

import majchrowski.dawid.spring.bean.Invoice;
import majchrowski.dawid.spring.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    InvoiceRepository invoiceRepository;

    @CrossOrigin
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/orders",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Invoice> getAllPersons() {
        return invoiceRepository.findAll();
    }

    @CrossOrigin
    @RequestMapping(
            method = RequestMethod.POST,
            path = "/orders",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Invoice addPerson(@RequestBody Invoice invoice) {
        invoice.calculateQuantity();
        invoice.calculatePrice();
        return invoiceRepository.save(invoice);
    }

}