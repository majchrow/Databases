package majchrowski.dawid.spring.bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int invoiceID;

    private int invoiceNumber;
    private int quantity = 0;
    private double orderPrice = 0;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Product> products= new ArrayList<>();

    public Invoice(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Invoice() {
    }

    public void addProduct(Product product){
        if(product.getUnitsOnStock() > 0){
            products.add(product);
            product.getInvoices().add(this);
            product.setUnitsOnStock(product.getUnitsOnStock()-1);
            this.quantity++;
        }
    }

    public void calculatePrice(){
        this.orderPrice = this.products.stream().map(Product::getUnitPrice).
                reduce(0., Double::sum);
    }

    public void calculateQuantity(){
        this.quantity = this.products.size();
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Product> getProducts() {
        return products;
    }

}
