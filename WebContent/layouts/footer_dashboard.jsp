</div>
        
    </div>
</div>
<script>
    $(document).ready(function () {
        $('#sidebarCollapse').on('click', function () {
            $('#sidebar').toggleClass('active');
            $(this).toggleClass('active');
        });

        $(".datepicker").datepicker({
            dateFormat: 'dd/mm/yy',
            dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
            dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
            dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
            monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
            monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
            nextText: 'Próximo',
            prevText: 'Anterior'
        });

    });

    function str_replace(find,replaceTo, str){
        find = find.replace(/[-\/\\^$*+?.()|[\]{}]/g, '\\$&');
        var re = new RegExp(find, 'g');
        str = str.replace(re,replaceTo);

        return str;
    }

    function remover_alerta(id) {
        $('#alerta' + id).remove();

        $.ajax({
            url: '/utilizador/remover_alerta/' + id,
            dataType: 'json',
            method: 'get',
            data: {
                '_token': "{{ csrf_token() }}"
            },
        });
    }

</script>
</body>
</html>



