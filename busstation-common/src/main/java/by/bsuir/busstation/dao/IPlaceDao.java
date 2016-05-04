package by.bsuir.busstation.dao;

import java.util.List;

import by.bsuir.busstation.entity.Place;

public interface IPlaceDao {
	Long create(Place place) throws DaoException;

	Place read(Long key) throws DaoException;

	List<Place> readAll() throws DaoException;

	void update(Place place) throws DaoException;

	void delete(Long key) throws DaoException;
}
