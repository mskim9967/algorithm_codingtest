import java.util.*;
import java.io.*;

public class Main{
    int[] arr, arrSum;
    int[][] cache;
    int s;
    
    Main(BufferedReader br) throws Exception {
        String[] parse = br.readLine().split(" ");
        arr = new int[Integer.parseInt(parse[0])];
        arrSum = new int[Integer.parseInt(parse[0])];
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
        for(int i = 1; i < arr.length; i++)
            arrSum[i] = arr[i] + arrSum[i - 1];

    }
    
    int min(int a, int b) {return a < b ? a : b;}
    
    int quantizate(int idx, int num) {
        if(num == 0)    return 0;
        if(idx >= arr.length)   return 9999999;
        
        if(cache[idx][num - 1] != -1)  return cache[idx][num - 1];
        int ret = 99999999;
        for(int i = idx; i < arr.length; i++) {
            int mid = (int)(Math.round((arrSum[i] - arrSum[idx] + arr[idx]) / (float)(i - idx + 1)));
            //System.out.println(mid );
            int sqSum = 0;
            for(int j = idx; j <= i; j++)
                sqSum += (arr[j] - mid) * (arr[j] - mid);
            ret = min(ret, sqSum + quantizate(i + 1, num - 1));
            System.out.println(ret);
        }
        return cache[idx][num - 1] = ret;
    }
   
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        for(int i = 0; i < testCase; i++) {
            Main q = new Main(br);
            bw.write(q.quantizate(0, q.s)+ "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

}
