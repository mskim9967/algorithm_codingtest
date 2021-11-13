import java.util.*;
import java.io.*;

public class Main{
    int n, k, len;
    int[] L, M, G;

    Main() {
        L = new int[5000];
        M = new int[5000];
        G = new int[5000];
    }

    void init(BufferedReader br) throws Exception {
        String[] parse = br.readLine().split(" ");
        n = Integer.parseInt(parse[0]);
        k = Integer.parseInt(parse[1]);
        for(int i = 0; i < n; i++) {
            parse = br.readLine().split(" ");
            L[i] = Integer.parseInt(parse[0]);
            M[i] = Integer.parseInt(parse[1]);
            G[i] = Integer.parseInt(parse[2]);
            len = Math.max(len, L[i]);
        }
    }

    void clear() {
        Arrays.fill(L, 0);
        Arrays.fill(M, 0);
        Arrays.fill(G, 0);
        len = 0;
    }

    int signCnt(int x) {
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            if(L[i]-M[i] > x) continue;
            else if(L[i] > x) cnt += (x - L[i] + M[i]) / G[i] + 1;
            else cnt += M[i] / G[i] + 1;
        }
        return cnt;
    }

    int ans(int min, int max) {
        if(min == max) return min;
        int mid = (min + max) / 2;
        return signCnt(mid) >= k ? ans(min, mid) : ans(mid + 1, max);
    }
    void solve(BufferedReader br, BufferedWriter bw) throws Exception {
        init(br);
        bw.write(ans(0, len) + "\n");
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

}