package by.bsuir.busstation.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import by.bsuir.busstation.dao.DaoException;
import by.bsuir.busstation.dao.ITicketDao;
import by.bsuir.busstation.entity.Ticket;
import by.bsuir.busstation.service.ITicketService;
import by.bsuir.busstation.service.ServiceException;

public class ITicketServiceImpl implements ITicketService {

	private static final Logger LOG = LoggerFactory.getLogger(ITicketService.class);

	@Autowired
	private ITicketDao ticketDao;

	@Override
	public Long create(Ticket ticket) throws ServiceException {
		try {
			Long key = ticketDao.create(ticket);
			LOG.info("Ticket has created with id: " + key);
			return key;
		} catch (DaoException ex) {
			LOG.error("Error while creating ticket: ", ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public Ticket read(Long key) throws ServiceException {
		try {
			Ticket ticket = ticketDao.read(key);
			LOG.info("Ticket has read with id: " + key);
			return ticket;
		} catch (DaoException ex) {
			LOG.error("Error while reading ticket: ", ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public List<Ticket> readAll() throws ServiceException {
		try {
			List<Ticket> ticketList = ticketDao.readAll();
			LOG.info("Ticket list has read. Size: " + ticketList.size());
			return ticketList;
		} catch (DaoException ex) {
			LOG.error("Error while reading ticket list: ", ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public List<Ticket> readByUser(Long key) throws ServiceException {
		try {
			List<Ticket> ticketList = ticketDao.readByUser(key);
			LOG.info("Ticket list has read. Size: " + ticketList.size());
			return ticketList;
		} catch (DaoException ex) {
			LOG.error("Error while reading ticket list: ", ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public void update(Ticket ticket) throws ServiceException {
		try {
			ticketDao.update(ticket);
			LOG.info("Ticket has updated with id: " + ticket.getId());

		} catch (DaoException ex) {
			LOG.error("Error while updating ticket: ", ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public void delete(Long key) throws ServiceException {
		try {
			ticketDao.delete(key);
			LOG.info("Ticket has deleted with id: " + key);

		} catch (DaoException ex) {
			LOG.error("Error while deleting ticket: ", ex);
			throw new ServiceException(ex);
		}
	}

}
