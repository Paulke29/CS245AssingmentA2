
import java.util.Map;
public class A2 {

	public static void main(String[] args) throws Exception{
		//String file = "src/tmdb_5000_credits.csv";
		String file = args[0];
		ReadFile readFile = new ReadFile(file);
		readFile.read();
		Map<String, Map<String, String>> map = readFile.getResultMap();
		Matchup matchup = new Matchup(map);
		matchup.getResult();
	}
}
