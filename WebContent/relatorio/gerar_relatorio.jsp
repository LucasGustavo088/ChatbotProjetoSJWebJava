<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page
	import="java.util.*,
                  utils.*,
                        model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Relatório</title>

<link rel="shortcut icon"
	href="/ChatbotProjetoSJWebJava/public/images/logo-icon.png">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="/ChatbotProjetoSJWebJava/public/vendor/jquery-ui-1.12.1.custom/jquery-ui.structure.css">
<link rel="stylesheet"
	href="/ChatbotProjetoSJWebJava/public/vendor/jquery-ui-1.12.1.custom/jquery-ui.css">

<!-- Scripts -->
<script
	src="/ChatbotProjetoSJWebJava/public/vendor/jquery/jquery-3.2.1.min.js"></script>
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

.thead {

}

.container {
	margin: 20px;
}

table th td {
	font-weight: bold;
}

table td, table th {
	padding: 5px;
	border: 1px solid #fff;
	border-width: 0 1px 1px 0;
}
</style>
</head>
<body>
	<div class="container col-md-12">
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
			<table class="table table-striped table-bordered table-hover">
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
								<td><fmt:formatDate value="${atendimento.data_criacao}"
										pattern="dd/MM/yyyy HH:mm:ss" /></td>
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
			<table class="table table-striped table-bordered table-hover">
				<thead class="thead">
					<tr>
						<td><strong>Atributo</strong></td>
						<td><strong>Valor</strong></td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Quantidade de atendimentos:</td>
						<td>${ fn:length(relatorio.atendimentos) }</td>
					</tr>
					<tr>
						<td>Quantidade de atendimentos resolvidos na pelo chatbot na
							primeira tentativa:</td>
						<td>${relatorio.quantidade_atendimentos.primeira_tentativas}</td>
					</tr>
					<tr>
						<td>Quantidade de atendimentos resolvidos na pelo chatbot na
							segunda tentativa:</td>
						<td>${ relatorio.quantidade_atendimentos.segunda_tentativas }</td>
					</tr>
					<tr>
						<td>Quantidade de atendimentos resolvidos na pelo chatbot na
							terceira tentativa:</td>
						<td>${relatorio.quantidade_atendimentos.terceira_tentivas}</td>
					</tr>
					<tr>
						<td>Quantidade de atendimentos encaminhado para atendimento
							humano:</td>
						<td>${ relatorio.quantidade_atendimentos.encaminhamento_humano }</td>
					</tr>
				</tbody>

			</table>
		</div>
	</div>
	<script>
                window.print();
        </script>
</body>
</html>