<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<nav>
	<div class="container">
	 	<div class="row">
			<div class="navbar-header col-md-10">
				
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	                <ul class="nav navbar-nav">
	                	<li><a href="do?action=MAIN">главная</a></li>
	                    <li><a href="do?action=PLACES">населённые пункты</a></li>
						<li><a href="do?action=BUSES">автобусы</a></li>
						<li><a href="do?action=DRIVERS">водители</a></li>
						<li><a href="do?action=TICKETS">подтверждение билетов</a></li>
						<li><a href="do?action=USERS">пользователи</a></li>
	                </ul>
            	</div>
			</div>
			
			<div class="col-md-2 header-credentials">
				<a class="btn" href="do?action=LOGOUT">выйти</a>
			</div>
		</div>
	</div>
</nav>