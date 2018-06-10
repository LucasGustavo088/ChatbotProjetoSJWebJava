<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="../layouts/header_dashboard.jsp" />

<div class="panel panel-default">
	<form id="form" >
		<div class="panel-heading">
			<h2 class="panel-title">
				<strong> <i class="fas fa-sitemap"></i> Palavra-chave:
					Adicione perguntas/respostas associadas ao tópico (palavra-chave).
					Dessa forma, o robô entenderá melhor qual é a melhor resposta
					associada a uma pergunta.
				</strong>
			</h2>
		</div>
		<div class="panel-body">
			<div class="form-group row">
				<label for="topico" class="col-sm-2 control-label">Tópico
					principal</label>
				<div class="col-sm-10">
					<input type="text" required name="topico"
						placeholder="Digite o tópico principal das perguntas e respostas. Ex: Certidão de nascimento"
						class="form-control" id="topico" rows="5">
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h2 class="panel-title">
						<strong> <i class="fas fa-sitemap"></i> Perguntas e
							respostas associadas ao tópico principal
						</strong>
					</h2>
				</div>
				<div class="panel-body">
					<div class="row container">
						<button type="button" onclick="adicionar_pergunta()"
							class="btn btn-success" style="float: right;">
							<i class="fas fa-plus"></i> Adicionar pergunta
						</button>
					</div>
	
					<div id="respostas_container" style="margin-top: 20px;"></div>
				</div>
			</div>
	
			<div class="salvar_cancelar row" style="margin-top: 200px;">
				<div class="col-md-2" style="float: right">
					<button type="button" class="btn btn-danger" id="cancelar">Cancelar</button>
					<button type="button" class="btn btn-success" id="salvar">Salvar</button>
				</div>
			</div>
		</div>
	</form>
</div>

<div id="clone_pergunta" style="display: none;">
	<div class="panel panel-default" id="div_pergunta$id">

		<div class="panel-heading">
			<div class="panel-title">
				<strong> Associação pergunta/resposta #$id </strong>
			</div>
		</div>
		<div class="panel-body">
			<div class="form-group row">
				<label for="pergunta$id" class="col-sm-2 control-label">Pergunta</label>
				<div class="col-sm-10">
					<input type="pergunta$id" name="perguntas[$id][pergunta]"
						placeholder="Digite uma pergunta" class="form-control col-md-10"
						id="pergunta$id" />
				</div>
			</div>
			<div class="form-group row">
				<label for="resposta" class="col-sm-2 control-label">Resposta</label>
				<div class="col-sm-10">
					<textarea type="text" name="respostas[$id][resposta]"
						placeholder="Resposta associada a pergunta acima"
						class="form-control" id="resposta$id" rows="5"></textarea>
				</div>
			</div>
			<a href="#/" onclick="remover_pergunta($id)" style="float: right;"><i
				class="fas fa-trash-alt"></i></a>
		</div>
	</div>
</div>
<script>
	var palavras_chaves_prefixo_principais = [ 'prefixo' , ' prefixo'];

	$(document).ready(function() {
		popular_pergunta();
		
		popular_teste();
	});
	
	function popular_teste() {
		var numero_teste = Math.floor((Math.random() * 100) + 1);
		$('#topico').val("topico teste " + numero_teste);
		$('#resposta1').text("teste resposta " + numero_teste);
		//adicionar_pergunta();
		$('#resposta2').text("teste resposta " + numero_teste);
		$('#pergunta2').text("teste pergunta " + numero_teste);
		$('#pergunta1').val("teste pergunta " + numero_teste);
		remover_pergunta(3);
	}
	
	$('#salvar').click(function() {
		salvar_cadastro();
	});

	function popular_pergunta() {
		$.each(palavras_chaves_prefixo_principais, function(index,
				palavra_chave_prefixo) {
			var id = contador_pergunta
			adicionar_pergunta();

			$('#pergunta' + id).val(
					palavra_chave_prefixo + ' (DIGITE O TÓPICO EM QUESTÃO)');
		});
	}

	function salvar_cadastro() {
		
		$.ajax({
			url: enderecoBack + 'chatbot/p_adicionar_palavra_chave_pergunta_ajax',
			method: 'POST',
			data: {
				formulario: $('#form').serialize()
			},
			success: function(retorno) {
				console.log(retorno);
			}
		});
	}

	var contador_pergunta = 1;
	function adicionar_pergunta() {
		var id = contador_pergunta;
		var clone_pergunta = $('#clone_pergunta').html();
		clone_pergunta = str_replace_all('$id', id, clone_pergunta);
		$('#respostas_container').append(clone_pergunta);

		contador_pergunta++;
	}

	function remover_pergunta(id) {
		$('#div_pergunta' + id).remove();
	}
</script>
<c:import url="../layouts/footer_dashboard.jsp" />