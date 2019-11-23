import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productID;

    private String productName;
    private Integer unitsOnStock;

    @ManyToOne
    @JoinColumn(name = "CategoryFK")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "SupplierFK")
    private Supplier supplier;

    @ManyToMany(mappedBy = "products", cascade = {CascadeType.PERSIST})
    private List<Invoice> invoices = new ArrayList<>();

    public Product(String productName, Integer unitsOnStock) {
        this.productName = productName;
        this.unitsOnStock = unitsOnStock;
    }


    public Product() {
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void addInvoice(Invoice invoice){
        invoice.addProduct(this);
    }

    public void addCategory(Category category){
        category.getProducts().add(this);
        this.category = category;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setUnitsOnStock(Integer unitsOnStock) {
        this.unitsOnStock = unitsOnStock;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getUnitsOnStock() {
        return unitsOnStock;
    }

    public void addSupplier(Supplier supplier){
        supplier.getProducts().add(this);
        this.supplier = supplier;
    }

}
