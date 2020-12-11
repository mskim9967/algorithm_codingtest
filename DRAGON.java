import java.util.*;
import java.io.*;

public class Main{
    int n, p, l;
    
    Main(BufferedReader br) throws Exception {
        String[] parse = br.readLine().split(" ");
        n = Integer.parseInt(parse[0]);
        p = Integer.parseInt(parse[1]);
        l = Integer.parseInt(parse[2]);
        
        cntCache = new int[n + 1];
        Arrays.fill(cntCache, -1);
    }
    /*
X=X+YF
Y=FX-Y

FX
FX+YF
FX+YF+FX-YF
FX+YF+FX-YF+FX+YF-FX-YF
FX+YF+FX-YF+FX+YF-FX-YF+FX+YF+FX-YF-FX+YF-FX-YF
    */
   
    
    void write_answer(BufferedWriter bw) throws Exception {

        bw.write("\n");
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        for(int i = 0; i < testCase; i++) {
            Main k = new Main(br);
            k.write_answer(bw);
        }
        bw.flush(); bw.close(); br.close();
    }
}
