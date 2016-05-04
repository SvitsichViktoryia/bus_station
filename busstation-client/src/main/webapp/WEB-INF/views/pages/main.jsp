<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>bus station</title>
	<link class="cssdeck" rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.1/css/bootstrap.min.css"> 
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.css" >
	<link rel="stylesheet" href="resources/css/style.css"> 
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
</head>

<body>
	<jsp:include page="/WEB-INF/views/tiles/header.jsp" />
	
	<div class="well">
		<div class="container">
			<div class="container">
				<c:if test = "${empty routes}">
					<div class="alert alert-info">
						На данный момент отсутствуют запланированные рейсы!
					</div>
				</c:if>
				<c:if test = "${message ne null}">
					<div class="alert alert-warning">
						<c:out value="${message}"/>
					</div>
				</c:if>
				<c:forEach var="route" items="${routes}">
					<ul class="list-group">
						<li class="list-group-item">
							<div class="row">
								<div class="col-md-10">
									<strong>Маршрут: </strong> <c:out value="${route.destination.name}"/> - <c:out value="${route.departure.name}"/>
									<strong>Стоимость: </strong> <c:out value="${route.cost}"/>  рублей <br/>
									<strong>Автобус: </strong><c:out value="${route.bus.regNumber}"/>
									<strong> Водитель:</strong> <c:out value="${route.driver.name}"/> <br/>
									<strong>Время отправления: </strong> <fmt:formatDate value="${route.departureDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
									<strong>Время прибытия: </strong> <fmt:formatDate value="${route.destinationDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</div>
								
								<div class="col-md-2">
									<span class="text=center">Осталось <c:out value="${route.bus.seats - fn:length(route.tickets)}"/> билетов</span>
									<form action="do" method="post">
										<button class="btn btn-info">заказать</button>
										<input type="hidden" name="route" value="${route.id}"/>
										<input type="hidden" name="action" value="MAIN"/>
									</form>
								</div>
							</div>
						</li>
					</ul>
				</c:forEach>
			</div>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/views/tiles/footer.jsp" />
</body>
</html>