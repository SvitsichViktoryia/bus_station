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
				<c:forEach var="route" items="${routes}">
					<ul class="list-group">
						<li class="list-group-item">
							<div class="row">
							<div class="col-md-10">
								
								<form class="form-inline" action="do" method="post">
									<div class="row" style="margin-top:5px;">
									<div class="form-group">
										<label for="departure">Маршрут:</label>
										<select name="departure" >
											<c:forEach var="dep" items="${places}">
												<c:choose>
													<c:when test="${route.departure.id eq dep.id}">
														<option selected="selected" value="${dep.id}"><c:out value="${dep.name}"/></option>
													</c:when>
													
													<c:otherwise>
														<option value="${dep.id}"><c:out value="${dep.name}"/></option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
									    <input type="hidden" name="action" value="MAIN">
									    <input type="hidden" name="id" value="${route.id}">
									</div>
									-
									<div class="form-group">
										<select name="destination">
											<c:forEach var="dest" items="${places}">
												<c:choose>
													<c:when test="${route.destination.id eq dest.id}">
														<option selected="selected" value="${dest.id}"><c:out value="${dest.name}"/></option>
													</c:when>
													
													<c:otherwise>
														<option value="${dest.id}"><c:out value="${dest.name}"/></option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
									</div>
									
									<div class="form-group">
										<label for="cost">Стоимость:</label>
										<input id="cost" style="height: 30px;width: 221px;" type="number" name="cost" value="${route.cost}" min="5000" required="required">
									</div>
									</div>
									
									<div class="row" style="margin-top:5px;">
									<div class="form-group">
										<label for="bus">Автобус:</label>
										<select name="bus">
											<c:forEach var="bus" items="${buses}">
												<c:choose>
													<c:when test="${route.bus.id eq bus.id}">
														<option selected="selected" value="${bus.id}"><c:out value="${bus.regNumber}"/></option>
													</c:when>
													
													<c:otherwise>
														<option value="${bus.id}"><c:out value="${bus.regNumber}"/></option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
									</div>
									
									<div class="form-group">
										<label for="driver">Водитель:</label>
										<select name="driver">
											<c:forEach var="driver" items="${drivers}">
												<c:choose>
													<c:when test="${route.driver.id eq driver.id}">
														<option selected="selected" value="${driver.id}"><c:out value="${driver.name}"/></option>
													</c:when>
													
													<c:otherwise>
														<option value="${driver.id}"><c:out value="${driver.name}"/></option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
									</div>
									</div>
									
									<div class="row" style="margin-top:5px;">
									<div class="form-group">
										<label  for="depDate">Дата отъезда</label>
										<input style="height: 30px;width: 180px;" name="depDate" value='<fmt:formatDate value="${route.departureDate}" pattern="yyyy-MM-dd HH:mm:ss"/>' required placeholder="yyyy-MM-dd hh:mm:ss" type="text"/>
									</div>
									
									<div class="form-group">
										<label for="destDate">Дата прибытия</label>
										<input style="height: 30px;width: 180px;" name="destDate" value='<fmt:formatDate value="${route.destinationDate}" pattern="yyyy-MM-dd HH:mm:ss"/>' required placeholder="yyyy-MM-dd hh:mm:ss" type="text"/>
									</div>
									<button class="btn btn-secondary" type="submit">изменить</button>
									</div>
								</form>
							</div>
							
							<div class="col-md-2">
								<form action="do" method="post">
									<button class="btn btn-danger">отменить рейс</button>
									<input type="hidden" name="route" value="${route.id}"/>
									<input type="hidden" name="action" value="MAIN"/>
								</form>
							</div>
							</div>
						</li>
					</ul>
				</c:forEach>
			</div>
				
				<form class="form-horizontal" action="do" method="post">
					<fieldset>
						<div class="control-group">
							<label class="control-label"  for="username">Пункт отправления</label>
							<div class="controls">
								<select name="departure">
									<c:forEach var="dep" items="${places}">
										<option value="${dep.id}"><c:out value="${dep.name}"/></option>
									</c:forEach>
								</select>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label"  for="username">Пункт назначения</label>
							<div class="controls">
								<select name="destination">
									<c:forEach var="dest" items="${places}">
										<option value="${dest.id}"><c:out value="${dest.name}"/></option>
									</c:forEach>
								</select>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label"  for="username">Стоимость</label>
							<div class="controls">
								<input style="height: 30px;width: 221px;" type="number" name="cost" value="" min="5000" required="required">
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label"  for="username">Автобус</label>
							<div class="controls">
								<select name="bus">
									<c:forEach var="bus" items="${buses}">
										<option value="${bus.id}"><c:out value="${bus.regNumber}"/></option>
									</c:forEach>
								</select>
							</div>
						</div>
						
						
						<div class="control-group">
							<label class="control-label"  for="username">Водитель</label>
							<div class="controls">
								<select name="driver">
									<c:forEach var="driver" items="${drivers}">
										<option value="${driver.id}"><c:out value="${driver.name}"/></option>
									</c:forEach>
								</select>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label"  for="username">Дата отъезда</label>
							<div class="controls">
								<input style="height: 30px;width: 221px;" name="depDate" required placeholder="yyyy-MM-dd hh:mm:ss" type="text"/>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label"  for="username">Дата прибытия</label>
							<div class="controls">
								<input style="height: 30px; width: 221px;" name="destDate" required placeholder="yyyy-MM-dd hh:mm:ss" type="text"/>
							</div>
						</div>
						
						<div class="control-group">
							<div class="controls">
								<button class="btn btn-success">добавить</button>
							</div>
						</div>
						
						<c:if test="${message ne null}">
							<span class="alert alert-danger">${message}</span>
						</c:if>
						<input type="hidden" name="action" value="MAIN"/>
					</fieldset>
				</form>
			</div>
		</div>

	<jsp:include page="/WEB-INF/views/tiles/footer.jsp" />
</body>
</html>