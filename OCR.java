import java.util.*;
import java.io.*;

public class Main{
    int m, q, n;
    String[] word, R;
    double[] B;
    double [][] T, M;
    double[][] cache;

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
            B[i] = Double.parseDouble(parse[i]);
        
        T = new double[m][m];
        for(int i = 0; i < m; i++) {
            parse = br.readLine().split(" ");
            for(int j = 0; j < m; j++)
                T[i][j] = Double.parseDouble(parse[j]);
        }
        
        M = new double[m][m];
        for(int i = 0; i < m; i++) {
            parse = br.readLine().split(" ");
            for(int j = 0; j < m; j++)
                M[i][j] = Double.parseDouble(parse[j]);
        }
    }
    
    int get_idx(String word) {
        for(int i = 0; i < m; i++)
            if(word.equals(this.word[i]))
                return i;
        return 0;
    }
    
    double maxNumerator(int idx, int chsWordIdx) {
        if(idx == n)    return 1;
        if(cache[idx][chsWordIdx] < 1.0)    return cache[idx][chsWordIdx];
        
        double ret;
        
        double pR_S = 0.0;
        for(int i = 0; i < m; i++)
            pR_S = Math.max(pR_S, M[i][get_idx(R[idx])]);
        
        double pS = 0.0;
        if(idx == 0) {
            for(int i = 0; i < m; i++) {
                if(pS >= B[i]) continue;
                pS = B[i];
                chsWordIdx = i;
            }
        }
        else {
            for(int i = 0; i < m; i++) {
                if(pS >= T[chsWordIdx][i]) continue;
                pS = T[chsWordIdx][i];
                chsWordIdx = i;
            }
        }
        
        return pR_S * pS * maxNumerator(idx + 1, chsWordIdx);
    }
    
    void write_answer(BufferedReader br, BufferedWriter bw) throws Exception {
        String[] parse = br.readLine().split(" ");
        n = Integer.parseInt(parse[0]);
        R = new String[n];
        for(int i = 0; i < n; i++) 
            R[i] = parse[i];
        
        cache = new double[n][m];
        for(int i = 0; i < cache.length; i++)
            for(int j = 0; j < cache[i].length; j++) 
                cache[i][j] = 1.1;
        
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        Main o = new Main(br);

        
        bw.flush(); bw.close(); br.close();
    }
}
