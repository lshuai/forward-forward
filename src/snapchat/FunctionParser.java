package snapchat;
import java.util.*;
import java.util.stream.*;

class Function {

    private final String name;

    public String open;

    public String close;

    public final List<Function> funcChild;

    public Function(LogEntry entry) throws Exception{
        if (!entry.start())
            throw new Exception();
        this.name = entry.name;
        this.open = entry.timeStamp;
        this.funcChild = new ArrayList<Function>();
    }

    public void print() {
        if (this.funcChild.isEmpty())        
            System.out.println(this.name + " " + "[" + this.open + "," + this.close + "]");
        else
            System.out.println(this.name + " " + "[" + this.open + "," + this.funcChild.get(0).open + "]" + " "
                 + "[" + this.funcChild.get(this.funcChild.size() - 1).open + "," + this.close + "]");
    }
}

class LogEntry {
    public String name;

    public String state;

    public String timeStamp;

    public LogEntry(String entry) {
        List<String> cols = new ArrayList<String>(Arrays.asList(entry.split("\t")))
            .stream()
            .map(e -> e.trim())
            .collect(Collectors.toList());
        this.name = cols.get(0);
        this.state = cols.get(1);
        this.timeStamp = cols.get(2);
    }
    
    public boolean start() {
        return this.state.equals("start");
    }
}

public class FunctionParser {
	public static void main(String[] args) {
		List<String> log = Arrays.asList("f1	start	1"
				,"f2	start	3"
				,"f2	end	5"
				,"f1	end	7"
				,"f3	start	9"
				,"f3	end	10"
				);
		try {
			parse(log);
		}
		catch (Exception ex) {
			
		}
	}
	
    public static void parse(List<String> log) throws Exception {
        if (log == null || log.size() == 0)
            return;

        Stack<Function> funcStack = new Stack<Function>();
        for (String logLine: log) {
            LogEntry entry = new LogEntry(logLine);
            if (funcStack.isEmpty() || entry.start()) {
                Function f = new Function(entry);
                if (!funcStack.isEmpty())
                	funcStack.peek().funcChild.add(f);
                funcStack.push(f);
            }
            else {
                Function topFunc = funcStack.pop();
                topFunc.close = entry.timeStamp;
                topFunc.print();
            }
        }
    }
}
