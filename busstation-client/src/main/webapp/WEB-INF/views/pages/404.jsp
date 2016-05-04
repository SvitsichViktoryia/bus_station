<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>404</title>
	
	<link class="cssdeck" rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.1/css/bootstrap.min.css"> 
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.css" >
	<link rel="stylesheet" href="resources/css/style.css"> 
</head>
<body>
	
	<div class="container">
	    <div class="row">
	        <div class="col-md-12">
	            <div class="error-template">
	                <h1>Oops!</h1>
	                <h2>404 Not Found</h2>
	                <div class="error-details">
	                    Sorry, an error has occured, Requested page not found!
	                </div>
	            </div>
	        </div>
	    </div>
	</div>

	<jsp:include page="/WEB-INF/views/tiles/footer.jsp" />
</body>
</html>