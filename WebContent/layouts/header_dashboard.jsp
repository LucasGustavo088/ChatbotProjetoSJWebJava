<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Cartório SP</title>

    <!-- Styles -->
    <link href="/ChatbotProjetoSJWebJava/public/css/bootstrap.min.css" rel="stylesheet"> 
    <link rel="shortcut icon" href="/ChatbotProjetoSJWebJava/public/images/logo-icon.png">
    <link rel="icon" type="image/png" href="cartorio-sp.ico"/>
    <link rel="stylesheet" type="text/css" href="/ChatbotProjetoSJWebJava/public/css/dashboard.css">
    <link rel="stylesheet" href="/ChatbotProjetoSJWebJava/public/css/datatable.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/ChatbotProjetoSJWebJava/public/vendor/jquery-ui-1.12.1.custom/jquery-ui.structure.css">
    <link rel="stylesheet" href="/ChatbotProjetoSJWebJava/public/vendor/jquery-ui-1.12.1.custom/jquery-ui.css">
    
    <!-- Scripts -->
    <script src="/ChatbotProjetoSJWebJava/public/vendor/jquery/jquery-3.2.1.min.js"></script>
    <script src="/ChatbotProjetoSJWebJava/public/vendor/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
    <script src="/ChatbotProjetoSJWebJava/public/vendor/bootstrap/js/popper.js"></script>
    <script src="/ChatbotProjetoSJWebJava/public/vendor/bootstrap/js/bootstrap.min.js"></script>
    
    
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script>

    <!-- Fonts -->
    <script src="/ChatbotProjetoSJWebJava/public/fonts\fontawesome-free-5.0.8\svg-with-js\js\fontawesome-all.js"></script>
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
    <div class="wrapper">
    <!-- Sidebar Holder -->
    <nav id="sidebar">
        <div class="sidebar-header">
            <h3>Cartório SP</h3>
        </div>

        <ul class="list-unstyled components">
            <li>
                <a href="#home_menu" data-toggle="collapse" aria-expanded="false"><span class="glyphicon glyphicon-home"></span> Home</a>
                <ul class="collapse list-unstyled" id="home_menu">
                    <li><a href="/ChatbotProjetoSJWebJava/dashboard/home"><i class="fas fa-list"></i> Pendências de atendimento</a></li>
                    <li class="nav-item"><a href="#"><span class="glyphicon glyphicon-file" aria-hidden="true"></span> Relatório de periodicidade diária</a></li>
                </ul>
            </li>
            <li>
                <!-- <a href=" {{-- atendimento') --}} "><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Atendimento</a> -->
            </li>
            <li>
                <a href="#chatbot_menu" data-toggle="collapse" aria-expanded="false"><span class="glyphicon glyphicon-comment" aria-hidden="true"></span> Chatbot</a>
                <ul class="collapse list-unstyled" id="chatbot_menu">
                    <li class="nav-item"><a href="/ChatbotProjetoSJWebJava/chatbot/listar_topicos"><i class="fas fa-sitemap"></i> Perguntas e respostas</a></li>
                    <li><a href="chatbot.configuracoes"><i class="fas fa-cog"></i> Configurações</a></li>
                </ul>
                
            </li>
            <li>
                <a href="relatorio.listar_pendencias"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span> Relatórios personalizados</a>
            </li>
            <li>
                <a href="#"> <span class="glyphicon glyphicon-signal" aria-hidden="true"></span> Análise</a>
            </li>
            <li>
                <a href="#configuracao_menu" data-toggle="collapse" aria-expanded="false"><span class="glyphicon glyphicon glyphicon-cog" aria-hidden="true"></span> Configurações</a>
                <ul class="collapse list-unstyled" id="configuracao_menu">
                    <li><a href="#"> Conta</a></li>
                    <li><a href="#"> Perfil</a></li>
                </ul>
            </li>
        </ul>
    </nav>
	
	<!-- Funções globais -->
	<script>
	function str_replace(find,replaceTo, str){
	      find = find.replace(/[-\/\\^$*+?.()|[\]{}]/g, '\\$&');
	      var re = new RegExp(find, 'g');
	      str = str.replace(re,replaceTo);

	      return str;
	  }
	</script>
	
    <!-- Page Content Holder -->
    <!-- header -->
    <div id="content">
        <nav class="navbar navbar-default" id="menu_horizontal">
            <div class="container">
                <div class="navbar-header">
                    <div class="col-md-2">
                        <button type="button" id="sidebarCollapse" class="btn btn-danger navbar-btn">
                            <i class="glyphicon glyphicon-menu-hamburger"></i>
                        </button>
                    </div>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="nav-item"><a class="nav-link" href="/ChatbotProjetoSJWebJava/dashboard/home">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="#">Relatório</a></li>
                        <li class="nav-item"><a class="nav-link" href="#">Administrativo</a></li>
                        <li class="nav-item"><a class="nav-link" href="#">Configurações</a></li>
                        <li class="nav-item"><a class="nav-link" href="/ChatbotProjetoSJWebJava/autorizacao/logout">Logout</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        
        <!-- @if (!empty($_SESSION['alertas']))
            @foreach ($_SESSION['alertas'] as $key => $alerta)
            <div class="container-row alert alert-$alerta['tipo'] }}" id="alerta$key }}">
                
                $alerta['mensagem'] }}

                <a style="float: right; cursor: pointer;" onclick="remover_alerta($key }})">
                    X
                </a>
            </div>
            @endforeach
        @endif -->
        <div class="container-row">
           
            <div class="container-row">
                <ol class="breadcrumb" style="border: 1px solid #ddd; border-radius: 4px;">
                    
                    <li><a href=""></a>parametro </li>
                    
                </ol>
            </div>