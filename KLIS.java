import java.util.*;
import java.io.*;

public class Main{
    int n;
    long k;
    int[] seq, lenCache;
    long[] cntCache;
    
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
        
        cntCache = new long[n + 1];
        Arrays.fill(cntCache, -1);
    }
    
    int calc_lisLen(int now) {
        if(lenCache[now] != -1) return lenCache[now];

        int ret = 1;
        for(int next = now + 1; next < seq.length; next++) 
            if(seq[now] < seq[next]) 
                ret = Math.max(ret, 1 + calc_lisLen(next));
        
        return lenCache[now] = ret;        
    }
    
    long calc_lisCnt(int now) {
        if(cntCache[now] != -1) return cntCache[now];
        
        long ret = 0;
        for(int next = now + 1; next < seq.length; next++) 
            if(seq[now] < seq[next] && lenCache[next] == lenCache[now] - 1)
                ret = Math.min(Integer.MAX_VALUE, ret + calc_lisCnt(next));
        
        if(lenCache[now] == 1)  ret = 1;
        return cntCache[now] = ret;
    }

    class Pair implements Comparable<Pair> {
        int element, idx;
        Pair(int element, int idx) { this.element = element; this.idx = idx; }
        public int compareTo(Pair o) { return this.element > o.element ? 1 : -1; }
    }

    void write_KLIS(BufferedWriter bw, int len, int prev) throws Exception {
        if(len == 0)    return;
        
        PriorityQueue<Pair> picked = new PriorityQueue<>();
        //Vector<Pair> picked = new Vector<Pair>();
        for(int i = 1; i < seq.length; i++)
            if(lenCache[i] == len && seq[i] > prev)
                picked.add(new Pair(seq[i], i));
    
        while(true) {
            Pair pop = picked.poll();
            if(cntCache[pop.idx] > k - 1) {
                bw.write(pop.element + " ");
                write_KLIS(bw, len - 1, pop.element);
                break;
            }
            k -= cntCache[pop.idx];
        }
    }
    
    void write_answer(BufferedWriter bw) throws Exception {
        bw.write(calc_lisLen(0) - 1 + "\n");
        calc_lisCnt(0);
        write_KLIS(bw, calc_lisLen(0) - 1, 0);
        bw.write("\n");
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        for(int i = 0; i < testCase; i++) {
            Main k = new Main(br);
            k.write_answer(bw);
        }
        bw.flush(); bw.close(); br.close();
    }
}
