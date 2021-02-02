import java.util.*;
import java.io.*;

public class Main{
    int n, k;
    Queue<Integer> queue;

    Main() {
        queue = new LinkedList<Integer>();
    }

    void init(BufferedReader br) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine(), " " );
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
    }

    void clear() {
        queue.clear();
    }

    int ans() {
        int ret = 0, sum = 1984;
        long a = 1983;
        queue.add(1984);
        for(int i = 1; i < n; i++) {
            a = (a * 214013 + 2531011) % ((long)1 << 32);
            int sig = (int)(a % 10000 + 1);

            queue.add(sig);
            sum += sig;

            while(sum > k) sum -= queue.poll();
            if(sum == k) ret++;
        }
        return ret;
    }

    void solve(BufferedReader br, BufferedWriter bw) throws Exception {
        init(br);
        bw.write(ans() + "\n");
        clear();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        Main c = new Main();
        for(int i = 0; i < testCase; i++)
            c.solve(br, bw);
        bw.flush(); bw.close(); br.close();
    }
}