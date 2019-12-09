import java.util.*;

public class Matchup {
    private Map<String, Map<String, String>> map = new HashMap<>();
    private String actor1;
    private String actor2;
    Matchup(Map map){
        this.map= map;
    }
    public void getResult(){
        Scanner sc = new Scanner(System.in);
        while(true) {
            while(true) {
                System.out.print("Actor 1 name: ");
                actor1 = sc.nextLine();
                if(map.containsKey(actor1)) {
                    break;
                }else {
                    System.out.println("No such actor.");
                }
            }

            while(true) {
                System.out.print("Actor 2 name: ");
                actor2 = sc.nextLine();
                if(map.containsKey(actor2)) {
                    break;
                }else {
                    System.out.println("No such actor.");
                }
            }
            // bfs
            Map<String, String> parent = new HashMap<String, String>();

            List<String> queue = new ArrayList<String>();
            queue.add(actor1);
            Map<String, Boolean> seen = new HashMap<String, Boolean>();
            seen.put(actor1, true);

            while(queue.size() > 0) {
                String cur = queue.remove(0);

                Map<String, String> hm = map.get(cur);
                Iterator<Map.Entry<String, String>> iter = hm.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<String, String> entry = (Map.Entry<String, String>) iter.next();
                    String key = entry.getKey();
                    String val = entry.getValue();

                    if(!seen.containsKey(key)) {
                        parent.put(key, cur);

                        queue.add(key);
                        seen.put(key, true);
                    }
                }
            }
            List<String> dd = new ArrayList<String>();
            dd.add(actor2);
            String res = parent.get(actor2);
            while(!res.equals(actor1)) {
                dd.add(res);
                res = parent.get(res);
            }
            dd.add(actor1);


            for(int i = dd.size() - 1; i >= 0; i--) {
                if(i == 0) {
                    System.out.println(dd.get(i));
                }else {
                    System.out.print(dd.get(i) + " --> ");
                }
            }
        }
    }
}
