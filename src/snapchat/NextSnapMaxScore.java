package snapchat;
import java.util.*;

class Person {
	public int score;
	public List<Person> nextSnap;
	
	public Person(int score) {
		this.score = score;
		this.nextSnap = new LinkedList<Person>();
	}
	public Person(int score, List<Person> nextSnap) {
		this.score = score;
		this.nextSnap = nextSnap;
	}
}

public class NextSnapMaxScore {
    private static int max;

    public int maxScore(Person person, int maxStep) throws Exception{
        if (person == null || person.nextSnap == null || maxStep < 0)
            throw new Exception();
        
        Set<Person> stepPath = new HashSet<Person>();
        stepPath.add(person);
        max = person.score;
        aux(max, stepPath, person, maxStep);
        return max;
    }

    public void aux(int score, Set<Person> stepPath, Person cur, int maxStep) {
        if (stepPath.size() == maxStep || cur.nextSnap.size() == 0)
            return;
        Optional<Person> tmpMax = cur.nextSnap.stream()
        		.filter(p -> !stepPath.contains(p))
        		.max((p1, p2) -> p1.score > p2.score ? 1 : -1);
        if (!tmpMax.isPresent())
        	return;
        score =  tmpMax.get().score > 0 ? score + tmpMax.get().score : tmpMax.get().score;
        max = Math.max(max, score );
        stepPath.add(cur);
        aux(score, stepPath, tmpMax.get(), maxStep);
    }
}
