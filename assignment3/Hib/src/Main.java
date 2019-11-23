import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import javax.persistence.TypedQuery;

public class Main {
    private static SessionFactory sessionFactory = null;

    public static void main(String[] args) {
        Product product1 = new Product("Prod1", 10);
        Product product2 = new Product("Prod2", 0);
        Product product3 = new Product("Prod3", 5);
//        Supplier supplier = new Supplier("AGH2",
//                "Czarnowiejska2", "Krakow2 ");
        Category category1 = new Category("Cat1");
        Category category2 = new Category("Cat2");
        Invoice invoice1 = new Invoice(1);
        Invoice invoice2 = new Invoice(2);

        sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(product1);
        session.save(product2);
        session.save(product3);
//        session.save(supplier);
        session.save(category1);
        session.save(category2);
        session.save(invoice1);
        session.save(invoice2);
//        supplier.addProduct(product1);
//        supplier.addProduct(product2);
//        supplier.addProduct(product3);
        product1.addCategory(category1);
        product2.addCategory(category2);
        product3.addCategory(category1);
        invoice1.addProduct(product1);
        invoice1.addProduct(product3);
        invoice2.addProduct(product2);
        invoice2.addProduct(product3);
        tx.commit();
        session.close();

        TypedQuery<Product> prodByInv = session.createQuery("from Product as product" +
                " where :invoice member of product.invoices", Product.class);
        prodByInv.setParameter("invoice", invoice1);
        for (Product product: prodByInv.getResultList()){
            System.out.println(product.getProductName());
        }

        TypedQuery<Invoice> invByProd = session.createQuery("from Invoice as invoice" +
                " where :product member of invoice.products", Invoice.class);
        invByProd.setParameter("product", product3);
        for (Invoice invoice: invByProd.getResultList()){
            System.out.println(invoice.getInvoiceNumber());
        }


//        TypedQuery<Product> prodByCat = session.createQuery("from Product as product" + " where lower(product.category.name)=:categoryName", Product.class);
//        prodByCat.setParameter("categoryName", "cat1");
//        for (Product product: prodByCat.getResultList()){
//            System.out.println(product.getProductName());
//        }
//        TypedQuery<Category> catByProd = session.createQuery("from Category as category" + " where :product member of category.products", Category.class);
//        catByProd.setParameter("product", product1);
//        for (Category category: catByProd.getResultList()){
//            System.out.println(category.getName());
//        }

        session.close();
    }
    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            sessionFactory = configuration.configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}



