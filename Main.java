import java.util.*;
import java.io.*;

public class Main{
<<<<<<< HEAD
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
=======
    public class Time implements Comparable<Time> {
        int heat, eat;
        Time(int heat, int eat) { this.heat = heat; this.eat = eat; }
        public int compareTo(Time o) { 
            if(this.eat < o.eat) return 1;
            else if(this.eat == o.eat) return 0;
            else return -1;
        }
    }
    Time[] time;
    int[] heatSum;
    int n;
    
    Main(BufferedReader br) throws Exception {
        n = Integer.parseInt(br.readLine());
        time = new Time[n];
        heatSum = new int[n];

        StringTokenizer token = new StringTokenizer(br.readLine(), " ");   
        for(int i = 0; i < n; i++) 
            time[i] = new Time(Integer.parseInt(token.nextToken()), 0);
        
        token = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i++)
            time[i].eat = Integer.parseInt(token.nextToken());
    }
    
    int calc_minLunchTime() {
        Arrays.sort(time);
        
        int ret = 0;
        for(int i = 0; i < n; i++) {
            heatSum[i] += time[i].heat + ((i != 0) ? heatSum[i - 1] : 0);
            ret = Math.max(ret, heatSum[i] + time[i].eat);
>>>>>>> af27cddc735fc21980bcf57a67f908049fbcae89
        }
        return ret;
    }

    void write_answer(BufferedWriter bw) throws Exception {
<<<<<<< HEAD
        bw.write(calc_minCost() + "\n");
=======
        bw.write(calc_minLunchTime() + "\n");
>>>>>>> af27cddc735fc21980bcf57a67f908049fbcae89
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