<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="../layouts/header_dashboard.jsp"/>
<div class="navbar">
    <a class="btn btn-success" href="chatbot.adicionar_palavra_chave_pergunta">Adicionar tópico principal</a>
</div>
<div style="clear: both;"></div>
<table class="table datatable table-striped" id="popular_tabela_atendimento">
    <thead>
        <tr>
            <th>ID</th>
            <th>Tópico principal</th>
            <th>Data de criação</th>
            <th style="width: 200px;">Ações</th>
        </tr>
    </thead>
    <tbody>

    </tbody>
</table>

<script>
    $(document).ready(function() {
        $('.datatable').DataTable( {
            ajax: '/chatbot/listar_topicos_ajax',
            searching: false,
            bFilter: true,
            info:     false,
            lengthChange: false
        });
    });
</script>
<c:import url="../layouts/footer_dashboard.jsp"/>