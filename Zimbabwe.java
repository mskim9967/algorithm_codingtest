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
    
    int cnt_price(int len, int picked) {
        if(len < 0) return 0;
        
        int ret = 0;
        for(int i = 0; i < e.length; i++) {
            if(eStr.charAt(len) - '0' < e[i]) continue;
            if(((picked>>i) & 1) == 1) continue;
            if(i != 0 && e[i] == e[i - 1] && ((picked>>(i-1)) & 1) == 0)  continue;
            System.out.println(eStr.charAt(len) + "/" + e[i]);
            if(len == 0)    ret += 1;
            else ret += cnt_price(len - 1, picked | (1<<i));

        }
        return ret;
    }
    
    
    void write_answer(BufferedWriter bw) throws Exception {
        bw.write(cnt_price(e.length - 1, 0) + "\n");
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
