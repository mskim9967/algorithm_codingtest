import java.util.*;
import java.io.*;

public class Main{
    int n;
    int[] board;
    int[][] cache;
    
    Main(BufferedReader br) throws Exception {
        n =Integer.parseInt(br.readLine());
        board = new int[n];
        
        String[] parse = br.readLine().split(" ");
        for(int i = 0; i < board.length; i++)
            board[i] = Integer.parseInt(parse[i]);
            
        cache = new int[n][n];
        for(int[] row: cache) 
            Arrays.fill(row, Integer.MAX_VALUE);
    }
  
    int calc_gap(int l, int r) {
        if(l > r)  return 0;
        if(cache[l][r] != Integer.MAX_VALUE)    return cache[l][r];
        
        int ret = Math.max(board[l] - calc_gap(l + 1, r), board[r] - calc_gap(l, r - 1));
        if(r - l + 1 >= 2) {
            ret = Math.max(ret, -calc_gap(l + 2, r));
            ret = Math.max(ret, -calc_gap(l, r - 2));
        }
        
        return cache[l][r] = ret;
    }
    
    void write_answer(BufferedWriter bw) throws Exception {
        bw.write(calc_gap(0, n - 1)+"");
        bw.write("\n");
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        for(int i = 0; i < testCase; i++) {
            Main d = new Main(br);
            d.write_answer(bw);
        }
        bw.flush(); bw.close(); br.close();
    }
}
