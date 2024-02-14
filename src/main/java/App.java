import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        //Create Database Connection
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LibraryManagement");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        //Update Database
        transaction.begin();

        //Add Author
        Author author = new Author();
        author.setName("Orhan Pamuk");
        author.setBirthDate(LocalDate.parse("1952-06-07"));
        author.setCountry("Türkiye");
        entityManager.persist(author);

        //Add Publisher
        Publisher publisher = new Publisher();
        publisher.setName("YKY");
        publisher.setAddress("İstanbul");
        publisher.setEstablishmentYear(1992);
        entityManager.persist(publisher);

        //Add Category
        Category categoryNovel = new Category();
        categoryNovel.setName("Novel");
        categoryNovel.setDescription("Chain of events which includes a cast of characters, a setting, and an ending");
        entityManager.persist(categoryNovel);

        ArrayList<Category> categoryArrayList = new ArrayList<>();
        categoryArrayList.add(categoryNovel);


        //Add Book
        Book book = new Book();
        book.setName("Kar");
        book.setPublicationYear(2002);
        book.setStock(100);
        book.setAuthor(author);
        book.setPublisher(publisher);
        entityManager.persist(book);
        book.setCategoryList(categoryArrayList);

        //Add BookBorrowing
        BookBorrowing bookBorrowing = new BookBorrowing();
        bookBorrowing.setBook(book);
        bookBorrowing.setBorrowerName("Onur");
        bookBorrowing.setBorrowingDate(LocalDate.parse("2024-02-14"));
        bookBorrowing.setReturnDate(null);
        entityManager.persist(bookBorrowing);

        transaction.commit();


    }
}
