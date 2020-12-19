import java.util.*;
import java.io.*;

public class Main{
    
    
    Main(BufferedReader br) throws Exception {
        String[] parse = br.readLine().split(" ");
        
    }
    
    void write_answer(BufferedWriter bw) throws Exception {

        bw.write("\n");
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(input.txt)));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        for(int i = 0; i < testCase; i++) {
            Main d = new Main(br);
            d.write_answer(bw);
        }
        bw.flush(); bw.close(); br.close();
    }
}
