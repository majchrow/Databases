import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
public class Customer extends Company{
    private float discount;

    public Customer() {
        super();
    }
    public Customer(float discount, String companyName, String street, String city, String zipCode) {
        super(companyName, street, city, zipCode);
        this.discount = discount;
    }

}
