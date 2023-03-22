package SERVICE;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import DAO.IMPL.CategoryDao;
import MODEL.Category;

@Path("/categories")
public class CategoryService {
	private CategoryDao categoryDao = new CategoryDao();

	@GET
	@Produces({ "application/json" })
	public List<Category> listCategories() {
		//List<Category> listCategories1 = categoryDao.findAllCategories();
		return categoryDao.findAllCategories();

	}
}
