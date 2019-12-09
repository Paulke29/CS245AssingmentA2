import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadFile {
    private String filename;
    private BufferedReader br = null;
    private Map<String, Map<String, String>> ResultMap = new HashMap<String, Map<String, String>>();
    private List<String> ResultList = new ArrayList<String>();
    ReadFile(String filename){
        this.filename= filename;
    }
    public void read()throws Exception{
        FileReader fr = new FileReader(filename);
        br = new BufferedReader(fr);
        String s = br.readLine();
        //System.out.println(s);
        while((s = br.readLine()) != null){
            int pos1 = s.indexOf("[{");
            if(pos1 == -1)
                continue;

            s = s.substring(pos1);
            //System.out.println(s);

            List<String> list = new ArrayList<String>();

            int pos3 = s.indexOf("}]");
            if(s.indexOf("cast_id") != -1) {
                String cast = s.substring(0, pos3 + 2);
                cast = cast.replace("\"\"", "\"");
                //System.out.println("cast: " + cast);

                JSONArray data1 = (JSONArray) new JSONParser().parse(cast);
                for(int i = 0; i < data1.size(); i++) {
                    list.add(((JSONObject) data1.get(i)).get("name").toString());
                }

                String temp = s.substring(pos3 + 2);
                if(temp.indexOf("credit_id") != -1) {
                    String credit = s.substring(pos3 + 5, s.length() - 1);
                    credit = credit.replace("\"\"", "\"");
                    //System.out.println("credit: " + credit);

                    JSONArray data2 = (JSONArray) new JSONParser().parse(credit);
                    for(int i = 0; i < data2.size(); i++) {
                        list.add(((JSONObject) data2.get(i)).get("name").toString());
                    }
                }
            }else {
                if(s.indexOf("credit_id") != -1) {
                    String credit = s.substring(0, pos3 + 2);
                    credit = credit.replace("\"\"", "\"");

                    JSONArray data1 = (JSONArray) new JSONParser().parse(credit);
                    for(int i = 0; i < data1.size(); i++) {
                        list.add(((JSONObject) data1.get(i)).get("name").toString());
                    }
                }
            }
            for(int i = 0; i < list.size(); i++) {
                String str1 = list.get(i);
                if(!ResultMap.containsKey(str1)) {
                    ResultMap.put(str1, new HashMap<String, String>());
                }

                Map<String, String> temp = ResultMap.get(str1);
                for(int j = 0; j < list.size(); j++) {
                    String str2 = list.get(j);
                    if(i != j) {
                        if(!temp.containsKey(str2)) {
                            temp.put(str2, str1);
                        }
                    }
                }
            }
        }
    }

    public Map<String, Map<String, String>> getResultMap(){
        return ResultMap;
    }
    public List<String> getResultList(){
        return this.ResultList;
    }

}
