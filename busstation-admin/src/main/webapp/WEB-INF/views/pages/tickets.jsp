<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>bus station</title>

<link class="cssdeck" rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.1/css/bootstrap.min.css"> 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.css" >
<link rel="stylesheet" href="resources/css/style.css"> 
</head>
<body>
	<jsp:include page="/WEB-INF/views/tiles/header.jsp" />
	
	<div class="well">
		<div class="container">
			<div class="container">
				<c:if test = "${empty tickets}">
					<div class="alert alert-info">
						На данный момент отсутствуют забронированные билеты!
					</div>
				</c:if>
				<c:forEach var="ticket" items="${tickets}">
					<ul class="list-group">
						<li class="list-group-item">
							<div class="row">
								<div class="col-md-9">
									<strong>Имя пользователя: </strong><c:out value="${ticket.user.login}"/>
									<strong>Маршрут: </strong><c:out value="${ticket.route.destination.name}"/> - <c:out value="${ticket.route.departure.name}"/> <br/>
									<strong>Время отправления: </strong><fmt:formatDate value="${ticket.route.departureDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
									<strong>Время прибытия: </strong><fmt:formatDate value="${ticket.route.destinationDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</div>
								<div class="col-md-3">
								<strong>Статус: </strong> 
								<c:choose>
									<c:when test="${ticket.status}">
										<span class="bg-success">ПОДТВЕРЖДЕНО</span>
									</c:when>
									<c:otherwise>
										<span class="bg-warning">НЕ ПОДТВЕРЖДЕНО</span>
										<form action="do" method="post">
											<button class="btn btn-success">подтвердить</button>
											<input type="hidden" name="ticket" value="${ticket.id}"/>
											<input type="hidden" name="action" value="TICKETS"/>
										</form>
									</c:otherwise>
								</c:choose>
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