package utils;

import java.util.ArrayList;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Alerta {
	public String mensagem;
	public String tipo;
	
	public void alerta(String mensagem, String tipo, ServletResponse request) {

        /*if(!isset($_SESSION)){
            session_start();
        }*/
		
		HttpServletRequest req = (HttpServletRequest) request;
	    HttpSession session = req.getSession(false);

        if(tipo == null) {
            tipo = "warning";
        } else if(tipo == "error" || tipo == "erro") {
            tipo = "danger";
        }
        
        
        ArrayList<Alerta> alertas = null;
        
    } 
}
