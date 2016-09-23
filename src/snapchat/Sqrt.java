package snapchat;
import java.util.*;

public class Sqrt {
    public static int mySqrt(int x) {
        if (x <= 0)
            return 0;

        long l = 0;
        long r = 2/x + 1;
        while (l <= r) {
            long mid = l + (r - l) / 2;
            long sqrt = mid * mid;
            if (sqrt == x)
                return (long)mid;
            if (sqrt < x) 
                l = mid + 1;
            else
                r = mid - 1;
        }
        return (long)r;
    }
}
