package DAO;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import MODEL.Category;

public interface ICategoryDao {
	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	public List<Category> findAllCategories();
	
	public Category findCategoryByID(int id);


	public void inserCategory(final Category category);

	public void deleteCategorykByID(int id);

	public void updateCategory(int id, String name);
}
