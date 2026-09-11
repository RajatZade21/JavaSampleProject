package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MergeIntervals {

    public static void main(String[] args){
        int[][] intervals = {
                {1, 3},
                {2, 6},
                {8, 10},
                {9, 15},
                {17, 20}
        };

        List<int[]> result = Arrays.stream(intervals)
                .sorted(Comparator.comparingInt(a -> a[0]))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            List<int[]> merged = new ArrayList<>();

                            list.forEach(interval -> {
                                if(merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]){
                                    merged.add(interval);
                                }
                                else{
                                    merged.get(merged.size() - 1)[1] =
                                            Math.max(
                                                    merged.get(merged.size() - 1)[1],
                                                    interval[1]
                                            );
                                }
                            });
                            return merged;
                        }
                ));

        result.forEach(interval ->
                System.out.println(Arrays.toString(interval))
        );
    }
}
