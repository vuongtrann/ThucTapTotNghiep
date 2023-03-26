package DAO.IMPL;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import DAO.IBookDao;
import MODEL.Book;
import MODEL.Category;

public class BookDao implements IBookDao {

	@Override
	public ArrayList<Book> findAllBooks() {
		Session session = sessionFactory.openSession();
		return (ArrayList<Book>) session.createQuery("from Book").list();
	}

	public Book findBookByID( final int id) {
		Session session = sessionFactory.openSession();
		return (Book) session.get(Book.class, id);
	}

	@Override
	public void insertBook(Book book) {
		Session session = sessionFactory.openSession();
		try {
			// bắt đầu 1 giao dịch
			session.beginTransaction();
			// thực thi câu query dạng hql
			session.save(book);
			// kết thúc 1 giao dịch
			session.getTransaction().commit();
			System.out.println("insert book success !");
		} catch (RuntimeException e) {
			// nếu có lỗi thì trở về trạng thái lúc chưa bắt đầu giao dịch.
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}

	}

	@Override
	public void deleteBookByID(int id) {
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			String hqlUpdate = "delete Book b where b.id = :bookId";
			session.createQuery(hqlUpdate).setInteger("bookId", id).executeUpdate();
			session.getTransaction().commit();
			System.out.println("delete book success !");
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}

	}

	@Override
	public void updateBook(int id, String name, String description, double price, String image, int cat_id) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hqlUpdate = "update Book b set b.bookName = :newName,b.description = :newDes , b.price = :newPrice,"
					+ " b.image = :newImage, b.catId = :newCatID  where b.id = :bookId";
			session.createQuery(hqlUpdate).setString("newName", name).setString("newDes", description)
					.setDouble("newPrice", price).setString("newImage", image).setInteger("newCatID", cat_id)
					.setInteger("bookId", id).executeUpdate();
			session.getTransaction().commit();
			System.out.println("update book success !");
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

}
