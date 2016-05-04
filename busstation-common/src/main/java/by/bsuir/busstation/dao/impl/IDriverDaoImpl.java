package by.bsuir.busstation.dao.impl;

import java.util.Collections;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import by.bsuir.busstation.dao.DaoException;
import by.bsuir.busstation.dao.IDriverDao;
import by.bsuir.busstation.entity.Driver;
import by.bsuir.busstation.utils.HibernateUtils;

public class IDriverDaoImpl implements IDriverDao {

	@Autowired
	private HibernateUtils manager;

	@Override
	public Long create(Driver driver) throws DaoException {
		Session session = null;
		try {
			session = manager.getSession();
			session.beginTransaction();
			Long key = (Long) session.save(driver);
			session.getTransaction().commit();
			return key;

		} catch (HibernateException ex) {
			throw new DaoException("Error while creating new Driver instance. See Details: ", ex);
		} finally {
			manager.closeSession(session);
		}
	}

	@Override
	public Driver read(Long key) throws DaoException {
		Session session = null;
		try {
			session = manager.getSession();
			Driver driver = (Driver) session.get(Driver.class, key);
			return driver;

		} catch (HibernateException ex) {
			throw new DaoException("error while reading Driver instance. See Details: ", ex);
		} finally {
			manager.closeSession(session);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Driver> readAll() throws DaoException {
		Session session = null;
		try {
			session = manager.getSession();
			List<Driver> driverList = session.createCriteria(Driver.class).list();
			return Collections.unmodifiableList(driverList);

		} catch (HibernateException ex) {
			throw new DaoException("error while reading all Driver instances. See Details: ", ex);
		} finally {
			manager.closeSession(session);
		}
	}

	@Override
	public void update(Driver driver) throws DaoException {
		Session session = null;
		try {
			session = manager.getSession();
			session.beginTransaction();
			session.update(driver);
			session.getTransaction().commit();

		} catch (HibernateException ex) {
			throw new DaoException("error while updating Driver instance. See Details: ", ex);
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
			Driver driver = (Driver) session.get(Driver.class, key);
			session.delete(driver);
			session.getTransaction().commit();

		} catch (HibernateException ex) {
			throw new DaoException("error while deleting Driver instance. See Details: ", ex);
		} finally {
			manager.closeSession(session);
		}
	}
}
