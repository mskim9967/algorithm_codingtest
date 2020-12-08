import java.util.*;
import java.io.*;

public class Main{
    int n, k, lisLen;
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
        if(lenCache[now] != -1) return lenCache[now];

        int ret = 1;
        for(int next = now + 1; next <= n; next++) {
            if(seq[now] < seq[next]) 
                ret = Math.max(ret, 1 + calc_lisLen(next));
        }
        return lenCache[now] = ret;        
    }
    
    int calc_lisCnt(int now) {
        if(lenCache[now] == 1) return cntCache[now] = 1;
        if(cntCache[now] != -1) return cntCache[now];
        
        int ret = 0;
        for(int next = now + 1; next <= n; next++) {
            if(lenCache[next] != lenCache[now] - 1)
                continue;
            if(seq[now] < seq[next])
                ret += calc_lisCnt(next);
        }
        return cntCache[now] = ret;
    }

    void print(int len) {
        class Pair implements Comparable<Pair> {
            int a, b;
            Pair(int a, int b) { this.a = a; this.b = b; }
            public int compareTo(Pair o) { return this.a > o.a ? 1 : -1; }
        }
        
        if(len == 0)    return;
        
        Vector<Pair> picked = new Vector<Pair>();
        for(int i = 1; i <= n; i++) {
            if(lenCache[i] == len)
                picked.add(new Pair(seq[i], i));
        }
   
        Collections.sort(picked);
    
        while(picked.size() != 0) {
            Pair pop = picked.get(0);
            picked.remove(0);
            if(cntCache[pop.b] > k - 1) {
                System.out.print(pop.a + " ");
                print(len - 1);
                break;
            }
            else {
                k -= cntCache[pop.b];
            }
        }
        
    }
    
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        for(int i = 0; i < testCase; i++) {
            Main k = new Main(br);
            k.lisLen = k.calc_lisLen(0) - 1;
            k.calc_lisCnt(0);
        
            k.print(k.lisLen);
            
            bw.write(k.calc_lisLen(0) - 1 + "\n");
            //for(int j = 0; j <= k.n; j++)
                //System.out.print(k.cntCache[j] + " ");
            System.out.println();
        }
        bw.flush(); bw.close(); br.close();
    }
}
