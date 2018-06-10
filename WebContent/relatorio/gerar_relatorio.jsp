<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*,
                  utils.*,
                 	model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Laravel</title>
<!-- Fonts -->
<link href="https://fonts.googleapis.com/css?family=Raleway:100,600"
	rel="stylesheet" type="text/css">
<!-- Styles -->
<style>
html, body {
	background-color: #fff;
	color: #111;
	font-family: 'Raleway', sans-serif;
	font-weight: 100;
	height: 100vh;
	margin: 0;
}

.full-height {
	height: 100vh;
}

.flex-center {
	align-items: center;
	display: flex;
	justify-content: center;
}

.position-ref {
	position: relative;
}

.top-right {
	position: absolute;
	right: 10px;
	top: 18px;
}

.content {
	text-align: center;
}

.title {
	font-size: 84px;
}

.links>a {
	color: #111;
	padding: 0 25px;
	font-size: 12px;
	font-weight: 600;
	letter-spacing: .1rem;
	text-decoration: none;
	text-transform: uppercase;
}

.m-b-md {
	margin-bottom: 30px;
}

.header {
	
}

.container {
	margin: 20px;
}

table td, table th {
	padding: 5px;
	border: 1px solid #fff;
	border-width: 0 1px 1px 0;
}
</style>
</head>
<body>
	<div class="container">
		<div class="header">
			<div>
				<h2>Relatório de atendimento</h2>
			</div>
			<div>
				<strong>Periodicidade:</strong> de 
				<fmt:formatDate value="${filtro.data_de}" pattern="dd/MM/yyyy" />
				até 
				<fmt:formatDate value="${filtro.data_ate}" pattern="dd/MM/yyyy" />
			</div>
		</div>
		<hr>
		<div>
			<table>
				<thead>
					<tr>
						<th style="width: 200px;">ID do atendimento</th>
						<th style="width: 200px;">Data de inicio</th>
						<th style="width: 100px;">Qnt. de interações</th>
						<th style="width: 100px;">Duração da interação</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty atendimentos}">
						<c:forEach var="atendimento" items="${atendimentos}">
							<tr>
								<td>${ atendimento.id }</td>
								<td><fmt:formatDate value="${atendimento.data_criacao}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
								<td>${ fn:length(atendimento.atendimentoHasPergunta) }</td>
								<td>${ atendimento.duracao_atendimento }</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
		<hr>
		<div>
			<h2>Detalhes</h2>
			<div>
				<div>
					<strong>Quantidade de atendimentos:</strong> <td>${ fn:length(relatorio.atendimentos) }</td>
				</div>
				<div>
					<strong>Quantidade de atendimentos resolvidos na pelo
						chatbot na primeira tentativa: </strong> ${ relatorio.quantidade_atendimentos.primeira_tentativas }
				</div>
				<div>
					<strong>Quantidade de atendimentos resolvidos na pelo
						chatbot na segunda tentativa: </strong> ${ relatorio.quantidade_atendimentos.segunda_tentativas }
				</div>
				<div>
					<strong>Quantidade de atendimentos resolvidos na pelo
						chatbot na terceira tentativa: </strong> ${ relatorio.quantidade_atendimentos.terceira_tentivas }
				</div>
				<div>
					<strong>Quantidade de atendimentos encaminhado para
						atendimento humano: </strong> ${ relatorio.quantidade_atendimentos.encaminhamento_humano }
				</div>
			</div>
		</div>
	</div>
	<script>
	window.print();
	</script>
</body>
</html>