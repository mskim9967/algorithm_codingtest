import java.util.*;
import java.io.*;

public class Main{
    final int B = 5;
    int board;
    byte[] cache;
    final int[] move1 = {1, 1, B, B, -1, -1, -B, -B, 1, -B, -1, B};
    final int[] move2 = {B, -B, -1, 1, -B, B, 1, -1, 0, 0, 0, 0};

    Main(BufferedReader br) throws Exception {
        board = 0;
        for(int i = 0; i < B; i++) {
            String line = br.readLine();
            for(int j = 0; j < B; j++)
                if(line.charAt(j) == '#') board = (1 << (i * B + j)) | board;
        }
        cache = new byte[1 << (B * B)];
        //for(int i = 0; i < cache.length; i++)
            //System.out.print(cache[i]);
    }
    
    boolean isPossible(int board, int pos, int block) {
        pos += move1[block];
        if(pos < 0 || pos >= B * B)    return false;
        if(((board >> pos) & 1) == 1)    return false;
        
        pos += move2[block];
        if(pos < 0 || pos >= B * B)    return false;
        if(((board >> pos) & 1) == 1)    return false;
        
        return true;
    }

    int update_board(int board, int pos, int block) {
        board = (1 << pos) | board;

        pos += move1[block];
        board = (1 << pos) | board;
        
        pos += move2[block];
        board = (1 << pos) | board;
        
        return board;
    }

    byte isPlayerWin(int board) {
        if(cache[board] != 0)  return cache[board];

        for(int i = 0; i < B * B; i++) {
            if(((board >> i) & 1) == 1) continue;
            
            for(int k = 0; k < 12; k++) {
                if(isPossible(board, i, k)) 
                    if(isPlayerWin(update_board(board, i, k)) == -1)
                        return cache[board] = 1;
            }
        }
        return cache[board] = -1;
    }

    void write_answer(BufferedWriter bw) throws Exception {
        bw.write(isPlayerWin(board) == 1 ? "WINNING\n" : "LOSING\n");
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
