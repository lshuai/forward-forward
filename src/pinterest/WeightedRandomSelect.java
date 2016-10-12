package pinterest;
import java.util.*;

class WeightedItem {
    public double weight;

    public int val;
    
    public WeightedItem(double weight, int val) {
        this.weight = weight;
        this.val = val;
    }
}

public class WeightedRandomSelect {
    public static void main(String[] args) {
        List<WeightedItem> items = Arrays.asList(new WeightedItem(1.0, 1), new WeightedItem(1.0, 2), new WeightedItem(2.0,3));
        System.out.println(new WeightedRandomSelect(items).random());
    }
    
    private List<Double> accuWeightedList;

    private WeightedItem[] items;

    public WeightedRandomSelect(List<WeightedItem> items) {
        // construct array items
        this.items = new WeightedItem[items.size()];
        items.toArray(this.items);
        // build accu list
        this.accuWeightedList = new ArrayList<Double>(this.items.length);
        double sum = .0;
        for (int i = 0; i < this.items.length; i++) {
            sum += this.items[i].weight; 
            this.accuWeightedList.add(sum);
        }
    }

    public int random() {
        double rand = Math.random() * this.accuWeightedList.get(this.accuWeightedList.size() - 1); 
        int l = 0;
        int r = this.accuWeightedList.size() - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            double weight = this.accuWeightedList.get(mid);
            if (weight == rand)
                return this.items[mid].val;
            else if (weight > rand) {
                if (mid == 0 || this.accuWeightedList.get(mid - 1) < rand)
                    return this.items[mid].val;
                r = mid - 1;
            }
            else
                l = mid + 1;
        }
        // will never hit this line
        return 0;
    }
}
