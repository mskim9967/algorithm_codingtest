import java.util.*;
import java.io.*;

public class Main{
    String[] arr;
    int k;
    int[][] overlapped, cache;
    
    Main(BufferedReader br) throws Exception {
        int k = Integer.parseInt(br.readLine());
        ArrayList<String> list = new ArrayList<String>();
        list.add("0");
        for(int i = 0; i < k; i++) {
            String line = br.readLine();
            for(int j = 0; j < list.size(); j++) {
                if(list.get(j).contains(line))
                    break;
                if(j == list.size() - 1) {
                    list.add(line);
                    break;
                }
            }
        }
        arr = list.toArray(new String[list.size()]);
        
        overlapped = new int[arr.length][arr.length];
        for(int i = 0; i < arr.length; i++)
            for(int j = 0; j < arr.length; j++)
                overlapped[i][j] = calc_overlapped(arr[i], arr[j]);
        
        cache = new int[arr.length][1 << arr.length];
        for(int[] x : cache)
            Arrays.fill(x, -1);
    }
    
    int calc_overlapped(String a, String b) {
        int ret = 0;
        int len = Math.min(a.length(), b.length());
        for(int i = 0; i < len; i++)
            if(b.substring(0, i + 1).equals(a.substring(a.length() - 1 - i)))
                ret = i + 1;
        
        return ret;
    }
    
    int calc_len(int now, int picked) {
        if(cache[now][picked] != -1)    return cache[now][picked];
        
        int ret = 0;
        for(int next = 1; next < arr.length; next++) {
            if(((picked >> next) & 1) == 1) continue;
            int calc = overlapped[now][next] + calc_len(next, (1 << next) | picked);
            ret = calc > ret ? calc : ret;
        }
        return cache[now][picked] = ret;
    }
    
    void write_str(BufferedWriter bw, int now, int picked) throws Exception {
        for(int next = 1; next < arr.length; next++) {
            if(((picked >> next) & 1) == 1) continue;
            
            if(calc_len(now, picked) - calc_len(next, (1 << next) | picked) == overlapped[now][next]) {
                bw.write(arr[next].substring(overlapped[now][next]));
                write_str(bw, next, (1 << next) | picked);
                return;
            } 
        }
    }
    
    void write_answer(BufferedWriter bw) throws Exception {
        write_str(bw, 0, 0);
        bw.write("\n");
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        for(int i = 0; i < testCase; i++) {
            
            Main z = new Main(br);
            
            z.write_answer(bw);
        }
        bw.flush(); bw.close(); br.close();
    }
}
