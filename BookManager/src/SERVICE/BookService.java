package SERVICE;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import DAO.IMPL.BookDao;
import MODEL.Book;
import MODEL.Category;

@Path("/books")
public class BookService {
	private BookDao bookDao = new BookDao();

	@GET
	@Produces({ "application/json" })
	public List<Book> listCategories() {

		return bookDao.findAllBooks();

	}
}
