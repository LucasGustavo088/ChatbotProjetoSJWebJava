package utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class Debug {

	public static void debug(Object obj, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(obj);
		out.write(jsonString);
	}
			
}
