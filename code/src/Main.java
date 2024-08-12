import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main
{
	public static void main(String[] args)
	{
		List<String> nomes = new ArrayList<>(Arrays.asList("Allan","bruno","Carlos","Pedro"));

		Iterator<String> iterator = nomes.iterator();
		while(iterator.hasNext())
		{
			String element = iterator.next();
			if(element.startsWith("C")) {
				iterator.remove();
			}
		}

		for(String nome : nomes) {
			System.out.println(nome);
		}

	}
}
