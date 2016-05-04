package by.bsuir.busstation.service;

import java.util.List;

import by.bsuir.busstation.entity.Bus;

public interface IBusService {
	Long create(Bus bus) throws ServiceException;

	Bus read(Long key) throws ServiceException;

	List<Bus> readAll() throws ServiceException;

	void update(Bus bus) throws ServiceException;

	void delete(Long key) throws ServiceException;
}
