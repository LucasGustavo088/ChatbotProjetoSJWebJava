package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import model.Atendimento;
import service.AtendimentoService;
import utils.Filtro;
import utils.QuantidadeAtendimentos;
import utils.Relatorio;

/**
 * Servlet Filter implementation class RelatorioController
 */
@WebFilter("/RelatorioController")
public class RelatorioController implements Filter {

        /**
         * Default constructor. 
         */
        public RelatorioController() {
                // TODO Auto-generated constructor stub
        }

        /**
         * @see Filter#destroy()
         */
        public void destroy() {
                // TODO Auto-generated method stub
        }

        /**
         * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
         */
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                // TODO Auto-generated method stub
                // place your code here

                // pass the request along the filter chain
                chain.doFilter(request, response);
        }

        public void gerarRelatorio(String[] url, ServletRequest request, ServletResponse response) throws ServletException, IOException, ParseException {

                Relatorio relatorio = new Relatorio();
                Filtro filtro = new Filtro();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
                SimpleDateFormat sdfBanco = new SimpleDateFormat("yyyy-MM-dd");
                
                if(request.getParameter("data_de") != null) {
                        filtro.data_de = sdf.parse(request.getParameter("data_de"));
                }

                if(request.getParameter("data_ate") != null) {
                        filtro.data_ate = sdf.parse(request.getParameter("data_ate"));
                }
                System.out.println(sdfBanco.format(filtro.data_ate));
                //Filtro
                relatorio.filtro = filtro;

                //Quantidade de atendimentos
                QuantidadeAtendimentos quantidadeAtendimentos = new QuantidadeAtendimentos();
                quantidadeAtendimentos.primeiraTentativas = 0;
                quantidadeAtendimentos.segundaTentativas = 0;
                quantidadeAtendimentos.terceiraTentativas = 0;
                quantidadeAtendimentos.encaminhamentoHumano = 0;
                relatorio.quantidadeAtendimentos = quantidadeAtendimentos;
                
                //Atendimentos
                System.out.println("WHERE DATA_CRIACAO >= '" + sdfBanco.format(filtro.data_de) + "' AND DATA_CRIACAO <= '" + sdfBanco.format(filtro.data_ate) + " 23:59:59'");
                AtendimentoService as = new AtendimentoService();
                ArrayList<Atendimento> atendimentos = as.
                                carregarCadastroCompleto(
                                                "WHERE DATA_CRIACAO >= '" + sdfBanco.format(filtro.data_de) + "' AND DATA_CRIACAO <= '" + sdfBanco.format(filtro.data_ate) + " 23:59:59'", 
                                                response
                                );
                
                
                for(Atendimento atendimento : atendimentos) {

                        //Quantidade de atendimentos
                        if(atendimento.atendimentoHasPergunta.size() == 1) {
                                relatorio.quantidadeAtendimentos.primeiraTentativas += 1;
                        } else if(atendimento.atendimentoHasPergunta.size() == 2) {
                                relatorio.quantidadeAtendimentos.segundaTentativas += 1;
                        } else if(atendimento.atendimentoHasPergunta.size() == 3) {
                                relatorio.quantidadeAtendimentos.terceiraTentativas += 1;
                        } else if(atendimento.atendimentoHasPergunta.size() > 3) {
                                relatorio.quantidadeAtendimentos.encaminhamentoHumano += 1;
                        }

                        //Duração da interação
                        if(atendimento.getData_finalizacao() != null && atendimento.getData_criacao() != null) {
                                long diferencaData = atendimento.getData_finalizacao().getTime() - atendimento.getData_criacao().getTime();
                                long diffSeconds = diferencaData / 1000 % 60;
                                long diffMinutes = diferencaData / (60 * 1000) % 60;
                                long diffHours = diferencaData / (60 * 60 * 1000) % 24;
                                long diffDays = diferencaData / (24 * 60 * 60 * 1000);
                                atendimento.duracao_atendimento = 
                                                (diffDays != 0 ? diffDays + " dias " : "") 
                                                + (diffHours != 0 ? diffHours + " horas " : "") 
                                                + (diffMinutes != 0 ? diffMinutes + " minutos " : "") 
                                                + (diffSeconds != 0 ? diffSeconds + " segundos " : "");
                        } else {
                                atendimento.duracao_atendimento = "Não finalizado";
                        }
                }
                
                relatorio.atendimentos = atendimentos;
                //Json.jsonEncode(relatorio, response);
                request.setAttribute("filtro", filtro);
                request.setAttribute("relatorio", relatorio);
                request.setAttribute("atendimentos", atendimentos);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/relatorio/gerar_relatorio.jsp");
                dispatcher.forward(request, response);
        }

        /**
         * @see Filter#init(FilterConfig)
         */
        public void init(FilterConfig fConfig) throws ServletException {
                // TODO Auto-generated method stub
        }

}
