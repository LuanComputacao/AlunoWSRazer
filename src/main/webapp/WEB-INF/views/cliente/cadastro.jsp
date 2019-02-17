<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cliente</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/style.css" />">
</head>
<body>
	<c:import url="../menu.jsp" />

	<c:url var="save" value="/cliente/save" />
	<form:form modelAttribute="cliente" action="${save}" method="post">
		<form:hidden path="id" />
		<fieldset>
			<legend> Cadastro de Cliente</legend>
			<div class="campo">
				<form:label path="cpf">CPF</form:label>
				<br>
				<form:input path="cpf" type="text" />
				<form:errors path="cpf" cssClass="error" />
			</div>
			<div class="campo">
				<form:label path="nome">Nome do Cliente</form:label>
				<br>
				<form:input path="nome" type="text" />
				<form:errors path="nome" cssClass="error" />
			</div>
			<div class="campo">
				<form:label path="sobrenome">Sobrenome</form:label>
				<br>
				<form:input path="sobrenome" type="text" />
				<form:errors path="sobrenome" cssClass="error" />
			</div>
			<div>
				<input type="submit" value="Salvar"> <input type="reset"
					value="Limpar">
			</div>
		</fieldset>
	</form:form>

	<fieldset class="grupo">
		<legend>Lista de Clientes</legend>
		<table class="table">
			<tr>
				<th>Código</th>
				<th>CPF</th>
				<th>Nome</th>
				<th>Sobrenome</th>
				<th>Ação</th>
			</tr>
			<c:forEach var="cliente" items="${page.content}" varStatus="i">
				<tr bgcolor='${i.count % 2 != 0 ? '#f1f1f1' : 'white'}'>
				    <td>${cliente.id}</td>
					<td>${cliente.cpf}</td>
					<td>${cliente.nome}</td>
					<td>${cliente.sobrenome}</td>
					<td><c:url var="update" value="/cliente/update/${cliente.id}" />
						<a href="${update}" title="Editar">&#9445</a> <c:url var="delete"
							value="/cliente/delete/${cliente.id}" /> <a href="${delete}"
						title="Excluir">&#9447</a></td>
				</tr>
			</c:forEach>
		</table>
		<c:import url="../paginacao.jsp" />
	</fieldset>
</body>
</html>