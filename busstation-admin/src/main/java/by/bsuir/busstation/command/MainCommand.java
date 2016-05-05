package by.bsuir.busstation.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.bsuir.busstation.entity.*;
import org.springframework.beans.factory.annotation.Autowired;

import by.bsuir.busstation.service.IBusService;
import by.bsuir.busstation.service.IDriverService;
import by.bsuir.busstation.service.IPlaceService;
import by.bsuir.busstation.service.IRouteService;
import by.bsuir.busstation.service.ServiceException;
import by.bsuir.busstation.utils.PagesUtil;

public class MainCommand implements Command {

	private static final String METHOD_POST = "POST";
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String BUS_PARAM = "bus";
	private static final String DRIVER_PARAM = "driver";
	private static final String COST_PARAM = "cost";
	private static final String DEST_PARAM = "destination";
	private static final String DEST_DATE_PARAM = "destDate";
	private static final String DEP_PARAM = "departure";
	private static final String ID_PARAM = "id";
	private static final String DEP_DATE_PARAM = "depDate";
	private static final String ROUTE_PARAM = "route";
	private static final String MESSAGE_PARAM = "message";

	private static final String PLACES_PARAM = "places";
	private static final String ROUTES_PARAM = "routes";
	private static final String DRIVERS_PARAM = "drivers";
	private static final String BUSES_PARAM = "buses";

	@Autowired
	private IRouteService routeService;

	@Autowired
	private IBusService busService;

	@Autowired
	private IPlaceService placeService;

	@Autowired
	private IDriverService driverService;

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);

		if (request.getMethod().equals(METHOD_POST)) {
			if (request.getParameter(ROUTE_PARAM) != null) {
				return deleteRoute(request);
			} else {
				try {
					Long destinationId = Long.valueOf(request.getParameter(DEST_PARAM));
					Long departureId = Long.valueOf(request.getParameter(DEP_PARAM));
					Date destinationDate = format.parse(request.getParameter(DEST_DATE_PARAM));
					Date departureDate = format.parse(request.getParameter(DEP_DATE_PARAM));
					Long busId = Long.valueOf(request.getParameter(BUS_PARAM));
					Long driverId = Long.valueOf(request.getParameter(DRIVER_PARAM));
					Long cost = Long.valueOf(request.getParameter(COST_PARAM));
					String routeId = request.getParameter(ID_PARAM);

					if (destinationDate.before(departureDate) || destinationId.equals(departureId)) {
						List<Route> routes = new ArrayList<>(new HashSet<>(routeService.readAll()));
						List<Bus> buses = new ArrayList<>(new HashSet<>(busService.readAll()));
						List<Place> places = new ArrayList<>(new HashSet<>(placeService.readAll()));
						List<Driver> drivers = new ArrayList<>(new HashSet<>(driverService.readAll()));

						request.setAttribute(ROUTES_PARAM, routes);
						request.setAttribute(BUSES_PARAM, buses);
						request.setAttribute(PLACES_PARAM, places);
						request.setAttribute(DRIVERS_PARAM, drivers);

						request.setAttribute(MESSAGE_PARAM, "проверьте введённые данные.");
						return PagesUtil.VIEW_MAIN;

					} else {
						if (routeId != null) {
							Long id = Long.valueOf(routeId);
							Route route = routeService.read(id);

							Bus bus = busService.read(busId);
							Driver driver = driverService.read(driverId);
							Place destination = placeService.read(destinationId);
							Place departure = placeService.read(departureId);

							route.setBus(bus);
							route.setCost(cost);
							route.setDeparture(departure);
							route.setDepartureDate(departureDate);
							route.setDestination(destination);
							route.setDestinationDate(destinationDate);
							route.setDriver(driver);

							routeService.update(route);
						} else {
							Bus bus = busService.read(busId);
							Driver driver = driverService.read(driverId);
							Place destination = placeService.read(destinationId);
							Place departure = placeService.read(departureId);

							Route route = new Route();
							route.setBus(bus);
							route.setCost(cost);
							route.setDeparture(departure);
							route.setDepartureDate(departureDate);
							route.setDestination(destination);
							route.setDestinationDate(destinationDate);
							route.setDriver(driver);

							routeService.create(route);
						}

					}
				} catch (ParseException ex) {
					List<Route> routes = new ArrayList<>(new HashSet<>(routeService.readAll()));
					List<Bus> buses = new ArrayList<>(new HashSet<>(busService.readAll()));
					List<Place> places = new ArrayList<>(new HashSet<>(placeService.readAll()));
					List<Driver> drivers = new ArrayList<>(new HashSet<>(driverService.readAll()));

					request.setAttribute(ROUTES_PARAM, routes);
					request.setAttribute(BUSES_PARAM, buses);
					request.setAttribute(PLACES_PARAM, places);
					request.setAttribute(DRIVERS_PARAM, drivers);

					request.setAttribute(MESSAGE_PARAM, "проверьте введённые даты.");
					return PagesUtil.VIEW_MAIN;
				}
				return PagesUtil.REDIRECT_MAIN;
			}
		} else {
			List<Route> routes = new ArrayList<>(new HashSet<>(routeService.readAll()));
			List<Bus> buses = new ArrayList<>(new HashSet<>(busService.readAll()));
			List<Place> places = new ArrayList<>(new HashSet<>(placeService.readAll()));
			List<Driver> drivers = new ArrayList<>(new HashSet<>(driverService.readAll()));

			request.setAttribute(ROUTES_PARAM, routes);
			request.setAttribute(BUSES_PARAM, buses);
			request.setAttribute(PLACES_PARAM, places);
			request.setAttribute(DRIVERS_PARAM, drivers);
			return PagesUtil.VIEW_MAIN;
		}
	}

	private String deleteRoute(HttpServletRequest request) throws ServiceException{
		Long routeId = Long.valueOf(request.getParameter(ROUTE_PARAM));
		Route route = routeService.read(routeId);
		route.getBus().getRoutes().remove(route);
		route.getDriver().getRoutes().remove(route);
		route.getDeparture().getDetartures().remove(route);
		route.getDestination().getDestinations().remove(route);
		route.getTickets().clear();
		routeService.delete(routeId);
		return PagesUtil.REDIRECT_MAIN;
	}

}
