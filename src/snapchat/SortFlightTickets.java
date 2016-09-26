package snapchat;
import java.util.*;

class Flight {
    public String from;
    public String to;

    public Flight(String from, String to) {
        this.from = from;
        this.to = to;
    }
    
    @Override
    public String toString() {
        return "[" + this.from + "," + this.to + "]";
    }
}

public class SortFlightTickets {
	public static void main(String[] args) {
	    System.out.println(new SortFlightTickets().sort(Arrays.asList(
	            new Flight("LAX", "SEA"), new Flight("POR", "CHI"), new Flight("SEA", "POR"), new Flight("CHI", "NYC")), 
	            "LAX", "NYC"));
	}
	
    public List<Flight> sort(List<Flight> flights, String start, String end) {
        List<Flight> res = new LinkedList<Flight>();
        if (flights == null || flights.size() == 0)
            return res;

        Map<String, String> fromTo = new HashMap<String, String>();
        for (Flight flight: flights) {
            if (fromTo.containsKey(flight.from))
                return res;
            fromTo.put(flight.from, flight.to);
        }
        String from = start;
        String to = "";
        while (!fromTo.isEmpty()) {
            to = fromTo.get(from);
            res.add(new Flight(from, to));
            fromTo.remove(from);
            from = to;
        }
        if (!to.equals(end))
            return new LinkedList<Flight>();
        return res;
    }
}
