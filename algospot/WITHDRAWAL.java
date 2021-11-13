import java.util.*;
import java.io.*;

public class Main{
    class Score implements Comparable<Score> {
        int r, c;
        double sub;
        Score(int r, int c) { this.r = r; this.c = c; }
        public int compareTo(Score o) { 
            if(o.sub - this.sub > 0.0) return 1;
            else return -1;
        }
    }
    int n, k;
    ArrayList<Score> arr;

    Main() {
        arr = new ArrayList<Score>();
    }

    void init(BufferedReader br) throws Exception {
        String[] parse = br.readLine().split(" ");
        n = Integer.parseInt(parse[0]);
        k = Integer.parseInt(parse[1]);
        
        parse = br.readLine().split(" ");
        for(int i = 0; i < n; i++)
            arr.add(new Score(Integer.parseInt(parse[i * 2]), Integer.parseInt(parse[i * 2 + 1])));
    }

    void clear() {
        arr.clear();
    }

    boolean isPositive(double x) {
        double sum = 0.0;
        PriorityQueue<Score> cp = new PriorityQueue<Score>();
        for(Score s : arr) {
            s.sub = x * s.c - (double)s.r;
            cp.add(s);
        }
        
        for(int i = 0; i < k; i++) {
            Score pop = cp.poll();
            sum += pop.sub;
        }
        return sum >= 0.0;
    }

    double ans(double min, double max) {
        if(Math.round(min * 100000000) == Math.round(max * 100000000)) return min;
        double mid = (min + max) / 2;
        return isPositive(mid) ? ans(min, mid) : ans(mid, max);
    }
    void solve(BufferedReader br, BufferedWriter bw) throws Exception {
        init(br);
        bw.write(ans(0.0, 1.0) + "\n");
        clear();
    }
    
    public static void main(String[] args) throws Exception {
       // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        Main l = new Main();
        for(int i = 0; i < testCase; i++)
            l.solve(br, bw);
        bw.flush(); bw.close(); br.close();
    }
}
