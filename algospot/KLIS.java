import java.util.*;
import java.io.*;

public class Main{
    int n, k;
    int[] seq, lenCache, cntCache;
    UniCache[] uniCache;
    
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
        for(int next = now + 1; next < seq.length; next++) 
            if(seq[now] < seq[next]) 
                ret = Math.max(ret, 1 + calc_lisLen(next));
        
        return lenCache[now] = ret;        
    }
    
    int calc_lisCnt(int now) {
        if(lenCache[now] == 1)  return cntCache[now] = 1;
        if(cntCache[now] != -1) return cntCache[now];
        
        int ret = 0;
        for(int next = now + 1; next < seq.length; next++) {
            if(seq[now] < seq[next] && lenCache[next] == lenCache[now] - 1) {
                ret += calc_lisCnt(next);
                if(ret < 0) ret = Integer.MAX_VALUE;
            }
        }
        return cntCache[now] = ret;
    }
    
    class UniCache implements Comparable<UniCache> {
        int element, len, cnt;
        UniCache(int element, int len, int cnt) { 
            this.element = element; this.len = len; this.cnt = cnt;
        }
        public int compareTo(UniCache o) { return this.element > o.element ? 1 : -1; }
    }
    
    void build_uniCache() {
        uniCache = new UniCache[n + 1];
        for(int i = 0; i < uniCache.length; i++)
            uniCache[i] = new UniCache(seq[i], lenCache[i], cntCache[i]);
        Arrays.sort(uniCache);
    }
    
    void write_KLIS(BufferedWriter bw, int now, int len) throws Exception {
        if(len == 0)    return;
       
        for(int next = now + 1; next < uniCache.length; next++) {
            if(uniCache[next].len != len)   continue;
            
            if(uniCache[next].cnt > k - 1) {
                bw.write(uniCache[next].element + " ");
                write_KLIS(bw, next, len - 1);
                break;
            }
            k -= uniCache[next].cnt;
        }       
    }
    
    void write_answer(BufferedWriter bw) throws Exception {
        bw.write(calc_lisLen(0) - 1 + "\n");
        calc_lisCnt(0);
        build_uniCache();
        write_KLIS(bw, 0, calc_lisLen(0) - 1);
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
