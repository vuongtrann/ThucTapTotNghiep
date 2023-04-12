package SERVICE;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.core.HttpContext;
import com.sun.jersey.api.core.HttpRequestContext;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import DAO.IMPL.BookDao;
import MODEL.Book;

@Path("/books")
public class BookService {
	private BookDao bookDao = new BookDao();

	@GET
	@Produces({ "application/json" })
	public List<Book> listBooks() {

		return bookDao.findAllBooks();

	}
	
	@GET
	@Produces({ "application/json" })
	@Path("/catID={id}")
	public List<Book> listBookByCatId(@PathParam("id") int id){
		return bookDao.showAllBookByCatId(id);
	}

	@GET
	@Produces({ "application/json" })
	@Path("/{id}")
	public Book findBookByID(@PathParam("id") int id) {
		return bookDao.findBookByID(id);
	}

	
}
