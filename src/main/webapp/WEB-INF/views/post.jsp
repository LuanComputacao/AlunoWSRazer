<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${pedido.titulo}</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css" />">
</head>
<body>
	<fieldset class="header">
		<h1>java Razer  </h1>
	</fieldset>
	
	<c:import url="menu.jsp"/><br>

	<fieldset>
	<div>
		<div>
			<h2>${postagem.titulo}</h2>
			<p>Cliente: <a href="<c:url value="/cliente/${postagem.cliente.id}/page/1"/>" >${postagem.cliente.nome}</a> 
			| <fmt:parseDate var="date" value="${postagem.dataPostagem}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>
			Data: <fmt:formatDate value="${date}" type="both"/>
			</p>
		</div>
		<div>
			<p class="post-texto">${postagem.texto}"</p>
		</div>
		<div>
			<p class="post-categ">
			<span>Categorias: </span>
			<c:forEach var="c" items="${postagem.categorias}">
				<a href="<c:url value="/categoria/${c.permalink}/page/1"/>" title="${c.descricao}">
				${c.descricao}</a>
			</c:forEach>	
			</p>
		</div>
		<div class="post-cliente">
			<img class="post-avatar" src="<c:url value="/avatar/load/${postagem.cliente.usuario.avatar.id}"/>"/>
			<p><strong>${postagem.cliente.nome}</strong></p>
			<p>${postagem.cliente.biografia}</p>
		</div>
	</div>	
	<c:if test="${ logado == null }">
	<p><em>Apenas usuários logados podem comentar neste post!</em></p>
	</c:if>		
	<c:import url="comments.jsp"/>
	</fieldset>
</body>
</html>