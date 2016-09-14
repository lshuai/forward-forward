package leetcode;
import java.util.*;
import java.util.stream.*;

public class CourseSchedule {
	public static void main(String[] args) {
		System.out.println(new CourseSchedule().canFinish(2, new int[][] {
			new int[] {2, 0},
			new int[] {2, 1},
		}));
		List<Integer>[] a = new List[1];
	}
	
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses < 1 
            || prerequisites == null 
            || prerequisites.length == 0 
            || prerequisites[0].length == 0)
            return false;
        
        Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
        for (int[] coursePair: prerequisites) {
            if (!adjList.containsKey(coursePair[0])) 
                adjList.put(coursePair[0], new LinkedList<Integer>(Arrays.asList(coursePair[1])));
            else 
                adjList.get(coursePair[0]).add(coursePair[1]);
            if (!adjList.containsKey(coursePair[1]))
                adjList.put(coursePair[1], new LinkedList<Integer>());
        }
        if (adjList.size() > numCourses)
        	return false;
        Set<Integer> visiting = new HashSet<Integer>();
        Set<Integer> visited = new HashSet<Integer>();
        for (Integer course: adjList.keySet()) {
            if (!dfs(visiting, visited, course, adjList))
                return false;
        }
        return true;
    }

    private boolean dfs(Set<Integer> visiting, 
        Set<Integer> visited, 
        int curCourse, 
        Map<Integer, List<Integer>> adjList) {

        if (visited.contains(curCourse))
            return true;
        if (visiting.contains(curCourse)) 
            return false;

        visiting.add(curCourse);
        for (Integer dep: adjList.get(curCourse)) {
            if (!dfs(visiting, visited, dep, adjList))
                return false;
        }
        visiting.remove(curCourse);
        visited.add(curCourse);
        return true;
    }
}
