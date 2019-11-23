package majchrowski.dawid.spring.controller;

import majchrowski.dawid.spring.bean.Product;
import majchrowski.dawid.spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public List<Product> getAllPersons(){
        return productRepository.findAll();
    }

    @CrossOrigin
    @RequestMapping(
            method = RequestMethod.POST,
            path = "/products",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Product addPerson(@RequestBody Product product) {
        return productRepository.save(product);
    }

}