import java.util.*;
import java.io.*;

public class Main{
    class GP { 
        double x, y, r; 
        GP(double x, double y, double r) { this.x = x; this.y = y; this.r = r;}
    }
    final double R = 8.0;
    int n;
    GP[] gp;
    double[] pos;

    Main(BufferedReader br) throws Exception {
        n = Integer.parseInt(br.readLine());
        gp = new GP[n];
        pos = new double[n];
        for(int i = 0; i < n; i++) {
            StringTokenizer token = new StringTokenizer(br.readLine(), " ");   
            gp[i] = new GP(Double.parseDouble(token.nextToken()),
                Double.parseDouble(token.nextToken()), Double.parseDouble(token.nextToken()));
        }
    }

    double trans_pointToPos(double x, double y) {
        return Math.asin(Math.sqrt(x * x + (y - R) * (y - R)) / (2 * R)) * 2 * R;
    }

    double get_l(double r) {
        return Math.asin(r / (2 * R)) * 2 * R;
    }

    void write_answer(BufferedWriter bw) throws Exception {
        bw.write("total length: " + Math.PI * 2 * R + "\n");
        for(int i = 0; i < n; i++) {
            pos[i] = trans_pointToPos(gp[i].x, gp[i].y);
            bw.write("gp[" + i + "]: " + pos[i]  + "\n");
            bw.write((pos[i] - get_l(gp[i].r) +(Math.PI * 2 * R)) % (Math.PI * 2 * R) + 
            " ~ gp[" + i + "] ~ "
             + (pos[i] + get_l(gp[i].r)) %  (Math.PI * 2 * R) + "\n");
            
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        for(int i = 0; i < testCase; i++) {
            Main l = new Main(br);
            l.write_answer(bw);
        }
        bw.flush(); bw.close(); br.close();
    }
}