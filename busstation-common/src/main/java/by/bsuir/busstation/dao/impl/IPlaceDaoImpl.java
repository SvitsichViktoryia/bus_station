package by.bsuir.busstation.dao.impl;

import java.util.Collections;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import by.bsuir.busstation.dao.DaoException;
import by.bsuir.busstation.dao.IPlaceDao;
import by.bsuir.busstation.entity.Place;
import by.bsuir.busstation.utils.HibernateUtils;

public class IPlaceDaoImpl implements IPlaceDao {

	@Autowired
	private HibernateUtils manager;

	@Override
	public Long create(Place place) throws DaoException {
		Session session = null;
		try {
			session = manager.getSession();
			session.beginTransaction();
			Long key = (Long) session.save(place);
			session.getTransaction().commit();
			return key;

		} catch (HibernateException ex) {
			throw new DaoException("Error while creating new Place instance. See Details: ", ex);
		} finally {
			manager.closeSession(session);
		}
	}

	@Override
	public Place read(Long key) throws DaoException {
		Session session = null;
		try {
			session = manager.getSession();
			Place place = (Place) session.get(Place.class, key);
			return place;

		} catch (HibernateException ex) {
			throw new DaoException("error while reading Place instance. See Details: ", ex);
		} finally {
			manager.closeSession(session);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Place> readAll() throws DaoException {
		Session session = null;
		try {
			session = manager.getSession();
			List<Place> placeList = session.createCriteria(Place.class).list();
			return Collections.unmodifiableList(placeList);

		} catch (HibernateException ex) {
			throw new DaoException("error while reading all Place instances. See Details: ", ex);
		} finally {
			manager.closeSession(session);
		}
	}

	@Override
	public void update(Place place) throws DaoException {
		Session session = null;
		try {
			session = manager.getSession();
			session.beginTransaction();
			session.update(place);
			session.getTransaction().commit();

		} catch (HibernateException ex) {
			throw new DaoException("error while updating Place instance. See Details: ", ex);
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
			Place place = (Place) session.get(Place.class, key);
			session.delete(place);
			session.getTransaction().commit();

		} catch (HibernateException ex) {
			throw new DaoException("error while deleting Place instance. See Details: ", ex);
		} finally {
			manager.closeSession(session);
		}
	}
}
