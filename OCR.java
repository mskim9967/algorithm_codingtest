import java.util.*;
import java.io.*;

public class Main{
    int m, q, n;
    String[] word;
    double[] B;
    double [][] T, M, cache;
    int[][] chsCache;
    int[] R;

    Main(BufferedReader br) throws Exception {
        String[] parse = br.readLine().split(" ");
        m = Integer.parseInt(parse[0]);
        q = Integer.parseInt(parse[1]);
        
        word = new String[m];
        parse = br.readLine().split(" ");
        for(int i = 0; i < m; i++)
            word[i] = parse[i];
        
        B = new double[m];
        parse = br.readLine().split(" ");
        for(int i = 0; i < m; i++)
            B[i] = parse[i].equals("0.0") ? -Double.MAX_VALUE : Math.log(Double.parseDouble(parse[i]));

        T = new double[m][m];
        for(int i = 0; i < m; i++) {
            parse = br.readLine().split(" ");
            for(int j = 0; j < m; j++)
                T[i][j] = parse[j].equals("0.0") ? -Double.MAX_VALUE : Math.log(Double.parseDouble(parse[j]));
        }
        
        M = new double[m][m];
        for(int i = 0; i < m; i++) {
            parse = br.readLine().split(" ");
            for(int j = 0; j < m; j++)
                M[i][j] = parse[j].equals("0.0") ? -Double.MAX_VALUE : Math.log(Double.parseDouble(parse[j]));
        }
    }
    
    int get_idx(String word) {
        for(int i = 0; i < m; i++)
            if(word.equals(this.word[i])) return i;
        return 0;
    }
    
    double maxNumerator(int idx, int chsWordIdx) {
        if(idx == n)    return 0.0;
        if(cache[idx][chsWordIdx] < 1.1) return cache[idx][chsWordIdx];
        
        double ret = -Double.MAX_VALUE;
        for(int i = 0; i < m; i++) {
            double calc = M[i][R[idx]] + maxNumerator(idx + 1, i);
            calc += (idx == 0) ? B[i] : T[chsWordIdx][i];
            
            if(ret > calc)  continue;
            ret = calc;
            chsCache[idx][chsWordIdx] = i;
        }
        return cache[idx][chsWordIdx] = ret;
    }
    
    void write_chsWord(BufferedWriter bw, int idx, int chsWordIdx) throws Exception {
        if(idx == n)    return ;
        int next = chsCache[idx][chsWordIdx];
        bw.write(word[next] + " ");
        write_chsWord(bw, idx + 1, next);
    }
    
    void write_answer(BufferedReader br, BufferedWriter bw) throws Exception {
        String[] parse = br.readLine().split(" ");
        n = Integer.parseInt(parse[0]);
        R = new int[n];
        for(int i = 1; i <= n; i++) 
            R[i - 1] = get_idx(parse[i]);
        
        cache = new double[n][m];
        for(int i = 0; i < cache.length; i++)
            for(int j = 0; j < cache[i].length; j++) 
                cache[i][j] = 2.0;
        
        chsCache = new int[n][m];
        
        maxNumerator(0, 0);
        write_chsWord(bw, 0, 0);
        bw.write("\n");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Main o = new Main(br);
        for(int i = 0; i < o.q; i++)
            o.write_answer(br, bw);
        bw.flush(); bw.close(); br.close();
    }
}
