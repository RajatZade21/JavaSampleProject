package collections.arrays;

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

        List<int[]> result = mergerIntervalUsingStream(intervals);

        List<int[]> result2 = mergeInterval(intervals);

        result.forEach(interval ->
                System.out.println(Arrays.toString(interval))
        );
    }

    static List<int[]> mergerIntervalUsingStream(int[][] intervals){
        return Arrays.stream(intervals)
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
    }

    static List<int[]> mergeInterval(int[][] intervals){
        if (intervals == null || intervals.length <= 1) {
            return new ArrayList<>();
        }

        // Step 1: Sort by start time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> result = new ArrayList<>();

        // Step 2: Start with the first interval
        int start = intervals[0][0];
        int end = intervals[0][1];

        // Step 3: Merge overlapping intervals
        for (int i = 1; i < intervals.length; i++) {

            int currentStart = intervals[i][0];
            int currentEnd = intervals[i][1];

            if (currentStart <= end) {
                // Overlapping: extend the current interval
                end = Math.max(end, currentEnd);
            }
            else{
                // No overlap: save the previous interval
                result.add(new int[]{start, end});

                start = currentStart;
                end = currentEnd;
            }
        }

        // Step 4: Add the last merged interval
        result.add(new int[]{start, end});

        return result;
    }
}
