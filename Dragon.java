import java.util.*;
import java.io.*;

public class Main{
    int n, p, l, k;
    int[] lenCache;
    
    Main(BufferedReader br) throws Exception {
        String[] parse = br.readLine().split(" ");
        n = Integer.parseInt(parse[0]);
        p = Integer.parseInt(parse[1]);
        l = Integer.parseInt(parse[2]);
        
        lenCache = new int[n + 1];
    }
    
    int calc_len(int gene) {
        if(gene == 0)   return lenCache[gene] = 1;
        
        lenCache[gene] =  2 * calc_len(gene - 1) + 2;
        if(lenCache[gene] <= lenCache[gene - 1]) 
            lenCache[gene] = Integer.MAX_VALUE;
            
        return lenCache[gene];
    }
   
    void write_k(BufferedWriter bw, String str, int gene) throws Exception {
        if(gene == 0) {
            bw.write(str.charAt(k - 1));
            k = -1;
            return ;
        }
        
        for(int i = 0; i < str.length(); i++) {
            if(k == -1)   return;
            
            if(str.charAt(i) == 'X') {
                if(k > lenCache[gene])
                    k -= lenCache[gene];
                else
                    write_k(bw, "X+YF", gene - 1);
            }
            else if(str.charAt(i) == 'Y') {
                if(k > lenCache[gene])
                    k -= lenCache[gene];
                else
                    write_k(bw, "FX-Y", gene - 1);
            }
            else {
                if(k == 1) {
                    bw.write(str.charAt(i));
                    k = -1;
                    return ;
                }
                k--;
            }
        }
   }
    
    void write_answer(BufferedWriter bw) throws Exception {
        calc_len(n);
        for(int i = p; i < p + l; i++){
            k = i;
            write_k(bw, "FX", n);
        }
        bw.write("\n");
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        for(int i = 0; i < testCase; i++) {
            Main d = new Main(br);
            d.write_answer(bw);
        }
        bw.flush(); bw.close(); br.close();
    }
}
