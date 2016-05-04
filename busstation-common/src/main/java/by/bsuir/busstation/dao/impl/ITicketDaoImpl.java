package by.bsuir.busstation.dao.impl;

import java.util.Collections;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import by.bsuir.busstation.dao.DaoException;
import by.bsuir.busstation.dao.ITicketDao;
import by.bsuir.busstation.entity.Ticket;
import by.bsuir.busstation.utils.HibernateUtils;

public class ITicketDaoImpl implements ITicketDao {

	private static final String QUERY_READ_BY_USER = "SELECT t FROM Ticket t WHERE t.user.id = :userId";

	@Autowired
	private HibernateUtils manager;

	@Override
	public Long create(Ticket ticket) throws DaoException {
		Session session = null;
		try {
			session = manager.getSession();
			session.beginTransaction();
			Long key = (Long) session.save(ticket);
			session.getTransaction().commit();
			return key;

		} catch (HibernateException ex) {
			throw new DaoException("Error while creating new Ticket instance. See Details: ", ex);
		} finally {
			manager.closeSession(session);
		}
	}

	@Override
	public Ticket read(Long key) throws DaoException {
		Session session = null;
		try {
			session = manager.getSession();
			Ticket ticket = (Ticket) session.get(Ticket.class, key);
			return ticket;

		} catch (HibernateException ex) {
			throw new DaoException("error while reading Ticket instance. See Details: ", ex);
		} finally {
			manager.closeSession(session);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> readAll() throws DaoException {
		Session session = null;
		try {
			session = manager.getSession();
			List<Ticket> ticketList = session.createCriteria(Ticket.class).list();
			return Collections.unmodifiableList(ticketList);

		} catch (HibernateException ex) {
			throw new DaoException("error while reading all Ticket instances. See Details: ", ex);
		} finally {
			manager.closeSession(session);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> readByUser(Long key) throws DaoException {
		Session session = null;
		try {
			session = manager.getSession();
			Query query = session.createQuery(QUERY_READ_BY_USER);
			query.setLong("userId", key);
			List<Ticket> ticketList = query.list();
			return Collections.unmodifiableList(ticketList);

		} catch (HibernateException ex) {
			throw new DaoException("error while reading user Ticket instances. See Details: ", ex);
		} finally {
			manager.closeSession(session);
		}
	}

	@Override
	public void update(Ticket ticket) throws DaoException {
		Session session = null;
		try {
			session = manager.getSession();
			session.beginTransaction();
			session.update(ticket);
			session.getTransaction().commit();

		} catch (HibernateException ex) {
			throw new DaoException("error while updating Ticket instance. See Details: ", ex);
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
			Ticket ticket = (Ticket) session.get(Ticket.class, key);
			session.delete(ticket);
			session.getTransaction().commit();

		} catch (HibernateException ex) {
			throw new DaoException("error while deleting Ticket instance. See Details: ", ex);
		} finally {
			manager.closeSession(session);
		}
	}
}
