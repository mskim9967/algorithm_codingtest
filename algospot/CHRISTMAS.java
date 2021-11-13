import java.util.*;
import java.io.*;

public class Main{
    int n, k;
    int[] arr, sumMod, cnt, cache, idx;

    Main() {
        arr = new int[100001];
        sumMod = new int[100001];
        cnt = new int[100000];
        cache = new int[100001];
        idx = new int[100000];        
        Arrays.fill(idx, -1);
        Arrays.fill(cache, -1);
    }

    void init(BufferedReader br) throws Exception {
        StringTokenizer token = new StringTokenizer(br.readLine(), " "); 
        n = Integer.parseInt(token.nextToken());
        k = Integer.parseInt(token.nextToken());

        token = new StringTokenizer(br.readLine(), " "); 
        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(token.nextToken());
            sumMod[i] = (sumMod[i - 1] + arr[i]) % k;
        }
    }

    void clear() {
        Arrays.fill(arr, 0);
        Arrays.fill(sumMod, 0);
        Arrays.fill(cnt, 0);
        Arrays.fill(cache, -1);
        Arrays.fill(idx, -1);
    }

    int one() {
        int ret = 0;
        for(int i = 0; i <= n; i++) 
            cnt[sumMod[i]]++;
        for(int i = 0; i < k; i++)
            ret = (int)((ret + (long)cnt[i] * (cnt[i] - 1) / 2) % 20091101);
        return ret;
    }

    int two() {
        for(int i = 0; i <= n; i++) {
            cache[i] = (i == 0) ? 0 : cache[i - 1];
            if(idx[sumMod[i]] != -1)
                cache[i] = Math.max(cache[i], 1 + cache[idx[sumMod[i]]]);
            idx[sumMod[i]] = i;
        }
        return cache[n];
    }

    void solve(BufferedReader br, BufferedWriter bw) throws Exception {
        init(br);
        bw.write(one() + " " + two() + "\n");
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