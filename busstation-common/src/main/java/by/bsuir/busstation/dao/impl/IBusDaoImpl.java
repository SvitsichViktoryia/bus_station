package by.bsuir.busstation.dao.impl;

import java.util.Collections;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import by.bsuir.busstation.dao.DaoException;
import by.bsuir.busstation.dao.IBusDao;
import by.bsuir.busstation.entity.Bus;
import by.bsuir.busstation.utils.HibernateUtils;

public class IBusDaoImpl implements IBusDao {

	@Autowired
	private HibernateUtils manager;

	@Override
	public Long create(Bus bus) throws DaoException {
		Session session = null;
		try {
			session = manager.getSession();
			session.beginTransaction();
			Long key = (Long) session.save(bus);
			session.getTransaction().commit();
			return key;

		} catch (HibernateException ex) {
			throw new DaoException("Error while creating new Bus instance. See Details: ", ex);
		} finally {
			manager.closeSession(session);
		}
	}

	@Override
	public Bus read(Long key) throws DaoException {
		Session session = null;
		try {
			session = manager.getSession();
			Bus bus = (Bus) session.get(Bus.class, key);
			return bus;

		} catch (HibernateException ex) {
			throw new DaoException("error while reading Bus instance. See Details: ", ex);
		} finally {
			manager.closeSession(session);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bus> readAll() throws DaoException {
		Session session = null;
		try {
			session = manager.getSession();
			List<Bus> busList = session.createCriteria(Bus.class).list();
			return Collections.unmodifiableList(busList);
			
		} catch (HibernateException ex) {
			throw new DaoException("error while reading all Bus instances. See Details: ", ex);
		} finally {
			manager.closeSession(session);
		}
	}

	@Override
	public void update(Bus bus) throws DaoException {
		Session session = null;
		try {
			session = manager.getSession();
			session.beginTransaction();
			session.update(bus);
			session.getTransaction().commit();

		} catch (HibernateException ex) {
			throw new DaoException("error while updating Bus instance. See Details: ", ex);
		} finally {
			manager.closeSession(session);
		}

	}

	@Override
	public void delete(Long key) throws DaoException {
		Session session = null;
		try {
			session = manager.getSession();
			session.beginTransaction();
			Bus bus = (Bus) session.get(Bus.class, key);
			session.delete(bus);
			session.getTransaction().commit();

		} catch (HibernateException ex) {
			throw new DaoException("error while deleting Bus instance. See Details: ", ex);
		} finally {
			manager.closeSession(session);
		}
	}
}
