package DAO;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import MODEL.Book;

public interface IBookDao {
	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	public List<Book> findAllBooks();
	public void insertBook(final Book book);
	public void deleteBookByID(int id);
	public void updateBook(int id, String name, String description, double price, String image, int cat_id);
	

}
