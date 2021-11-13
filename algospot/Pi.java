import java.util.*;
import java.io.*;

public class Main{
    int[] pi, cache;
    
    Main(BufferedReader br) throws Exception {
        String line = br.readLine();
        pi = new int[line.length()];
        for(int i = 0; i < pi.length; i++)
            pi[i] = line.charAt(i) - '0';
        cache = new int[line.length()];
    }
    
    int min(int a, int b) { return a < b ? a : b; }
    
    int find_minLevel(int idx) {
        if(idx >= pi.length) return 0;
        if(cache[idx] != 0) return cache[idx];
        
        int ret = pi.length / 3 * 10;
        
        for(int i = 3; i <= 5; i++) {
            if(idx + i > pi.length) break;
            int level = classify(idx, i);
            ret = min(ret, level + find_minLevel(idx + i));
        }
        return cache[idx] = ret;
    }
    
    int classify(int idx, int num) {
        int minus = pi[idx] - pi[idx + 1];
        for(int i = idx + 1; i < idx + num - 1; i++) {
            if(pi[i] - pi[i + 1] != minus)
                break;
            if(i == idx + num - 2) {
                if(minus == 0) return 1;
                if(minus == 1 || minus == -1) return 2;
                return 5;
            }
        }
        for(int i = idx + 2; i < idx + num; i++) {
            if(pi[i] != pi[i - 2]) break;
            if(i == idx + num - 1) return 4;
        }
        return 10;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        for(int i = 0; i < testCase; i++) {
            Main p = new Main(br);
            bw.write(p.find_minLevel(0) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

}
