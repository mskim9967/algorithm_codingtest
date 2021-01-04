import java.util.*;
import java.io.*;

public class Main{
    class GP implements Comparable<GP>{ 
        double x, y, r, arc, pos, start, end;
        GP(double x, double y, double r) { this.x = x; this.y = y; this.r = r;}
        public int compareTo(GP o) { 
            if(this.start > o.start) return 1; 
            else if(this.start == o.start) return 0;
            else return -1;
        }
    }
    //R:Radius, D:Diameter, CIR:Circumference
    final double R = 8.0, D = R * 2, CIR = 2 * Math.PI * R;
    final int IMPOSSIBLE = Integer.MAX_VALUE;
    int n;
    GP[] gp;
    ArrayList<GP> over;

    Main(BufferedReader br) throws Exception {
        n = Integer.parseInt(br.readLine());
        gp = new GP[n];
        over = new ArrayList<GP>();

        for(int i = 0; i < n; i++) {
            StringTokenizer token = new StringTokenizer(br.readLine(), " ");   
            gp[i] = new GP(Double.parseDouble(token.nextToken()),
                Double.parseDouble(token.nextToken()), Double.parseDouble(token.nextToken()));
            
            gp[i].arc = calc_arc(gp[i].r);
            gp[i].pos = convert_pointToPos(gp[i].x, gp[i].y);
            gp[i].start = (gp[i].pos - gp[i].arc);
            gp[i].end = (gp[i].pos + gp[i].arc);
            
            if(gp[i].start < 0.0 || gp[i].end > CIR) over.add(gp[i]);
        }
    }

    double convert_pointToPos(double x, double y) {
        if(x < 0.0) return CIR - Math.asin(Math.sqrt(x * x + (y - R) * (y - R)) / D) * D;
        else return Math.asin(Math.sqrt(x * x + (y - R) * (y - R)) / D) * D;
    }

    double calc_arc(double r) {
        return Math.asin(r / D) * D;
    }

    int calc_minSoldierOnAxis(double left, double right) {
        int ret = 0;
        double start = left, end = start;

        for(int i = 0; i < n;) {
            for(; i < n && gp[i].start < start; i++) 
                end = Math.max(end, gp[i].end);
            ret++;
            
            if(start == end) return IMPOSSIBLE;
            if(end > right) return ret;
            
            start = end;
        }
        return IMPOSSIBLE;
    }

    int calc_minSoldier() {
        int ret = IMPOSSIBLE;
        for(GP pop : over)
            ret = Math.min(ret, calc_minSoldierOnAxis(pop.end % CIR, (pop.start + CIR) % CIR));
        return (ret == IMPOSSIBLE) ? IMPOSSIBLE : ret + 1;
    }

    void write_answer(BufferedWriter bw) throws Exception {
        Arrays.sort(gp);
        int ans = calc_minSoldier();
        bw.write((ans == IMPOSSIBLE ? "IMPOSSIBLE" : ans) + "\n");
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