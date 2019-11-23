import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int SupplierID;

    private String companyName;

    @Embedded
    private Adress adress;

    @OneToMany(mappedBy = "supplier")
    private Set<Product> products = new HashSet<>();

    public Supplier() {
    }

    public Supplier(String companyName, Adress adress) {
        this.companyName = companyName;
        this.adress = adress;
    }

    public int getSupplierID() {
        return SupplierID;
    }

    public String getCompanyName() {
        return companyName;
    }


    public Set<Product> getProducts() {
        return products;
    }

    public void setSupplierID(int supplierID) {
        SupplierID = supplierID;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product){
        this.products.add(product);
        product.addSupplier(this);
    }

}
