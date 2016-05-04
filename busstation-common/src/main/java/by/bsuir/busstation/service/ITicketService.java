package by.bsuir.busstation.service;

import java.util.List;

import by.bsuir.busstation.entity.Ticket;

public interface ITicketService {
	Long create(Ticket ticket) throws ServiceException;

	Ticket read(Long key) throws ServiceException;

	List<Ticket> readAll() throws ServiceException;
	
	List<Ticket> readByUser(Long key) throws ServiceException;

	void update(Ticket ticket) throws ServiceException;

	void delete(Long key) throws ServiceException;
}
