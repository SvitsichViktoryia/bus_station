package by.bsuir.busstation.command;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import by.bsuir.busstation.entity.Place;
import by.bsuir.busstation.service.IPlaceService;
import by.bsuir.busstation.service.ServiceException;
import by.bsuir.busstation.utils.PagesUtil;

public class PlacesCommand implements Command {

	private static final String METHOD_POST = "POST";
	private static final String NAME_PARAM = "name";
	private static final String ID_PARAM = "id";
	private static final String PLACES_PARAM = "places";
	private static final String MESSAGE_PARAM = "message";

	private static final String PLACE_PARAM = "place";

	@Autowired
	private IPlaceService placeService;

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		if (request.getMethod().equals(METHOD_POST)) {
			if (request.getParameter(PLACE_PARAM) != null) {
				return deletePlace(request);}
			String id = request.getParameter(ID_PARAM);
			String name = request.getParameter(NAME_PARAM);
			
			if (id != null) {
				return updatePlace(Long.valueOf(id), name);
			} else {
				return createPlace(name);
			}
		} else {
			List<Place> places = new ArrayList<>(new HashSet<>(placeService.readAll()));
			request.setAttribute(PLACES_PARAM, places);
			return PagesUtil.VIEW_PLACES;
		}
	}
	private String deletePlace(HttpServletRequest request) throws ServiceException{
		Long placeId = Long.valueOf(request.getParameter(PLACE_PARAM));
		try {
			Place place = placeService.read(placeId);
			placeService.delete(placeId);

		} catch (Exception e) {
			request.setAttribute(MESSAGE_PARAM, "Невозможно удалить город");
			List<Place> places = new ArrayList<>(new HashSet<>(placeService.readAll()));
			request.setAttribute(PLACES_PARAM, places);
			return PagesUtil.VIEW_PLACES;
		}
		return PagesUtil.REDIRECT_PLACES;
	}
	private boolean checkPlace(String placeName) throws ServiceException {
		List<Place> places = new ArrayList<>(new HashSet<>(placeService.readAll()));
		for (Place place: places) {
			if (place.getName().equals(placeName)) {
				return false;
			}
		}
		return true;
	}
	
	private String updatePlace(Long id, String name) throws ServiceException {
		Place place = placeService.read(id);
		place.setName(name);
		placeService.update(place);
		return PagesUtil.REDIRECT_PLACES;
	}
	
	private String createPlace(String name) throws ServiceException {
		if (checkPlace(name)) {
			Place place = new Place();
			place.setName(name);
			placeService.create(place);
		}
		return PagesUtil.REDIRECT_PLACES;
	}
}
