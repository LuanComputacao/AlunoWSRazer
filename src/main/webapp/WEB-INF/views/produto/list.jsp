<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Produtos</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css" />">
</head>
<body>
	<c:import url="../menu.jsp"/>
	<fieldset>
		<legend>Lista de Produtos</legend>
		<table class="table">
			<tr>
				<th>Código</th>
				<th>Descrição</th>
				<th>Ação</th>
			</tr>	
			<c:forEach var="produto" items="${page.content}" varStatus="i">		
			<tr bgcolor='${i.count % 2 != 0 ? '#f1f1f1' : 'white'}'>
				<td>${produto.id}</td>
				<td>${produto.descricao}</td>

				<td>					
					<c:url var="update" value="/produto/update/${produto.id}"/>
					<a href="${update}" title="Editar">&#9445</a>
					<c:url var="delete" value="/produto/delete/${produto.id}"/>
					<a href="${delete}" title="Excluir">&#9447</a>
				</td>
			</tr>
			</c:forEach>
		</table>
		<c:import url="../paginacao.jsp"/>
	</fieldset>
</body>
</html>