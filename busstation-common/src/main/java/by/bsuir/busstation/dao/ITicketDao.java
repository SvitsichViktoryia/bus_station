package by.bsuir.busstation.dao;

import java.util.List;

import by.bsuir.busstation.entity.Ticket;

public interface ITicketDao {
	Long create(Ticket ticket) throws DaoException;

	Ticket read(Long key) throws DaoException;

	List<Ticket> readAll() throws DaoException;
	
	List<Ticket> readByUser(Long key) throws DaoException;

	void update(Ticket ticket) throws DaoException;

	void delete(Long key) throws DaoException;
}
