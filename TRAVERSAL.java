import java.util.*;
import java.io.*;

public class Main{
    int n;
    int[] pre, in;
    ArrayList<Integer> post;

    Main() {
        pre = new int[100];
        in = new int[100];
        post = new ArrayList<Integer>();
    }

    void init(BufferedReader br) throws Exception {
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i++)
            pre[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i++)
            in[i] = Integer.parseInt(st.nextToken());
    }

    void clear() {
        Arrays.fill(pre, 0);
        Arrays.fill(in, 0);
        post.clear();
    }

    void ans(int idx, int l, int r) {
        if(l >= r) {
            if(l == r) post.add(in[l]);
            return;
        }
        int mid = l;
        while(pre[idx] != in[mid])
            mid++;
        
        ans(idx + 1, l, mid - 1);
        ans(idx + mid - l + 1, mid + 1, r);

        post.add(pre[idx]);
        return;
    }

    void solve(BufferedReader br, BufferedWriter bw) throws Exception {
        init(br);
        ans(0, 0, n - 1);
        for(int ele : post)
            bw.write(ele + " ");
        bw.write("\n");
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