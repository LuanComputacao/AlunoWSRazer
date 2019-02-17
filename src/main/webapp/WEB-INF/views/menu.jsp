<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<fieldset style="font-family: monospace; font-size: 10pt;">
	<legend>Menu</legend>
	<nav>
		<a href="<c:url value="/"/>">Home</a>
	</nav>
	<nav>		
	<security:authorize access="hasAnyAuthority('ADIMIN', 'AUTOR', 'LEITOR')">	
	|	<a href="<c:url value="/usuario/perfil/${logado.id}"/>">Perfil do Usuário</a>
	</security:authorize>
	<security:authorize access="hasAuthority('ADIMIN')">	
	|	<a href="<c:url value="/usuario/list"/>">Lista de Usuários</a>
	</security:authorize>
	</nav>
	<nav>
		<security:authorize access="hasAnyAuthority('ADIMIN','AUTOR','LEITOR')">
	|	<a href="<c:url value="/cliente/add"/>">Novo Cliente</a>
	</security:authorize>
	</nav>
	<nav>
	<security:authorize access="hasAuthority('ADIMIN')">
	|	<a href="<c:url value="/pedido/add"/>">Novo Pedido</a> 		
	</security:authorize>
	<security:authorize access="hasAuthority('ADIMIN')">
	|   <a href="<c:url value="/pedido/list"/>">Lista de Pedido</a>
	</security:authorize>
	</nav>
	
	<nav>
	<security:authorize access="hasAnyAuthority('ADIMIN','AUTOR')">
	|	<a href="<c:url value="/produto/add"/>">Novo Produto</a>
	</security:authorize>
	</nav>
	<nav class="login">
		
		<c:if test="${ logado == null }">
			<a href="<c:url value="/auth/form"/>">Entrar</a> |
			<a href="<c:url value="/usuario/add"/>">Cadastrar-se</a>
		</c:if>
		
		<security:authorize access="hasAnyAuthority('ADIMIN','AUTOR','LEITOR')">
			<form action="<c:url value="/logout"/>" method="post">
				<security:csrfInput/>
				<button type="submit">Sair</button>
			</form>
		</security:authorize>		
	</nav>	
</fieldset>
