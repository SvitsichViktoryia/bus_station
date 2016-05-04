package by.bsuir.busstation.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import by.bsuir.busstation.dao.DaoException;
import by.bsuir.busstation.dao.IPlaceDao;
import by.bsuir.busstation.entity.Place;
import by.bsuir.busstation.service.IPlaceService;
import by.bsuir.busstation.service.ServiceException;

public class IPlaceServiceImpl implements IPlaceService {

	private static final Logger LOG = LoggerFactory.getLogger(IPlaceService.class);

	@Autowired
	private IPlaceDao placeDao;

	@Override
	public Long create(Place place) throws ServiceException {
		try {
			Long key = placeDao.create(place);
			LOG.info("Place has created with id: " + key);
			return key;
		} catch (DaoException ex) {
			LOG.error("Error while creating place: ", ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public Place read(Long key) throws ServiceException {
		try {
			Place place = placeDao.read(key);
			LOG.info("Place has read with id: " + key);
			return place;
		} catch (DaoException ex) {
			LOG.error("Error while reading place: ", ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public List<Place> readAll() throws ServiceException {
		try {
			List<Place> placeList = placeDao.readAll();
			LOG.info("Place list has read. Size: " + placeList.size());
			return placeList;
		} catch (DaoException ex) {
			LOG.error("Error while reading place list: ", ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public void update(Place place) throws ServiceException {
		try {
			placeDao.update(place);
			LOG.info("Place has updated with id: " + place.getId());

		} catch (DaoException ex) {
			LOG.error("Error while updating place: ", ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public void delete(Long key) throws ServiceException {
		try {
			placeDao.delete(key);
			LOG.info("Place has deleted with id: " + key);

		} catch (DaoException ex) {
			LOG.error("Error while deleting place: ", ex);
			throw new ServiceException(ex);
		}
	}

}
