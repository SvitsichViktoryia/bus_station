package by.bsuir.busstation.dao;

import java.util.List;

import by.bsuir.busstation.entity.Bus;

public interface IBusDao {
	Long create(Bus bus) throws DaoException;

	Bus read(Long key) throws DaoException;

	List<Bus> readAll() throws DaoException;

	void update(Bus bus) throws DaoException;

	void delete(Long key) throws DaoException;
}
