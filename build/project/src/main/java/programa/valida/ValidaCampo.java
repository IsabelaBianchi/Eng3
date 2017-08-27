package programa.valida;

public class ValidaCampo {

	public static boolean isEmpty(String string) {

		if (string.equals("")) {
			return true;
		} else
			return false;

	}

	public static boolean contemEspaco(String string) {

		if (string.indexOf(' ') != -1) {
			return true;
		} else
			return false;

	}

	public static boolean comecaCom(String string) {

		if (string.startsWith("[")) {
			return true;
		} else
			return false;

	}

	public static boolean comecaComEspaco(String string) {

		if (string.startsWith(" ")) {
			return true;
		} else
			return false;

	}

}
