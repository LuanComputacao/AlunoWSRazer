<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<thead>
<tr>
	<th>Código:</th>
	<th>Data de Pedido:</th>
	<th>Cliente:</th>
	<th>CPF:</th>
	<th>Itens:</th>	
	<th>Ação:</th>
</tr>
</thead>
<tbody>
<c:forEach var="pedido" items="${page.content}" varStatus="i">		
<tr bgcolor='${i.count % 2 != 0 ? '#f1f1f1' : 'white'}'>
	<td>${pedido.id}</td>
	<td>
		<fmt:parseDate var="date" value="${pedido.dataPedido}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>
		<fmt:formatDate value="${date}" type="both"/>
	</td>
	<td>${pedido.cliente.nome}</td>
	<td>${pedido.cliente.cpf}</td>
	<td>
		<c:forEach var="c" items="${pedido.produtos}">
			[ ${c.descricao} ]
		</c:forEach>
	</td>
	<td>					
		<c:url var="update" value="/pedido/update/${pedido.id}"/>
		<a href="${update}" title="Editar">&#9445</a>
		<c:url var="delete" value="/pedido/delete/${pedido.id}"/>
		<a href="${delete}" title="Excluir">&#9447</a>
	</td>
</tr>
</c:forEach>
</tbody>
<tfoot>
<tr>
	<th colspan="7">
	<c:forEach var="p" begin="1" end="${page.totalPages}">
		<c:choose>
			<c:when test="${ (p - 1) eq page.number }">
				<button id="button_${p}" disabled="disabled" value="${p}">${p}</button>
			</c:when>
			<c:otherwise>
				<button id="button_${p}" value="${p}">${p}</button>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	</th>
</tr>
</tfoot>
