import java.util.*;
import java.io.*;

public class Main{
    long n, m;
    int pov, depth;

    Main() {
    }

    void init(BufferedReader br) throws Exception {
        String[] parse = br.readLine().split(" ");
        n = Integer.parseInt(parse[0]);
        m = Integer.parseInt(parse[1]);
        pov = (int)(((double)(m * 100) / n));
        depth = 0;
    }

    void clear() {
    }

    int ans(int min, int max) {
        if(++depth == 33) return -1;
        if(min == max) return min;
        int mid = (min + max) / 2;
        return ((double)(mid + m) / (n + mid)) >= ((pov + 1) / 100.0) ? ans(min, mid) : ans(mid + 1, max);
    }
    void solve(BufferedReader br, BufferedWriter bw) throws Exception {
        init(br);
        bw.write(ans(0, 2000000000) + "\n");
        clear();
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        Main l = new Main();
        for(int i = 0; i < testCase; i++)
            l.solve(br, bw);
        bw.flush(); bw.close(); br.close();
    }

}