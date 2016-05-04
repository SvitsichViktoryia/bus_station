<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>bus station</title>
<link class="cssdeck" rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/style.css">

</head>
<body>
	<div class="well">
		<div class="modal-body">
			<div class="container">
				<div class="authorization">
					<form class="form-horizontal" action="do" method="post">
						<fieldset>
							<div class="control-group">
								<label class="control-label"  for="username">Имя пользователя</label>
								<div class="controls">
									<input type="text" name="login" value="" required pattern="^[A-Za-z0-9_]{3,15}$" placeholder="введите логин...">
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label"  for="username">Email</label>
								<div class="controls">
									<input type="text" name="email" value="" required pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" placeholder="введите email...">
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label"  for="username">Пароль</label>
								<div class="controls">
									<input type="password" name="password" value="" required pattern="^[A-Za-z0-9_]{3,15}$" placeholder="введите пароль...">
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label"  for="username">Повторите пароль</label>
								<div class="controls">
									<input type="password" name="confirm" value="" required pattern="^[A-Za-z0-9_]{3,15}$" placeholder="повторите пароль...">
								</div>
							</div>
							
							<div class="control-group">
								<div class="controls">
									<button class="btn btn-success">Регистрация</button>
									<c:if test="${message ne null}">
										<span class="alert alert-danger">${message}</span>
									</c:if>
								</div>
							</div>
							<input type="hidden" name="action" value="REGISTRATION"/>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/tiles/footer.jsp" />
</body>
</html>