package snapchat;

public class ReverseInteger {
    public static void main(String[] args) {
        System.out.println(new ReverseInteger().reverse(-1234));
    }
    
    public int reverse(int x) {
        int old = x;
        x = Math.abs(x);
        int res = 0;
        int digit = 1;
        while (x > 0) {
            digit = x % 10;
            res = res * 10 + digit;
            x /= 10;
        }
        
        return old > 0 ? res : res * -1;
    }
}
