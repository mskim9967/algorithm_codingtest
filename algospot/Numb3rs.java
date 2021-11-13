import java.util.*;
import java.io.*;

public class Main{
    int vilCnt, pastDay, startVil;
    boolean[][] hasPath;
    int[] pathCnt;
    double[][] cache;
    
    Main(BufferedReader br) throws Exception {
        String[] parse = br.readLine().split(" ");
        vilCnt = Integer.parseInt(parse[0]);
        pastDay = Integer.parseInt(parse[1]);
        startVil = Integer.parseInt(parse[2]);
        pathCnt = new int[vilCnt];
       
        cache = new double[vilCnt][pastDay + 1];
        for(int i = 0; i < cache.length; i++)
            for(int j = 0; j < cache[i].length; j++) 
                cache[i][j] = -1.0;
        
        hasPath = new boolean[vilCnt][vilCnt];
        for(int i = 0; i < vilCnt; i++) {
            parse = br.readLine().split(" ");
            for(int j = 0; j < vilCnt; j++) {
                hasPath[i][j] = parse[j].equals("1") ? true : false;
                if(hasPath[i][j])   pathCnt[i]++;
            }
        }
    }

    double calc_hideProb(int vil, int day) {
        if(day == 0)
            return vil == startVil ? 1.0 : 0.0;
            
        if(cache[vil][day] > -0.9)
            return cache[vil][day];
            
        double ret = 0.0;
        for(int pastVil = 0; pastVil < vilCnt; pastVil++)
            if(hasPath[vil][pastVil])
                ret+=calc_hideProb(pastVil, day - 1) / pathCnt[pastVil];
        
        return cache[vil][day] = ret;
    }

    void write_answer(BufferedReader br, BufferedWriter bw) throws Exception {
        int ansVilCnt = Integer.parseInt(br.readLine());
        String[] parse = br.readLine().split(" ");
        
        for(int i = 0; i < ansVilCnt; i++)
            bw.write(calc_hideProb(Integer.parseInt(parse[i]), pastDay) + " ");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        for(int i = 0; i < testCase; i++) {
            Main n = new Main(br);
            n.write_answer(br, bw);
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
