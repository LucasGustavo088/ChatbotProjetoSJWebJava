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
            dayNames: ['Domingo','Segunda','Ter�a','Quarta','Quinta','Sexta','S�bado'],
            dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
            dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','S�b','Dom'],
            monthNames: ['Janeiro','Fevereiro','Mar�o','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
            monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
            nextText: 'Pr�ximo',
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



