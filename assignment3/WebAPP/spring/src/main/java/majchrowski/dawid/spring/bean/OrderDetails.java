package majchrowski.dawid.spring.bean;

import javax.persistence.*;

@Entity
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private int quantity;

    public OrderDetails() {
    }

    public OrderDetails(Invoice invoice, Product product, int quantity) {
        this.invoice = invoice;
        this.product = product;
        this.quantity = quantity;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
