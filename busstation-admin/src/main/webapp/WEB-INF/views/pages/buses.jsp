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
		<c:if test="${message ne null}">
			<span class="alert alert-danger">${message}</span>
		</c:if>
		<c:if test="${message1 ne null}">
			<span class="alert alert-danger">${message1}</span>
		</c:if>
		<div class="container">

				<c:forEach var="bus" items="${buses}">
					<ul class="list-group">
						<li class="list-group-item">
                        <div class="row">
                            <form class="form-inline" action="do" method="post">
								<div class="form-group">
									<label for="numb">Номер:</label>
									<input id="numb" class="form-control" name="regNumber" style="height: 35px;" type="text" required="required" value='<c:out value="${bus.regNumber}"/>'>
								    <input type="hidden" name="action" value="BUSES">
								    <input type="hidden" name="id" value="${bus.id}">
								</div>
								
								<div class="form-group">
									<label for="date">Дата последнего техосмотра: </label>
									<input id="date" class="form-control" name="date" style="height: 35px;" type="date" min="3" required="required" value='<fmt:formatDate value="${bus.inspectionDate}" pattern="yyyy-MM-dd"/>'>
								</div>
								
								<div class="form-group">
									<label for="seats">Количество мест: </label>
									<input id="seats" class="form-control" name="seats" style="height: 35px;" type="number" min="1" required="required" value='<c:out value="${bus.seats}"/>'>
								</div>
								<button class="btn btn-secondary" type="submit">изменить</button>
							</form>
							<div class="col-md-2">
								<form action="do" method="post">
									<button class="btn btn-danger">удалить</button>
									<input type="hidden" name="bus" value="${bus.id}"/>
									<input type="hidden" name="action" value="BUSES"/>
								</form>
							</div>
                        </div>
						</li>
					</ul>
				</c:forEach>
				<form class="form-horizontal" action="do" method="post">
					<fieldset>
						<div class="control-group">
							<label class="control-label"  for="username">Номер</label>
							<div class="controls">
								<input style="height: 30px" type="text" name="regNumber" value="" required="required">
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label"  for="username">Количество мест</label>
							<div class="controls">
								<input style="height: 30px" type="number" name="seats" value="" min="1" required="required">
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label"  for="username">Дата техосмотра</label>
							<div class="controls">
								<input style="height: 30px" type="date" name="date" value="" required="required" pattern="yyyy-MM-dd">
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<button class="btn btn-success">добавить</button>

							</div>
						</div>
						
						<input type="hidden" name="action" value="BUSES"/>
					</fieldset>
				</form>

		</div>
	</div>
	<jsp:include page="/WEB-INF/views/tiles/footer.jsp" />
</body>
</html>