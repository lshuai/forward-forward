package pinterest;
import java.util.*;
import java.util.stream.*;

class Interval {
    public int start;
    public int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class MeetingRoomII {
    public int minRooms(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0)
            return -1;
        return intervals.size() - merge(intervals).size();
    }
    
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0)
            return new ArrayList<Interval>();

        Collections.sort(intervals, (i1, i2) -> i1.start < i2.start ? -1 : (i1.start == i2.start ? 0 : 1));
        int m = 0; 
        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).start == intervals.get(m).start || intervals.get(m).end >= intervals.get(i).start)  
                intervals.set(m, new Interval(intervals.get(m).start, Math.max(intervals.get(i).end, intervals.get(m).end)));
            else
                intervals.set(++m, intervals.get(i));
        }
        
        return intervals.subList(0, m + 1);
    }
}
