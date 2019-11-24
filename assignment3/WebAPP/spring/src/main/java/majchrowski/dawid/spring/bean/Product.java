package majchrowski.dawid.spring.bean;

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
    private Double unitPrice;

    @ManyToMany(mappedBy = "products")
    private List<Invoice> invoices = new ArrayList<>();

    public Product(String productName, Integer unitsOnStock, Double unitPrice) {
        this.productName = productName;
        this.unitsOnStock = unitsOnStock;
        this.unitPrice = unitPrice;
    }

    public Product() {
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void addInvoice(Invoice invoice){
        invoice.addProduct(this);
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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
