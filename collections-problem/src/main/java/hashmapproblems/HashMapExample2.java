package hashmapproblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//Convert array to map based on split
public class HashMapExample2 {
    public static void main(String[] args){
        List l = new ArrayList<>();
        l.add("/c1/12/c2/200/c3/23");
        l.add("/c1/299/c5/103");
        //O/p : c1=[12,299] c2=[200] c3=[23] c5=[103]

        HashMap<String,List<String>> map = new HashMap<>();
        for(Object obj:l){
            String[] str = ((String)obj).split("/");
            int counter=1;
            for(int i=0;i < str.length / 2;i++){
                String key = str[counter++];
                String value = str[counter++];
                if(map.containsKey(key)){
                    List<String> list = map.get(key);
                    list.add(value);
                    map.put(key,list);
                }
                else{
                    map.put(key,new ArrayList<String>(Arrays.asList(value)));
                }
            }
        }
        System.out.println(map);
    }
}
