package review.pinterest;
import java.util.*;

class Interval {
    public int start;
    public int end;
    public int cost;

    public Interval(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }
    
    public Interval(int start, int end) {
        this(start, end, 0);
    }
}

public class MaxCpuCost {
    public static int max(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0)
            return -1;

        Collections.sort(intervals, (a, b) -> a.start - b.start);
        PriorityQueue<Interval> priorityQueue = new PriorityQueue<Interval>((a, b) -> a.end - b.end);
        int max = 0;
        for (Interval interval: intervals) {
            if (priorityQueue.isEmpty() || priorityQueue.peek().end <= interval.start) 
                priorityQueue.offer(interval);
            else {
                int tmpMax = 0;
                while (!priorityQueue.isEmpty() && priorityQueue.peek().end > interval.start) 
                    tmpMax += priorityQueue.poll().cost; 
                max = Math.max(max, tmpMax);
            }
        }

        return max;
    }
}