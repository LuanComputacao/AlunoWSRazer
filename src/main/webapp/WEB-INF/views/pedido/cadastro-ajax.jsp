<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pedido</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css" />">
<script type="text/javascript" src="<c:url value="/js/jquery-2.1.4.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/postagem.js"/>"></script>
</head>
<body>
	<c:import url="../menu.jsp"/>
	
	<c:url var="save" value="/pedido/ajax/save"/>
	<form id="save-ajax">
		<security:csrfInput/>												 
		<fieldset>
			<legend> Cadastro de Pedidos</legend>
			<div class="campo">
				<label for="dataPedido">Data Pedido</label><br>
				<input name="dataPedido" type="text" size="60"/>
				<span id="titulo-error" class="error"></span>
			</div>
			<div class="campo">
				<label for="produtos">Selecione os Produtos</label><br>
				<select multiple name="produtos">
					<c:forEach var="c" items="${produtos}">
					<option value="${c.id}">${c.descricao}</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<input type="submit" value="Salvar">
				<input type="reset" value="Limpar">
			</div>
		</fieldset>	
	</form>
	<div id="info"></div>
</body>
</html>