package snapchat;

public class MinInRotatedII {
    public static void main(String[] args)  {
        System.out.println(new MinInRotatedII().findMin(new int[] {4,5,6,0,1,1,2,2,2,2,3,3}));
    }
    
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right])
                right = mid;
            else if(nums[mid] > nums[right])
                left = mid + 1;
            else if (nums[left] > nums[right])
                left = mid + 1;
            else if (nums[left] < nums[right])
                right = mid;
            else
                return nums[left];
        }
        return nums[left];
    }
}
