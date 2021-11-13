import java.util.*;
import java.io.*;

public class Main{
    int[] price, pref, maxPref;
    int m, n;

    Main(BufferedReader br) throws Exception {
        String[] parse = br.readLine().split(" ");
        n = Integer.parseInt(parse[0]);
        m = Integer.parseInt(parse[1]) / 100;
        price = new int[n];
        pref = new int[n];

        for(int i = 0; i < n; i++) {
            String[] parse1 = br.readLine().split(" ");
            price[i] = Integer.parseInt(parse1[0]) / 100;
            pref[i] = Integer.parseInt(parse1[1]);
        }

        maxPref = new int[201];
    }
    
    int calc_maxPref(int m) {
        int ret = 0;
        for(int budget = 1; budget <= m; budget++) {
            for(int i = 0; i < n; i++) {
                if(budget < price[i]) continue;
                maxPref[budget % 201] = Math.max(maxPref[budget % 201], pref[i] + maxPref[(budget - price[i]) % 201]);
            }
            ret = Math.max(ret, maxPref[budget % 201]);
        }
        return ret;
    }

    void write_answer(BufferedWriter bw) throws Exception {
        bw.write(calc_maxPref(m) + "\n");
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        for(int i = 0; i < testCase; i++) {
            Main d = new Main(br);
            d.write_answer(bw);
        }
        bw.flush(); bw.close(); br.close();
    }
}
