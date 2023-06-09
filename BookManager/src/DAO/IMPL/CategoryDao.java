package DAO.IMPL;

import java.util.List;

import org.hibernate.Session;

import DAO.ICategoryDao;
import MODEL.Category;

public class CategoryDao implements ICategoryDao {

	@Override
	public List<Category> findAllCategories() {
		Session session = sessionFactory.openSession();
		return session.createQuery("from Category").list();
	}
	@Override
	public Category findCategoryByID(final int id){
		Session session = sessionFactory.openSession();
		return (Category) session.get(Category.class, id);
	}
	@Override
	public void inserCategory(Category category) {
		Session session = sessionFactory.openSession();
		try {
			// bắt đầu 1 giao dịch
			session.beginTransaction();
			// thực thi câu query dạng hql
			session.save(category);
			// kết thúc 1 giao dịch
			session.getTransaction().commit();
			System.out.println("insert Category success !");
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
	public void deleteCategorykByID(int id) {
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			String hqlUpdate = "delete Category b where b.id = :catId";
			session.createQuery(hqlUpdate).setInteger("catId", id).executeUpdate();
			session.getTransaction().commit();
			System.out.println("delete Category success !");
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}

	}

	@Override
	public void updateCategory(int id, String name) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hqlUpdate = "update Category b set b.categoryName = :newName where b.id = :catId";
			session.createQuery(hqlUpdate).setString("newName", name).setInteger("catId", id).executeUpdate();
			session.getTransaction().commit();
			System.out.println("update Category success !");
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

}
