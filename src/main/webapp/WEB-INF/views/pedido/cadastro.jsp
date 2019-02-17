<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pedidos</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css" />">
</head>
<body>
	<c:import url="../menu.jsp"/>
	
	<c:url var="save" value="/pedido/save"/>
	<form:form modelAttribute="pedido" action="${save}" method="post">
		<form:hidden path="id"/>												 
		<fieldset class="grupo">
			<legend> Cadastro de Pedidos</legend>
			<div class="campo">
				<form:label path="dataPedido">Data Pedido</form:label><br>
				<form:input path="dataPedido" type="text" />
				<form:errors path="dataPedido" cssClass="error"/>
			</div>
			<div class="campo">
				<form:label path="produtos">Selecione os Produtos</form:label><br>
				<form:select multiple="true" path="produtos">
					<form:options items="${produtos}" itemValue="id" itemLabel="descricao"/>
				</form:select>
			</div>
			
<!-- 			<div class="campo">  -->
<%-- 				<form:label path="cliente">Selecione o cliente</form:label><br>  --%>
<%--  				<form:select multiple="false" path="cliente">  --%>
<%--  					<form:options items="${cliente}" itemValue="id" itemLabel="nome"/>  --%>
<%--  				</form:select>  --%>
<!--  			</div>  -->

			<div>
				<input type="submit" value="Salvar">
				<input type="reset" value="Limpar">
			</div>
		</fieldset>	
	</form:form>
	
</body>
</html>