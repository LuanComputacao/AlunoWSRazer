<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pedidos</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css" />">
<script type="text/javascript" src="<c:url value="/js/jquery-2.1.4.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/postagem.js"/>"></script>
</head>
<body>
	<c:import url="../menu.jsp"/>
	<fieldset>
		<legend>Lista de Pedidos</legend>
		<div>
			<input id="search" type="search" placeholder="Busca por CPF" value="">
		</div>
		<table id="table-ajax" class="table" title="${ clienteId != null ? clienteId : 0 }">
		
			<jsp:include page="table-rows.jsp"/>
			
		</table>
		
		<div id="info"></div>
		
	</fieldset>
</body>
</html>