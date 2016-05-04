package by.bsuir.busstation.service;

import java.util.List;

import by.bsuir.busstation.entity.Place;

public interface IPlaceService {
	Long create(Place place) throws ServiceException;

	Place read(Long key) throws ServiceException;

	List<Place> readAll() throws ServiceException;

	void update(Place place) throws ServiceException;

	void delete(Long key) throws ServiceException;
}
