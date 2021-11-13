import java.util.*;
import java.io.*;

public class Main{
    final int INF = 100;
    int n, k, m, l;
    int[] preSub, semeSub;
    int[][] cache;

    Main() {
        preSub = new int[12];
        semeSub = new int[10];
        cache = new int[4096][10];
    }

    void init(BufferedReader br) throws Exception {
        String[] parse = br.readLine().split(" ");
        n = Integer.parseInt(parse[0]);
        k = Integer.parseInt(parse[1]);
        m = Integer.parseInt(parse[2]);
        l = Integer.parseInt(parse[3]);
        for(int i = 0; i < n; i++) {
            parse = br.readLine().split(" ");
            int loop = Integer.parseInt(parse[0]);
            for(int j = 1; j <= loop; j++)
                preSub[i] |= (1 << Integer.parseInt(parse[j]));
        }
        for(int i = 0; i < m; i++) {
            parse = br.readLine().split(" ");
            int loop = Integer.parseInt(parse[0]);
            for(int j = 1; j <= loop; j++)
                semeSub[i] |= (1 << Integer.parseInt(parse[j]));
        }
    }

    void clear() {
        Arrays.fill(preSub, 0);
        Arrays.fill(semeSub, 0);
        for(int[] row : cache) Arrays.fill(row, 0);
    }

    int cnt_element(int set) {
        int ret = 0;
        for(; set != 0; set = set >> 1)
            if((set & 1) == 1) ret++;
        return ret;
    }

    int ans(int taken, int semester) {
        if(cnt_element(taken) >= k) return 0;
        if(semester == m) return INF;
        if(cache[taken][semester] != 0) return cache[taken][semester];

        int ret = INF;
        int left = ~taken & semeSub[semester];
        for(int i = 0; i < n; i++) {
            if(((left >> i) & 1) == 0) continue;
            if((taken & preSub[i]) != preSub[i]) left &= ~(1 << i);
        }
        for(int pick = left; pick != 0; pick = (pick - 1) & left) {
            if(cnt_element(pick) > l) continue;
            ret = Math.min(ret, 1 + ans(pick | taken, semester + 1));
        }
        ret = Math.min(ret, ans(taken, semester + 1));
        return cache[taken][semester] = ret;
    }

    void solve(BufferedReader br, BufferedWriter bw) throws Exception {
        init(br);
        bw.write((ans(0, 0) >= INF ? "IMPOSSIBLE" : ans(0, 0)) + "\n");
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