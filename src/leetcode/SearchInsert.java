package leetcode;
import java.util.*;

public class SearchInsert {
    public static void main(String[] args) {
        SearchInsert si = new SearchInsert();
        System.out.println(si.searchInsert(new int[] {1,2,4,6,7}, 3));
        System.out.println(si.searchInsert(new int[] {1,3,5,6}, 2));
        System.out.println(si.searchInsert(new int[] {1,3,5,6}, 5));
        System.out.println(si.searchInsert(new int[] {1,3,5,6}, 7));
        System.out.println(si.searchInsert(new int[] {1,3,5,6}, 0));
        System.out.println(si.searchInsert(new int[] {1,3}, 2));
    }
    
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int cur = nums[mid];
            if (cur == target)
                return mid;
            if (cur > target) {
                if (mid == 0 || nums[mid - 1] < target )
                    return mid;
                else 
                    r = mid - 1;
            }
            else {
                if (mid == nums.length - 1 || nums[mid + 1] > target)
                    return mid + 1;
                else
                    l = mid + 1; 
            }
        }
        // never hit this line
        return -1;
    }
}
