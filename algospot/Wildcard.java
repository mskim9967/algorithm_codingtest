import java.util.*;
import java.io.*;

public class Main{
    String pat, str;
    int[][] cache;
    
    Main(BufferedReader br) throws Exception {
        pat = br.readLine();
    }
    
    void init_cache() {
        for(int i = 0; i < cache.length; i++)
            for(int j = 0; j < cache[i].length; j++)
                cache[i][j] = -1;
    }
    
    void print_matchedFile(BufferedReader br, BufferedWriter bw) throws Exception {
        int fileNum = Integer.parseInt(br.readLine());
        Vector<String> matchedFile = new Vector<String>();
        for(int i = 0; i < fileNum; i++) {
            str = br.readLine();
            cache = new int[pat.length()][str.length()];
            init_cache();
            if(isMatched(0,0) == 1)
                matchedFile.add(str);
        }
        Collections.sort(matchedFile);
        for(int i = 0; i < matchedFile.size(); i++)
            bw.write(matchedFile.get(i) + "\n");
    }
    
    int isMatched(int p, int s) {
        if(p == pat.length() && s == str.length())
            return 1;
                
        if(p == pat.length())
            return 0;

        if(s == str.length()) {
            if(pat.charAt(p) == '*')
                return isMatched(p + 1, s);
            return 0;
        }
        
        if(cache[p][s] != -1)  
            return cache[p][s];
        
        if(pat.charAt(p) == '*') {
            if(isMatched(p + 1, s) == 1 || isMatched(p, s + 1) == 1) 
                return cache[p][s] = 1;
            
            else 
                return cache[p][s] = 0;
        }
        
        if(pat.charAt(p) == str.charAt(s) || pat.charAt(p) == '?') {
            cache[p][s] = 1;
            return isMatched(p + 1, s + 1);
        }
        
        return cache[p][s] = 0;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        for(int i = 0; i < testCase; i++) {
            Main w = new Main(br);
            w.print_matchedFile(br, bw);
        }
        bw.flush();
        bw.close();
        br.close();
    }

}
