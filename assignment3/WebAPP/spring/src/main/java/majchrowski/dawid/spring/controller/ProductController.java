package majchrowski.dawid.spring.controller;

import majchrowski.dawid.spring.bean.Product;
import majchrowski.dawid.spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @CrossOrigin
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/products",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}