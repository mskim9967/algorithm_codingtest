import java.util.*;
import java.io.*;

public class JLIS{
    int[] a, b;
    int[][] cache;

    JLIS(BufferedReader br) throws Exception {
        String[] parse = br.readLine().split(" ");
        a = new int[Integer.parseInt(parse[0]) + 1];
        b = new int[Integer.parseInt(parse[1]) + 1];
        a[0] = Integer.MIN_VALUE;
        b[0] = Integer.MIN_VALUE + 1;
        cache = new int[a.length][b.length];
        
        parse = br.readLine().split(" ");
        for(int i = 1; i < a.length; i++)
            a[i] = Integer.parseInt(parse[i - 1]);
            
        parse = br.readLine().split(" ");
        for(int i = 1; i < b.length; i++)
            b[i] = Integer.parseInt(parse[i - 1]);
    }
    
    int max(int a, int b) { return a > b ? a : b; }
    
    int JLIS_len(int aIdx, int bIdx) {
        if(cache[aIdx][bIdx] != 0)
            return cache[aIdx][bIdx];
        
        int ret = 2;
        int maxNum = max(a[aIdx], b[bIdx]);
        
        for(int i = aIdx + 1; i < a.length; i++)
            if(a[i] > maxNum)
                ret = max(ret, JLIS_len(i, bIdx) + 1);
        
        for(int i = bIdx + 1; i < b.length; i++)
            if(b[i] > maxNum)
                ret = max(ret, JLIS_len(aIdx, i) + 1);
                
        return cache[aIdx][bIdx] = ret;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        for(int i = 0; i < testCase; i++) {
            JLIS j = new JLIS(br);
            bw.write(j.JLIS_len(0, 0) - 2 + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

}
