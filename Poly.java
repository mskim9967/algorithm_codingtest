import java.util.*;
import java.io.*;

public class Main{
    int sqCnt;
    int[][] cache;
    final int MOD = 10000000;
    
    Main(BufferedReader br) throws Exception {
        sqCnt = Integer.parseInt(br.readLine());
        cache = new int[sqCnt][sqCnt];
    }

    int count_poly(int n, int k) {
        if(n == k)  return 1;
        if(cache[n - 1][k - 1] != 0)    return cache[n - 1][k - 1];
        
        int ret = 0;
        for(int i = 1; i <= n - k; i++)
            ret = (ret + (i + k - 1) * count_poly(n - k, i)) %  MOD;
        return cache[n - 1][k - 1] = ret;
    }
   
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        for(int i = 0; i < testCase; i++) {
            Main p = new Main(br);
            int ans = 0;
            for(int j = 1; j <= p.sqCnt; j++) 
                ans = (ans + p.count_poly(p.sqCnt, j)) % p.MOD;
            bw.write(ans + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
