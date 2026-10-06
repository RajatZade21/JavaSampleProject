package map;

import java.util.HashMap;
import java.util.Map;

public class MapExample1 {
    public static void main(String[] args){
        Map<Integer, String> map = new HashMap<>();

        map.put(1, "A");
        map.put(2, "B");

        //This code will generally throw a ConcurrentModificationException.
        for (Integer key : map.keySet()) {
            map.remove(1);
        }
    }
}
