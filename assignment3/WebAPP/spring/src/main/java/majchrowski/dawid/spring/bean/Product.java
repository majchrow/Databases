package majchrowski.dawid.spring.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productID;

    private String productName;
    private Integer unitsOnStock;
    private Double unitPrice;

    public Product(String productName, Integer unitsOnStock, Double unitPrice) {
        this.productName = productName;
        this.unitsOnStock = unitsOnStock;
        this.unitPrice = unitPrice;
    }

    public Product() {
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
