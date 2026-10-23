package hashmaps;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentHashMapExample {
    public static void main(String[] args){
        Map<String, Integer> freq = new ConcurrentHashMap<>();

        String sentence = "Count word frequencies from many threads without losing updates.";
        String[] words = sentence.split(" ");
        for (String w : words) {
            freq.merge(w, 1, Integer::sum);
        }

        // Lazily create a per-key structure
        Map<String, List<String>> groups = new ConcurrentHashMap<>();

        groups.computeIfAbsent("key", k -> new CopyOnWriteArrayList<>())
                .add("key");
        //computeIfAbsent(key, ...) creates a list only when the key is absent. Concurrent calls for the same key use the mapping operation safely.


    }
}
