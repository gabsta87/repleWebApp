package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Transaction;

import dao.Connection.SessionManager;
import dataManagement.Item;

public class ItemsDAO extends DAO<Item> implements Serializable{
	private static final long serialVersionUID = -3600811903383436963L;

	public ItemsDAO() {	}

	@Override
	public Item find(int id) {
		Transaction t = SessionManager.getSessionFactory().getCurrentSession().beginTransaction();
		Item result = SessionManager.getSessionFactory().getCurrentSession().get(Item.class, id);
		t.commit();
		return result;
	}

	@Override
	public void create(Item obj) {
		Transaction t = SessionManager.getSessionFactory().getCurrentSession().beginTransaction();
		SessionManager.getSessionFactory().getCurrentSession().save(obj);
		t.commit();
	}

	@Override
	public void update(Item obj) {
		Transaction t = SessionManager.getSessionFactory().getCurrentSession().beginTransaction();
		SessionManager.getSessionFactory().getCurrentSession().update(obj);
		t.commit();
	}

	@Override
	public void delete(Item obj) {
		Transaction t = SessionManager.getSessionFactory().getCurrentSession().beginTransaction();
		SessionManager.getSessionFactory().getCurrentSession().delete(obj);
		t.commit();
	}

	@Override
	public void saveOrUpdate(Item obj) {
		Transaction t = SessionManager.getSessionFactory().getCurrentSession().beginTransaction();
		SessionManager.getSessionFactory().getCurrentSession().saveOrUpdate(obj);
		t.commit();
	}
	
	public List<Item> getAll(){
		Transaction t = SessionManager.getSessionFactory().getCurrentSession().beginTransaction();
		@SuppressWarnings("unchecked")
		List<Item> items = (List<Item>) SessionManager.getSessionFactory().getCurrentSession().createQuery("from countries").list();
		t.commit();
		return items;
	}
	
}
