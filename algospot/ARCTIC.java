import java.util.*;
import java.io.*;

public class Main{
    int n;
    double[][] dist;
    class Base {double x, y;}
    Base[] base;

    Main() {
        dist = new double[100][100];
        base = new Base[100];
        for(int i = 0; i < 100; i++) base[i] = new Base();
    }

    void init(BufferedReader br) throws Exception {
        n = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < n; i++) {
            String[] parse = br.readLine().split(" ");
            base[i].x = Double.parseDouble(parse[0]);
            base[i].y = Double.parseDouble(parse[1]);  
        }
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                dist[i][j] = Math.sqrt(Math.pow(base[i].x - base[j].x, 2.0) + Math.pow(base[i].y - base[j].y, 2.0));
    }

    void clear() {
        for(double[] row : dist) Arrays.fill(row, 0.0);
        for(Base b : base) { b.x = 0.0; b.y = 0.0; }
    }

    boolean isGraphConnected(double r) {
        int visitedBaseCnt = 0;
        boolean[] isVisited = new boolean[n];
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(0);
        isVisited[0] = true;
        while(queue.size() != 0) {
            int pop = queue.poll();
            visitedBaseCnt++;
            for(int next = 0; next < n; next++)
                if(!isVisited[next] && dist[pop][next] <= r) {
                    queue.offer(next);
                    isVisited[next] = true;
                }
        }
        return visitedBaseCnt == n;
    }

    double ans(double min, double max) {
        if(Math.round(min * 100) == Math.round(max * 100)) return Math.round(max * 100) * 0.01;
        double mid = (min + max) / 2;
        return isGraphConnected(mid) ? ans(min, mid) : ans(mid, max);
    }
    void solve(BufferedReader br, BufferedWriter bw) throws Exception {
        init(br);
        //bw.write(String.format("%.2f", ans(0.0, 1000.0)) + "\n");
        bw.write(String.format("%.2f", mst()) + "\n");
        clear();
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        Main l = new Main();
        for(int i = 0; i < testCase; i++)
            l.solve(br, bw);
        bw.flush(); bw.close(); br.close();
    }

    class Edge implements Comparable<Edge> {
        int v1, v2;
        double wei;
        Edge(int v1, int v2, double wei) {this.v1 = v1; this.v2 = v2; this.wei = wei; }
        public int compareTo(Edge o) { return (this.wei - o.wei) > 0.0 ? 1: -1; }
    }

    class DisjointSet {
        int[] arr;
        DisjointSet(int n) { 
            arr = new int[n];
            for(int i = 0; i < n; i++) arr[i] = i;
        }
        void uni(int a, int b) {
            arr[find(b)] = find(a);
        }
        int find(int a) {
            int m = a;
            while(arr[m] != m) m = arr[m];
            return arr[a] = arr[m];
        }
        boolean isOneSet() {
            int cnt = 0;
            for(int i = 0; i < n; i++) 
                if(arr[i] == i) cnt++;
            return cnt == 1;
        }
    }
    double mst() {
        double ret = 0.0;
        int[] djSet = new int[n];
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        DisjointSet ds = new DisjointSet(n);

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                if(i != j) pq.add(new Edge(i, j, dist[i][j]));
        
        while(!ds.isOneSet()) {
            Edge pop = pq.poll();
            if(ds.find(pop.v1) == ds.find(pop.v2)) continue;
            ret = Math.max(ret, pop.wei);
            ds.uni(pop.v1, pop.v2);
        }
        return ret;
    }
}