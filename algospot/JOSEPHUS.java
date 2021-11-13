import java.util.*;
import java.io.*;

public class Main{
    int n, k;
    LinkedList<Integer> ll;
    Pair[] cache;

    Main() {
        ll = new LinkedList<Integer>();
        cache = new Pair[1001];
    }

    void init(BufferedReader br) throws Exception {
        StringTokenizer token = new StringTokenizer(br.readLine(), " "); 
        n = Integer.parseInt(token.nextToken());
        k = Integer.parseInt(token.nextToken());
    }

    void clear() {
        ll.clear();
    }

    void ans() {
        for(int i = 1; i <= n; i++)
            ll.add(i);

        for(int idx = 0; ll.size() != 2; idx = (idx + k - 1) % ll.size())
            ll.remove(idx);
   }

    void solve(BufferedReader br, BufferedWriter bw) throws Exception {
        init(br);
        ans();
        Collections.sort(ll);
        bw.write(ll.get(0) + " " + ll.get(1) + "\n");
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