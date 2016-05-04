package by.bsuir.busstation.dao.impl;

import java.util.Collections;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import by.bsuir.busstation.dao.DaoException;
import by.bsuir.busstation.dao.IRouteDao;
import by.bsuir.busstation.entity.Route;
import by.bsuir.busstation.utils.HibernateUtils;

public class IRouteDaoImpl implements IRouteDao {

	@Autowired
	private HibernateUtils manager;

	@Override
	public Long create(Route route) throws DaoException {
		Session session = null;
		try {
			session = manager.getSession();
			session.beginTransaction();
			Long key = (Long) session.save(route);
			session.getTransaction().commit();
			return key;

		} catch (HibernateException ex) {
			throw new DaoException("Error while creating new Route instance. See Details: ", ex);
		} finally {
			manager.closeSession(session);
		}
	}

	@Override
	public Route read(Long key) throws DaoException {
		Session session = null;
		try {
			session = manager.getSession();
			Route route = (Route) session.get(Route.class, key);
			return route;

		} catch (HibernateException ex) {
			throw new DaoException("error while reading Route instance. See Details: ", ex);
		} finally {
			manager.closeSession(session);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Route> readAll() throws DaoException {
		Session session = null;
		try {
			session = manager.getSession();
			List<Route> routeList = session.createCriteria(Route.class).list();
			return Collections.unmodifiableList(routeList);
			
		} catch (HibernateException ex) {
			throw new DaoException("error while reading all Route instances. See Details: ", ex);
		} finally {
			manager.closeSession(session);
		}
	}

	@Override
	public void update(Route route) throws DaoException {
		Session session = null;
		try {
			session = manager.getSession();
			session.beginTransaction();
			session.update(route);
			session.getTransaction().commit();

		} catch (HibernateException ex) {
			throw new DaoException("error while updating Route instance. See Details: ", ex);
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
			Route route = (Route) session.get(Route.class, key);
			session.delete(route);
			session.getTransaction().commit();

		} catch (HibernateException ex) {
			throw new DaoException("error while deleting Route instance. See Details: ", ex);
		} finally {
			manager.closeSession(session);
		}
	}
}
