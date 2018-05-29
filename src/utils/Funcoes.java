package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Funcoes {
	
	public static String dataAtual() {
	    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
	}
}
