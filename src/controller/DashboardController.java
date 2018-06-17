package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Atendimento;
import service.AtendimentoService;
import utils.Debug;
import utils.ResultadosAjax;

/**
 * Servlet Filter implementation class DashboardController
 */
@WebFilter("/DashboardController")
public class DashboardController implements Filter {

        /**
         * Default constructor. 
         */
        public DashboardController() {

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

        public void home(String[] url, ServletRequest request, ServletResponse response) throws ServletException, IOException {

                RequestDispatcher dispatcher = request
                                .getRequestDispatcher("/dashboard/home.jsp");
                dispatcher.forward(request, response);

        }

        public void listarPendenciasAjax(String[] url, ServletRequest request, ServletResponse response) throws ServletException, IOException {

                AtendimentoService atendimentoService = new AtendimentoService();
                ArrayList<Atendimento> atendimentos = atendimentoService.carregarCadastroCompleto("", response);

                ArrayList<ArrayList> aaData = new ArrayList<ArrayList>();
                for(Atendimento atendimento : atendimentos) {
                        String botao_atender = " <a onclick='redirecionar_para_atendimento(" + atendimento.getId() + ");' class='btn btn-success'> Atender</a>"; 

                        String status = "<strong>Não finalizado</strong>";
                        
                        if(atendimento.getStatus().equals("finalizado")) {
                                status = "<strong><span style='color: green'>Finalizado</span></strong>";
                                botao_atender = " <a onclick='redirecionar_para_atendimento(" + atendimento.getId() + ");' class='btn btn-primary'> Visualizar</a>";
                        } else if(atendimento.getStatus().equals("atendimento_iniciado")) {
                                status = "<strong>Atendimento iniciado</strong>";
                        } else {
                                botao_atender = " <a onclick='redirecionar_para_atendimento(" + atendimento.getId() + ");' class='btn btn-primary'> Visualizar</a>";
                        }
                        
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy | HH:mm:ss");

                        ArrayList<String> data = new ArrayList<String>();
                        data.add(String.valueOf(atendimento.getId()));
                        data.add(atendimento.cliente.getNome_cliente());
                        data.add(atendimento.cliente.getEmail_cliente());
                        data.add(sdf.format(atendimento.getData_criacao()));
                        data.add(status);
                        data.add(botao_atender);
                        
                        aaData.add(data);

                }
                
                ResultadosAjax resultado = new ResultadosAjax();
                resultado.aaData = aaData;
                resultado.iTotalDisplayRecords = aaData.size();
                resultado.iTotalRecords = aaData.size();
                resultado.sEcho = 1;
                Debug.debug(resultado, response);
        }

        public void atendimento(String[] url, ServletRequest request, ServletResponse response) throws ServletException, IOException {

                RequestDispatcher dispatcher = request
                                .getRequestDispatcher("/dashboard/atendimento.jsp");
                request.setAttribute("id_atendimento", url[2]);
                dispatcher.forward(request, response);
        }

        /**
         * @see Filter#init(FilterConfig)
         */
        public void init(FilterConfig fConfig) throws ServletException {

        }

}
