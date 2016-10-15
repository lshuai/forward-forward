package review.pinterest;
import java.util.*;

class Task {
    public int id;
    public void execute() {};
    public List<Task> deps() {return new LinkedList<Task>();};
}

public class TaskDependencies {
    public List<List<Task>> order(List<Task> tasks) throws Exception{
        Set<Task> visited = new HashSet<Task>();
        Stack<Task> stack = new Stack<Task>();
        Set<Task> visiting = new HashSet<Task>();
        List<List<Task>> res = new LinkedList<List<Task>>();
        for (Task task: tasks) {
            if (visited.contains(task))
                continue;
            if (!dfs(visited, visiting, stack, task, res))
                throw new Exception("Cycle exists!");
        }
        return res;
    }
    
    private static boolean dfs(Set<Task> visited, 
            Set<Task> visiting, 
            Stack<Task> stack, 
            Task task, 
            List<List<Task>> res) {
        if (visiting.contains(task))
            return false;
        visited.add(task);
        visiting.add(task);
        task.execute();
        for (Task dep: task.deps()) {
            if (visited.contains(task))
                continue;
            if (!dfs(visited, visiting, stack, dep, res))
                return false;
        }
        stack.push(task);
        visiting.remove(task);
        if (visiting.isEmpty()) {
            List<Task> sorted = new LinkedList<Task>();
            while (!stack.isEmpty())
                sorted.add(stack.pop());
            res.add(sorted);
        }
        return true;
    }
}
