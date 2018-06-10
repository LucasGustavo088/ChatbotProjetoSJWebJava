<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	
	<form method="POST" action="/ChatbotProjetoSJWebJava/autorizacao/logar">
		<div onclick="popularLogin();" style="position: fixed; top:10px; left: 10px; height: 30px; width: 160px; background: #ccc;" class="btn-success btn" id="mensagem_alerta">
   			Preencher login   
		</div>
		<div style="margin: 0 auto; width: 400px;">
			<h1>Login</h1>
			Login <input type="email" name="email" id="email"> <br/><br/>
			Senha <input type="password" name="password" id="password"> <br/><br/>
			<button type="submit" value="logar">Logar</button>
		</div>
		
		<script>
			function popularLogin() {
				document.getElementById("email").value = 'funcionario@email.com';
				document.getElementById("password").value = 'funcionario';
			}
		</script>
	</form>
</body>
</html>