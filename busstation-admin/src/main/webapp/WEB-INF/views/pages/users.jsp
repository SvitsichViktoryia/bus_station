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

		<c:forEach var="user" items="${users}">
			<ul class="list-group">
				<li class="list-group-item">
					<div class="row">
						<form class="form-inline" action="do" method="post">
							<div class="form-group">
								<label for="login">ФИО:</label>
								<input id="login" class="form-control" name="login" style="height: 35px;width: 500px;" type="text" required pattern="^[A-Za-z0-9_]{3,15}$" value='<c:out value="${user.login}"/> '>
								<input type="hidden" name="action" value="USERS">
								<input type="hidden" name="id" value="${user.id}">
							</div>

							<div class="form-group">
								<label for="email">email: </label>
								<input id="email" class="form-control" name="email" style="height: 35px;" type="text" required pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" value='<c:out value="${user.email}"/>'>
							</div>
							<div class="form-group">
								<label for="password">password: </label>
								<input id="password" class="form-control" name="password" style="height: 35px;" type="text" required  value='<c:out value="${user.password}"/>'>
							</div>
							<button class="btn btn-secondary" type="submit">изменить</button>

						</form>
						<div class="col-md-2">
							<form action="do" method="post">
								<button class="btn btn-danger">удалить</button>
								<input type="hidden" name="user" value="${user.id}"/>
								<input type="hidden" name="action" value="USERS"/>
							</form>
						</div>

						<div class="col-md-2">
							<form action="do" method="post">
								<button class="btn btn-danger">Пользователь -> Aдмин</button>
								<input type="hidden" name="admin" value="${user.id}"/>
								<input type="hidden" name="action" value="USERS"/>
							</form>
						</div>

						<div class="col-md-2">
							<form action="do" method="post">
								<button class="btn btn-danger">Aдмин -> Пользователь</button>
								<input type="hidden" name="admin_user" value="${user.id}"/>
								<input type="hidden" name="action" value="USERS"/>
							</form>
						</div>
					</div>
				</li>
			</ul>
		</c:forEach>

	</div>
</div>
<jsp:include page="/WEB-INF/views/tiles/footer.jsp" />
</body>
</html>