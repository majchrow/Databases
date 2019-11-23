import javax.persistence.Embeddable;

@Embeddable
public class Adress {
    private String street;
    private String city;

    public Adress() {
    }

    public Adress(String street, String city) {
        this.street = street;
        this.city = city;
    }
}
