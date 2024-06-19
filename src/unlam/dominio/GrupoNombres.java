package unlam.dominio;

import java.util.Comparator;

public class GrupoNombres implements Comparator<String> {

	@Override
	public int compare(String grupo1, String grupo2) {
		int num1 = Integer.parseInt(grupo1.replaceAll("\\D+", ""));
        int num2 = Integer.parseInt(grupo2.replaceAll("\\D+", ""));
        return Integer.compare(num1, num2);
	}

}
