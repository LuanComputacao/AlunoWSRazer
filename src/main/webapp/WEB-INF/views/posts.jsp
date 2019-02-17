<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css" />">
</head>
<body>
	<fieldset class="header">
		<h1>Trabalho Java web Razer </h1>
	</fieldset>
	
	<c:import url="menu.jsp"/><br>

	<fieldset>
	<div>
		<form action="<c:url value="/search"/>" method="get">
			<input name="texto" type="search" placeholder="busca por palavra chave"> 
			<input type="submit" value="Localizar">
		</form>
	</div>
	<c:forEach var="p" items="${page.content}">
	<div>
		<div>
			<h2><a href="<c:url value="/${p.permalink}" />" title="${p.permalink}">${p.permalink}</a></h2>
			<p>Cliente: <a href="<c:url value="/cliente/${p.cliente.id}/page/1"/>" >${p.cliente.nome}</a> 
			| <fmt:parseDate var="date" value="${p.dataPedido}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>
			   Data: <fmt:formatDate value="${date}" type="both"/>
			
			</p>
		</div>
		<div>
			<p class="post-texto">
				<c:forTokens var="resumo" items="${p.dataPedido}"  delims=" " begin="0" end="60" >
					${resumo}
				</c:forTokens>
			</p>
		</div>
		<div>
			<p class="post-categ">
			<span>Intens Pedido: </span>
			<c:forEach var="c" items="${p.produtos}">
				<a href="<c:url value="/produto/${c.permalink}/page/1"/>" title="${c.descricao}">
				${c.descricao}</a>
			</c:forEach>	
			</p>
		</div>
	</div>	
	</c:forEach>
	<c:import url="paginacao.jsp"/>
	</fieldset>
</body>
</html>