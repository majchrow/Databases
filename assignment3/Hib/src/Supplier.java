import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Supplier extends Company {
    private String bankAccountNumber;

    @OneToMany(mappedBy = "supplier")
    private Set<Product> products = new HashSet<>();

    public Supplier() {
        super();
    }
    public Supplier(String bankAccountNumber, String companyName, String street, String city, String zipCode) {
        super(companyName, street, city, zipCode);
        this.bankAccountNumber = bankAccountNumber;
    }

    public void addProduct(Product product){
        this.products.add(product);
        product.addSupplier(this);
    }

    public Set<Product> getProducts() {
        return products;
    }
}
