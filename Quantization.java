import java.util.*;
import java.io.*;

public class Main{
    int[] arr, arrSum, arrSqSum;
    int[][] cache;
    int s;
    final int INT_MAX = Integer.MAX_VALUE / 2;
    
    Main(BufferedReader br) throws Exception {
        String[] parse = br.readLine().split(" ");
        arr = new int[Integer.parseInt(parse[0])];
        arrSum = new int[Integer.parseInt(parse[0])];
        arrSqSum = new int[Integer.parseInt(parse[0])];
        s = Integer.parseInt(parse[1]);
        cache = new int[arr.length][s];
        for(int i = 0; i < cache.length; i++)
            for(int j = 0 ;j < cache[i].length; j++)
                cache[i][j] = -1;
        
        parse = br.readLine().split(" ");
        for(int i = 0; i < arr.length; i++) 
            arr[i] = Integer.parseInt(parse[i]);
        Arrays.sort(arr);
        
        arrSum[0] = arr[0];
        arrSqSum[0] = arr[0] * arr[0];
        for(int i = 1; i < arr.length; i++) {
            arrSum[i] = arr[i] + arrSum[i - 1];
            arrSqSum[i] = arr[i] * arr[i] + arrSqSum[i - 1];
        }
    }
    
    int quantizate(int idx, int num) {
        if(idx == arr.length || num == 0) {
            if(idx == arr.length && num == 0)    return 0;
            return INT_MAX;
        }
        if(cache[idx][num - 1] != -1)  return cache[idx][num - 1];
        
        int ret = INT_MAX;
        for(int i = idx; i < arr.length; i++) {
            int mid = (int)(Math.round((arrSum[i] - arrSum[idx] + arr[idx]) / (float)(i - idx + 1)));
            int sqSum = (arrSqSum[i] - arrSqSum[idx] + arr[idx] * arr[idx]) - (2 * mid * (arrSum[i] - arrSum[idx] + arr[idx])) + (mid * mid * (i - idx + 1));
            ret = Integer.min(ret, sqSum + quantizate(i + 1, num - 1));
        }
        return cache[idx][num - 1] = ret;
    }
   
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        for(int i = 0; i < testCase; i++) {
            Main q = new Main(br);
            if(q.arr.length <= q.s)
                bw.write("0\n");
            else    
                bw.write(q.quantizate(0, q.s)+ "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
