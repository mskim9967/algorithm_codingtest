import java.util.*;
import java.io.*;

public class Main{
    String eStr;
    int[] e;
    int m;
    
    final int MOD = 1000000007;
    
    Main(BufferedReader br) throws Exception {
        String[] parse = br.readLine().split(" ");
        eStr = parse[0];
        m = Integer.parseInt(parse[1]);
        
        e = new int[eStr.length()];
        for(int i = 0; i < e.length; i++)
            e[i] = eStr.charAt(i) - '0';
            
        Arrays.sort(e);
    }
    
    int cnt_price(int len, int picked, boolean isLess) {
        int ret = 0;
        
        if(len == e.length ) {
            if(isLess == true)
                return 1;
            return 0;
        }
        
        for(int i = 0; i < e.length; i++) {
            if(((picked>>i) & 1) == 1) continue;
            if(!isLess && eStr.charAt(len) - '0' < e[i])  continue;
            if(i != 0 && e[i] == e[i - 1] && ((picked>>(i-1)) & 1) == 0)  continue;
            ret += cnt_price(len + 1, picked | (1<<i), isLess || e[i] < (eStr.charAt(len) -  '0'));
        }
        return ret;
    }
    
    
    void write_answer(BufferedWriter bw) throws Exception {
        bw.write(cnt_price(0, 0, false) + "\n");
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
