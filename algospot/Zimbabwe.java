import java.util.*;
import java.io.*;

public class Main{
    int[] e, eSort;
    int m;
    int[][][] cache;
    final int MOD = 1000000007;
    
    Main(BufferedReader br) throws Exception {
        String[] parse = br.readLine().split(" ");
        e = new int[parse[0].length()];
        eSort = new int[parse[0].length()];
        for(int i = 0; i < e.length; i++)
            e[i] = eSort[i] = parse[0].charAt(i) - '0';
        Arrays.sort(eSort);
        
        m = Integer.parseInt(parse[1]);
        
        cache = new int[1 << e.length][m][2];
        for(int[][] x : cache)
            for(int[] y : x)
                Arrays.fill(y, -1);
    }
    
    int cnt_price(int len, int picked, int mod, boolean isLess) {
        if(len == e.length)
            return (isLess == true && mod == 0) ? 1 : 0;
        if(cache[picked][mod][isLess ? 1 : 0] != -1)
            return cache[picked][mod][isLess ? 1 : 0];
        
        int ret = 0;
        for(int i = 0; i < eSort.length; i++) {
            if(((picked >> i) & 1) == 1) continue;
            if(!isLess && e[len] < eSort[i])  continue;
            if(i != 0 && eSort[i] == eSort[i - 1] && ((picked >> (i - 1)) & 1) == 0)  continue;
            
            ret = (ret + cnt_price(len + 1, picked | (1 << i), 
                (mod * 10 + eSort[i]) % m, isLess || e[len] > eSort[i]))% MOD;
        }
        return cache[picked][mod][isLess ? 1 : 0] = ret;
    }
    
    void write_answer(BufferedWriter bw) throws Exception {
        bw.write(cnt_price(0, 0, 0, false) + "\n");
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        for(int i = 0; i < testCase; i++) {
            Main z = new Main(br);
            z.write_answer(bw);
        }
        bw.flush(); bw.close(); br.close();
    }
}
