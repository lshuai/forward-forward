package leetcode;
import java.util.*;

class Ugly {
    public int p;
    public int[] res;
    public int pi;
    
    public Ugly(int p, int[] res, int pi) {
        this.p = p;
        this.res = res;
        this.pi = pi;
    }
    
    public int val() {
        return this.res[this.pi] * this.p;
    }
} 

public class UglyNumberII {
    public static void main(String[] args) {
        UglyNumberII u = new UglyNumberII();
        System.out.println(u.primes(100));
        System.out.println(u.nthSuper(7, new int[] {2, 3, 5}));
        
    }
    
    public int nthHeap(int n, int[] primes) {
        int[] res = new int[n];
        res[0] = 1;
        PriorityQueue<Ugly> heap = new PriorityQueue<Ugly>((a, b) -> a.val() - b.val());
        heap.offer(new Ugly(1, res, 0));
        for (int i = 0; i < primes.length; i++) 
            heap.offer(new Ugly(primes[0], res, 0));
        for (int i = 1; i < n; i++) {
            Ugly tmp = heap.poll();
            while (!heap.isEmpty() && tmp.val() == heap.peek().val()) {
                Ugly dup = heap.poll();
                heap.offer(new Ugly(dup.p, res, dup.pi + 1));
            }
            res[i] = tmp.val();
        }
        
        return res[n - 1];
    }
    
    public int nthUglyNumber(int n) {
        int[] primes = new int[n];
        int i2 = 0, i3 = 0, i5 = 0;
        primes[0] = 1;
        for (int i = 1; i < n; i++) {
            int p2 = primes[i2] * 2;
            int p3 = primes[i3] * 3;
            int p5 = primes[i5] * 5;
            int min = Math.min(Math.min(p2, p3), p5);
            primes[i] = min;
            if (min == p2)
                i2++;
            if (min == p3)
                i3++;
            if (min == p5)
                i5++;
        }

        return primes[n - 1];
    }
    
    public List<Integer> primes(int n) {
        if (n <= 0)
            return new ArrayList<Integer>();
        ArrayList<Integer> res = new ArrayList<Integer>();
        res.add(1);
        int count = 1;
        int candi = 2;
        while (count < n) {
            int curLen = res.size();
            boolean isPrime = true;
            for (int i = 0; i < curLen; i++) {
                int p = res.get(i);
                if (p != 1 && candi % p == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                res.add(candi);
                count++;
            }
            candi++;
        }
        return res;
    }
    
    public int nthSuper(int n, int[] primes) {
        int[] res = new int[n];
        res[0] = 1;
        int[] pi = new int[primes.length];
        
        for (int i = 1; i < n; i++) {
            res[i] = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) 
                res[i] = Math.min(res[i], res[pi[j]] * primes[j]);
            
            for (int j = 0; j < primes.length; j++)
                if (res[pi[j]] * primes[j] == res[i])
                    pi[j]++;
        }
        
        return res[n - 1];
    }
    
    public int nthSuperUglyNumber(int n, int[] primes) {
        int [] res = new int[n];
        res[0] = 1;
        int [] cur = new int[primes.length];
        
        for(int i = 1; i < n; i++){
            res[i] = Integer.MAX_VALUE;
            for(int j = 0; j < primes.length; j++){
                if (primes[j] * res[cur[j]] == res[i-1]) {
                    cur[j]++;
                }
                res[i] = Math.min(res[i], primes[j] * res[cur[j]]);
            }
        }
        return res[n-1];
    }
}
