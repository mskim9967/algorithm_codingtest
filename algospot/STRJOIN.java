import java.util.*;
import java.io.*;

public class Main{
    int n;
    PriorityQueue<Integer> pq;
    
    Main(BufferedReader br) throws Exception {
        n = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<Integer>();

        StringTokenizer token = new StringTokenizer(br.readLine(), " ");   
        for(int i = 0; i < n; i++) 
            pq.add(Integer.parseInt(token.nextToken()));
    }
    
    int calc_minCost() {
        int ret = 0;
        while(pq.size() != 1) {
            int a = pq.poll();
            int b = pq.poll();
            ret += a + b;
            pq.add(a + b);
        }
        return ret;
    }

    void write_answer(BufferedWriter bw) throws Exception {
        bw.write(calc_minCost() + "\n");
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