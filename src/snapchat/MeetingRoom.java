package snapchat;
import java.util.*;
import java.util.stream.*;

class Interval {
    public int start;
    public int end;
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class MeetingRoom {
    public boolean canAttendMeetings(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0)
            return false;
        if (intervals.size() == 1)
            return true;

        Collections.sort(intervals, (i1, i2) -> i1.start <= i2.start ? -1 : 1);
        Iterator<Interval> iterator = intervals.iterator();
        Interval prev = iterator.next();
        Interval cur = null;
        while (iterator.hasNext()) {
            cur = iterator.next(); 
            if (prev.start >= prev.end || cur.start >= cur.end)
                return false;
            if (prev.start == cur.start && prev.end != cur.end)
                return false;
            if (prev.end > cur.start)
                return false;
        }
        return true;
    }

    public void test() {
        // null or empty intervals
        // start >= end (invalid intervals)
        // duplicate intervals
        // duplicate start intervals
        // duplicate end intervals
    }
}
