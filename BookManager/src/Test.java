import java.util.List;

import org.hibernate.Session;

import DAO.IMPL.BookDao;
import DAO.IMPL.CategoryDao;
import MODEL.Book;
import MODEL.Category;
import UTIL.HibernateUtil;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		BookDao bookDao = new BookDao();
		CategoryDao categoryDao = new CategoryDao()	;
		Book book = new Book();
		Category category = new Category();
		
		/**BOOK TEST*/
		
		//Book book2 = new Book("Book2", "des2", 1111, "DAdsa", 2);
		//bookDao.insertBook(book2);
		
		//bookDao.updateBook(1, "book1Update", "dsadsadasda", 1231313, "dasdad", 1);
		
		//bookDao.deleteBookByID(2);

//		List<Book> listBooks = bookDao.findAllBooks();
//		for(Book b:listBooks) {
//			System.out.println(b.toString());
//		}
		
		
		
		/**Category Test*/
		
		
		//Category category2 = new Category("cat2");
		//categoryDao.inserCategory(category2);
		
		//categoryDao.updateCategory(2, "cat2update");
		//categoryDao.deleteCategorykByID(2);
		
		List<Category> listCategories = categoryDao.findAllCategories();
		System.out.println(listCategories);
//		for(Category c: listCategories) {
//			System.out.println(c.toString());
//		}
		
		
		/**User Test*/
	}

}
