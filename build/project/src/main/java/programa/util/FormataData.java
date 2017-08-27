package programa.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormataData {

	public static String retornaData(){
		
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		return data.format(new Date());
	}
	
	public static int retornaDia(){
		SimpleDateFormat data = new SimpleDateFormat("dd");
		
		
		return Integer.parseInt(data.format(new Date()));
		
	}
	
	public static int retornaMes() {

		SimpleDateFormat data = new SimpleDateFormat("MM");
		
		
		return Integer.parseInt(data.format(new Date()));
	}

	public static int retornaAno() {

		SimpleDateFormat data = new SimpleDateFormat("yyyy");

		return Integer.parseInt(data.format(new Date()));
	}
	
}
