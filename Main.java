import java.util.*;
import java.io.*;

public class Main{
    int n, k, m;
    int[] L, Q, LL;
    double[][] T, cache;

    Main(BufferedReader br) throws Exception {
        String[] parse = br.readLine().split(" ");
        n = Integer.parseInt(parse[0]); //# of song
        k = Integer.parseInt(parse[1]); //minute
        m = Integer.parseInt(parse[2]); //# of favorite song

        L = new int[n]; //song length
        parse = br.readLine().split(" ");
        for(int i = 0; i < n; i++)
            L[i] = Integer.parseInt(parse[i]);
        
        LL = L.clone();

        T = new double[n][n]; //probabilty with log
        for(int i = 0; i < n; i++) {
            parse = br.readLine().split(" ");
            for(int j = 0; j < n; j++)
                T[i][j] = Double.parseDouble(parse[j]);
        }
        
        Q = new int[m]; //favorite song
        parse = br.readLine().split(" ");
        for(int i = 0; i < m; i++)
            Q[i] = Integer.parseInt(parse[i]);
    }

    double calc_prob(int aimed, int min, int now) {
        if(min == k)    return (now == aimed) ? 1.0 : 0.0;
        if(cache[min][now] < 1)    return cache[min][now];

        double ret = 0.0;

        if(LL[now] != 0) {
            LL[now]--;
            ret = calc_prob(aimed, min + 1, now);
        }
        else {
            LL[now] = L[now];
            for(int next = 0; next < n; next++) {
                    ret += T[now][next] * calc_prob(aimed, min + 1, next);
                
            }
        }
        return cache[min][now] = ret;
    }

    void write_answer(BufferedWriter bw) throws Exception {
        for(int i = 0; i < m; i++) {
            cache = new double[k + 1][n];
            for(double[] x : cache)
                Arrays.fill(x, Double.MAX_VALUE);
            bw.write(calc_prob(Q[i], 0, 0) + " ");
        }
        bw.write("\n");
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        for(int i = 0; i < testCase; i++) {
            Main d = new Main(br);
            d.write_answer(bw);
        }
        bw.flush(); bw.close(); br.close();
    }
}

