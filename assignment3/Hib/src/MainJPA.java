import javax.persistence.*;

public class MainJPA{

    public static void testCompany(){
        Supplier supplier1 = new Supplier("bankAcc1", "AGH1",
                "Czarnowiejska1", "Krakow1", "30-049");
        Supplier supplier2 = new Supplier("bankAcc2", "AGH2",
                "Czarnowiejska2", "Krakow2", "30-049");
        Customer customer1 = new Customer(0, "AGH2",
                "Czarnowiejska2", "Krakow2", "30-049");
        Customer customer2 = new Customer(0, "AGH2",
                "Czarnowiejska2", "Krakow2", "30-049");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_DB");
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        em.persist(supplier1);
        em.persist(supplier2);
        em.persist(customer1);
        em.persist(customer2);
        etx.commit();
//        TypedQuery<Supplier> supplierQuery = em.createQuery("from Supplier as supplier" +
//            " where lower(supplier.class)=:supplierClass", Supplier.class);
//        supplierQuery.setParameter("supplierClass", "s");
//        TypedQuery<Customer> customerQuery = em.createQuery("from Customer as customer" +
//                " where lower(customer.class)=:customerClass", Customer.class);
//        supplierQuery.setParameter("supplierClass", "s");
//        customerQuery.setParameter("customerClass", "c");
//        for (Supplier supplier: supplierQuery.getResultList()){
//            System.out.println(supplier);
//        }
//        for (Customer customer: customerQuery.getResultList()){
//            System.out.println(customer);
//        }

        em.close();
    }

    public static void testCompany2(){
        Supplier supplier1 = new Supplier("bankAcc1", "AGH1",
                "Czarnowiejska1", "Krakow1", "30-049");
        Supplier supplier2 = new Supplier("bankAcc2", "AGH2",
                "Czarnowiejska2", "Krakow2", "30-049");
        Customer customer1 = new Customer(0, "AGH2",
                "Czarnowiejska2", "Krakow2", "30-049");
        Customer customer2 = new Customer(0, "AGH2",
                "Czarnowiejska2", "Krakow2", "30-049");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_DB");
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        em.persist(supplier1);
        em.persist(supplier2);
        em.persist(customer1);
        em.persist(customer2);
        etx.commit();
        TypedQuery<Supplier> supplierQuery = em.createQuery("from Supplier as supplier", Supplier.class);
        TypedQuery<Customer> customerQuery = em.createQuery("from Customer as customer", Customer.class);
        for (Supplier supplier: supplierQuery.getResultList()){
            System.out.println(supplier);
        }
        for (Customer customer: customerQuery.getResultList()){
            System.out.println(customer);
        }

        em.close();
    }

    public static void testCompany3(){
        Supplier supplier1 = new Supplier("bankAcc1", "AGH1",
                "Czarnowiejska1", "Krakow1", "30-049");
        Supplier supplier2 = new Supplier("bankAcc2", "AGH2",
                "Czarnowiejska2", "Krakow2", "30-049");
        Customer customer1 = new Customer(0, "AGH2",
                "Czarnowiejska2", "Krakow2", "30-049");
        Customer customer2 = new Customer(0, "AGH2",
                "Czarnowiejska2", "Krakow2", "30-049");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_DB");
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        em.persist(supplier1);
        em.persist(supplier2);
        em.persist(customer1);
        em.persist(customer2);
        etx.commit();
//        TypedQuery<Supplier> supplierQuery = em.createQuery("from Supplier as supplier", Supplier.class);
//        TypedQuery<Customer> customerQuery = em.createQuery("from Customer as customer", Customer.class);
//        for (Supplier supplier: supplierQuery.getResultList()){
//            System.out.println(supplier);
//        }
//        for (Customer customer: customerQuery.getResultList()){
//            System.out.println(customer);
//        }

        em.close();
    }

    public static void main(String[] args) {
        testCompany2();
//        Product product1 = new Product("Prod1", 10);
//        Product product2 = new Product("Prod2", 0);
//        Product product3 = new Product("Prod3", 5);
//        Category category1 = new Category("Cat1");
//        Category category2 = new Category("Cat2");
//        Invoice invoice1 = new Invoice(1);
//        Invoice invoice2 = new Invoice(2);
//        Supplier supplier = new Supplier("bankAcc1", "AGH2",
//                "Czarnowiejska2", "Krakow2", "30-049");
//
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_DB");
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction etx = em.getTransaction();
//        etx.begin();
//        em.persist(supplier);
//        invoice1.addProduct(product1);
//        invoice1.addProduct(product3);
//        invoice2.addProduct(product2);
//        invoice2.addProduct(product3);
//        em.persist(invoice1);
//        em.persist(invoice2);
//        em.persist(product1);
//        em.persist(product2);
//        em.persist(product3);
//        em.persist(category1);
//        em.persist(category2);
//        supplier.addProduct(product1);
//        supplier.addProduct(product2);
//        supplier.addProduct(product3);
//        product1.addCategory(category1);
//        product2.addCategory(category2);
//        product3.addCategory(category1);
//        etx.commit();

//        TypedQuery<Product> prodByCat = em.createQuery("from Product as product" +
//                " where lower(product.category.name)=:categoryName", Product.class);
//        prodByCat.setParameter("categoryName", "cat1");
//        for (Product product: prodByCat.getResultList()){
//            System.out.println(product.getProductName());
//        }
//        TypedQuery<Category> catByProd = em.createQuery("from Category as category" +
//                " where :product member of category.products", Category.class);
//        catByProd.setParameter("product", product1);
//        for (Category category: catByProd.getResultList()){
//            System.out.println(category.getName());
//        }
//        em.close();
    }
}



