<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
		<div class="container">

				<c:forEach var="driver" items="${drivers}">
					<ul class="list-group">
						<li class="list-group-item">
							<div class="row">
							<form class="form-inline" action="do" method="post">
								<div class="form-group">
									<label for="name">ФИО:</label>
									<input id="name" class="form-control" name="name" style="height: 35px;width: 500px;" type="text" required="required" value='<c:out value="${driver.name}"/> '>
								    <input type="hidden" name="action" value="DRIVERS">
								    <input type="hidden" name="id" value="${driver.id}">
								</div>

								<div class="form-group">
									<label for="exp">стаж: </label>
									<input id="exp" class="form-control" name="experience" style="height: 35px;" type="number" min="3" required="required" value='<c:out value="${driver.experience}"/>'>
								</div>
								<button class="btn btn-secondary" type="submit">изменить</button>
							</form>
								<div class="col-md-2">
									<form action="do" method="post">
										<button class="btn btn-danger">удалить</button>
										<input type="hidden" name="driver" value="${driver.id}"/>

										<input type="hidden" name="action" value="DRIVERS"/>

									</form>
								</div>
							</div>
						</li>
					</ul>
				</c:forEach>
				
				<form class="form-horizontal" action="do" method="post">
					<fieldset>
						<div class="control-group">
							<label class="control-label"  for="username">ФИО</label>
							<div class="controls">
								<input style="height: 30px" type="text" name="name" value="" required="required">
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label"  for="username">Стаж</label>
							<div class="controls">
								<input style="height: 30px" type="number" name="experience" value="" min="3" required="required">
							</div>
						</div>
						
						<div class="control-group">
							<div class="controls">
								<button class="btn btn-success">добавить</button>
							</div>
						</div>
						
						<input type="hidden" name="action" value="DRIVERS"/>
					</fieldset>
				</form>

		</div>
	</div>
	<jsp:include page="/WEB-INF/views/tiles/footer.jsp" />
</body>
</html>