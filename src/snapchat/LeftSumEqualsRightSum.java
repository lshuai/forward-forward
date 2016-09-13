package snapchat;
import java.util.*;

public class LeftSumEqualsRightSum {    
    public int get(int[] a) {
        if (a == null || a.length == 0)
            return -1;
        
        int sum = 0;
        for (int i = 0; i < a.length; i++)
            sum =+ a[i];
        int left = 0;
        int right = sum;
        for (int i = 0; i < a.length; i++) {
            left =+ a[i - 1];
            if (i > 0)
                right =- a[i - 1];
            if (left == right)
                return i;
        }
        return -1;
    }
    
    public void test() {
    	int[] a = null;
    	a = new int[0];
    	a = new int[] {1};
    	a = new int[] {0, -2, 2};
    }
}
