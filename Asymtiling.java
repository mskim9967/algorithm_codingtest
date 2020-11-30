import java.util.*;
import java.io.*;

public class Main{
    int wid;
    int[] cache;
    final int MOD = 1000000007;
    
    Main(BufferedReader br) throws Exception {
        wid = Integer.parseInt(br.readLine());
        cache = new int[wid];
    }

    int count_tiling(int w) {
        if(w == 2)  return 2;
        if(w == 1)  return 1;
        
        if(cache[w - 1] != 0)   return cache[w - 1];
        return cache[w - 1] = (count_tiling(w - 1) + count_tiling(w - 2)) % MOD;
    }
    
    int count_symTiling(int w) {
        if(w <= 2) 
            return w;
        
        if(w % 2 == 1)
            return count_tiling((w - 1) / 2);
        
        return (count_tiling((w - 2) / 2) + count_tiling(w / 2)) % MOD;
    }
   
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        for(int i = 0; i < testCase; i++) {
            Main c = new Main(br);
            bw.write((c.count_tiling(c.wid) - c.count_symTiling(c.wid)+c.MOD)%c.MOD + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
