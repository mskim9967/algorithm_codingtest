import java.util.*;
import java.io.*;

public class Main{
    int m, q, n;
    String[] word, R, ans;
    double[] B;
    double [][] T, M;
    double[][] cache;
    int[][] chs;

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
            B[i] = Math.log(Double.parseDouble(parse[i]));
        
        T = new double[m][m];
        for(int i = 0; i < m; i++) {
            parse = br.readLine().split(" ");
            for(int j = 0; j < m; j++)
                T[i][j] = Math.log(Double.parseDouble(parse[j]));
        }
        
        M = new double[m][m];
        for(int i = 0; i < m; i++) {
            parse = br.readLine().split(" ");
            for(int j = 0; j < m; j++)
                M[i][j] = Math.log(Double.parseDouble(parse[j]));
        }
    }
    
    int get_idx(String wword) {
        for(int i = 0; i < m; i++)
            if(wword.equals(word[i])){
                return i;
            }
        return 0;
    }
    
    double maxNumerator(int idx, int chsWordIdx) {
        if(idx == n)    return 0.0;
        if(cache[idx][chsWordIdx] < 1.0)    return cache[idx][chsWordIdx];
        
        double ret = Double.MIN_VALUE;
        
        for(int i = 0; i < m; i++) {
            if(idx == 0) {
                if(ret <= M[i][get_idx(R[idx])] + B[i] + maxNumerator(idx + 1, i))
                    continue;
                ret = M[i][get_idx(R[idx])] + B[i] + maxNumerator(idx + 1, i);
                ans[idx] = new String(word[i]);
            }
            else {
                if(ret <= M[i][get_idx(R[idx])] + T[chsWordIdx][i] + maxNumerator(idx + 1, i))
                    continue;
                ret = M[i][get_idx(R[idx])] + T[chsWordIdx][i] + maxNumerator(idx + 1, i);
                ans[idx] = new String(word[i]);
            }
            chs[idx][chsWordIdx] = i;
        }

        return cache[idx][chsWordIdx] = ret;
    }
    
    void print_ans(int idx, int chsWordIdx) {
        if(idx == n)    return ;
        
        int next = chs[idx][chsWordIdx];
        System.out.print(word[next] + " ");
        print_ans(idx + 1, next);
    }
    
    void write_answer(BufferedReader br, BufferedWriter bw) throws Exception {
        for(int k = 0; k < q; k++) { 
            String[] parse = br.readLine().split(" ");
            n = Integer.parseInt(parse[0]);
            R = new String[n];
            for(int i = 1; i <= n; i++) {
                R[i - 1] = parse[i];
            }
     
            cache = new double[n][m];
            for(int i = 0; i < cache.length; i++)
                for(int j = 0; j < cache[i].length; j++) 
                    cache[i][j] = 1.1;
            
            chs = new int[n][m];
            ans = new String[n];
            
            maxNumerator(0, 0);
            //print_ans(0, 0);
            for(int i =0; i < n; i++)
                bw.write(ans[i] + " ");
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        Main o = new Main(br);
        o.write_answer(br, bw);
        
        bw.flush(); bw.close(); br.close();
    }
}
