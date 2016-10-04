import java.util.*;
import java.util.stream.*;

class Query {
    public long timeStamp;
    public String val;

    public Query(String val) {
        this.val = val;
        this.timeStamp = Calendar.getInstance().getTimeInMillis();
    }
}

public class Throttle {
    private final int qps;

    private final Queue<Query> queryDeq;

    public Throttle(int qps) {
        this.qps = qps; 
        this.queryDeq = new LinkedList<Query>();
    }

    public boolean addQuery(Query query) {
        if (this.queryDeq.size() < this.qps) {
            this.queryDeq.offer(query);
            return true;
        }
        if (withInQps(this.queryDeq.peek().timeStamp)) {
            queryDeq.poll();
            queryDeq.offer(query);
            return true;
        }
        return false;
    }
    
    private boolean withInQps(long timeStamp) {
        return (Calendar.getInstance().getTimeInMillis() - timeStamp) < 1000;
    }
}
