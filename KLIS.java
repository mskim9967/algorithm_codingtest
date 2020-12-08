import java.util.*;
import java.io.*;

public class Main{
    int n, k;
    int[] seq, lenCache, cntCache;

    Main(BufferedReader br) throws Exception {
        String[] parse = br.readLine().split(" ");
        n = Integer.parseInt(parse[0]);
        k = Integer.parseInt(parse[1]);
        
        seq = new int[n + 1];
        parse = br.readLine().split(" ");
        seq[0] = 0;
        for(int i = 0; i < n; i++)
            seq[i + 1] = Integer.parseInt(parse[i]);
            
        lenCache = new int[n + 1];
        Arrays.fill(lenCache, -1);
        
        cntCache = new int[n + 1];
        Arrays.fill(cntCache, -1);
    }
    
    int calc_lisLen(int now) {
        if(now > n) return 0;
        if(lenCache[now] != -1) return lenCache[now];

        int ret = 0;
        for(int next = now + 1; next <= n; next++) {
            if(seq[now] < seq[next]) 
                ret = Math.max(ret, 1 + calc_lisLen(next));
        }
        return lenCache[now] = ret;        
    }
    
    int calc_lisCnt(int now) {
        if(now > n) return 0;
        if(cntCache[now] != -1) return cntCache[now];
        
        calc_lisLen(0);
        
        int ret = 0;
        for(int next = now + 1; next <= n; next++) {
            if(lenCache[next] != lenCache[now] - 1)    
                continue;
            if(seq[now] < seq[next])
                ret += 1 + calc_lisCnt(next);
        }
        return cntCache[now] = ret;
    }
   

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        for(int i = 0; i < testCase; i++) {
            Main k = new Main(br);
            bw.write(k.calc_lisCnt(0) + "\n");
            for(int j = 0; j < k.n; j++)
                System.out.print(k.lenCache[j] + " ");
        }
        bw.flush(); bw.close(); br.close();
    }
}
