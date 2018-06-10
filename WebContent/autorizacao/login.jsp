<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Cartório SP</title>

<!-- Styles -->
<link href="/ChatbotProjetoSJWebJava/public/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="shortcut icon"
	href="/ChatbotProjetoSJWebJava/public/images/logo-icon.png">
<link rel="icon" type="image/png" href="cartorio-sp.ico" />
<link rel="stylesheet" type="text/css"
	href="/ChatbotProjetoSJWebJava/public/css/dashboard.css">
<link rel="stylesheet"
	href="/ChatbotProjetoSJWebJava/public/css/datatable.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="/ChatbotProjetoSJWebJava/public/vendor/jquery-ui-1.12.1.custom/jquery-ui.structure.css">
<link rel="stylesheet"
	href="/ChatbotProjetoSJWebJava/public/vendor/jquery-ui-1.12.1.custom/jquery-ui.css">

<!-- Scripts -->
<script
	src="/ChatbotProjetoSJWebJava/public/vendor/jquery/jquery-3.2.1.min.js"></script>
<script
	src="/ChatbotProjetoSJWebJava/public/vendor/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
<script
	src="/ChatbotProjetoSJWebJava/public/vendor/bootstrap/js/popper.js"></script>
<script
	src="/ChatbotProjetoSJWebJava/public/vendor/bootstrap/js/bootstrap.min.js"></script>


<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.css">
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script>

<!-- Fonts -->
<script
	src="/ChatbotProjetoSJWebJava/public/fonts\fontawesome-free-5.0.8\svg-with-js\js\fontawesome-all.js"></script>
<style>
.ui-datepicker-header {
	background: #d9534f;
}

table.dataTable thead th {
	border-top: 1px solid #dee2e6;
	border-top: 1px solid #dee2e6;
	border-top-width: 1px;
	border-top-style: solid;
	border-top-color: rgb(222, 226, 230);
	border-bottom: rgb(222, 226, 230);
}
</style>

</head>
<body>
	
	<form method="POST" action="/ChatbotProjetoSJWebJava/autorizacao/logar">
		<c:if test="${not empty alertas}">
		<div class="col-md-12">
			
            <c:forEach var="alerta" items="${alertas}" varStatus="myIndex">
            <div class="container-row alert alert-${alerta.tipo}" id="alerta${myIndex.index}">    
                ${alerta.mensagem}

                <a style="float: right; cursor: pointer;" onclick="remover_alerta(${myIndex.index})">
                    X
                </a>
            </div>
            </c:forEach>
        </div>
        </c:if>
        <div onclick="popularLogin();" class="col-md-2 btn-success btn" style="margin-top:-8px; margin-left: 10px; height: 30px; width: 160px;" id="mensagem_alerta">
   			Preencher login   
		</div>
		<div style="margin: 0 auto; width: 400px;">
			<h1>Login</h1>
			Login <input type="email" class="form-control col-md-6" name="email" id="email"> <br/><br/>
			Senha <input type="password" class="form-control col-md-6" name="password" id="password"> <br/><br/>
			<button type="submit" class="btn btn-success" value="logar">Logar</button>
		</div>
		
		<script>
			function popularLogin() {
				document.getElementById("email").value = 'funcionario@email.com';
				document.getElementById("password").value = 'funcionario';
			}
			
		  	var enderecoBack = 'http://localhost:8080/ChatbotProjetoSJWebJava/';

		    function remover_alerta(id) {
		        $('#alerta' + id).remove();

		        $.ajax({
		            url: enderecoBack + '/utilizador/remover_alerta/' + id,
		            dataType: 'json',
		            method: 'get',
		            data: {
		                '_token': "{{ csrf_token() }}"
		            },
		        });
		    }
			
		</script>
	</form>
</body>
</html>