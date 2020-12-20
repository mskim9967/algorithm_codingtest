import java.util.*;
import java.io.*;

public class Main{
    final int LEN = 5, RIGHT = 1, LEFT = -1, UP = -LEN, DOWN = LEN;
    int board;
    byte[] cache;
    final int[] MOVE1 = {RIGHT, DOWN, DOWN, LEFT, RIGHT, UP, LEFT, DOWN};
    final int[] MOVE2 = {DOWN, LEFT, RIGHT, DOWN, 0, 0, 0, 0};

    Main(BufferedReader br) throws Exception {
        cache = new byte[1 << LEN * LEN];
    }
    
    boolean isPossible(int board, int pos, int block) {
        pos += MOVE1[block];
        if((MOVE1[block] == RIGHT || MOVE1[block] == LEFT) && 
            (pos - MOVE1[block]) / LEN != pos / LEN)    return false;
        if(pos < 0 || pos >= LEN * LEN)    return false;
        if(((board >> pos) & 1) == 1)    return false;
        
        pos += MOVE2[block];
        if((MOVE2[block] == RIGHT || MOVE2[block] == LEFT) && 
            (pos - MOVE2[block]) / LEN != pos / LEN)    return false;
        if(pos < 0 || pos >= LEN * LEN)    return false;
        if(((board >> pos) & 1) == 1)    return false;
        
        return true;
    }

    int update_board(int board, int pos, int block) {
        board = (1 << pos) | board;
       
        pos += MOVE1[block];
        board = (1 << pos) | board;
       
        pos += MOVE2[block];
        return (1 << pos) | board;
    }

    byte isPlayerWin(int board) {
        if(cache[board] != 0)  return cache[board];

        for(int i = 0; i < LEN * LEN; i++) {
            if(((board >> i) & 1) == 1) continue;
            
            for(int block = 0; block < MOVE1.length; block++) {
                if(isPossible(board, i, block)) 
                    if(isPlayerWin(update_board(board, i, block)) == -1)
                        return cache[board] = 1;
            }
        }
        return cache[board] = -1;
    }

    void write_answer(BufferedReader br, BufferedWriter bw) throws Exception {
        board = 0;
        for(int i = 0; i < LEN; i++) {
            String line = br.readLine();
            for(int j = 0; j < LEN; j++)
                if(line.charAt(j) == '#') board = (1 << (i * LEN + j)) | board;
        }
        bw.write(isPlayerWin(board) == 1 ? "WINNING\n" : "LOSING\n");
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        Main d = new Main(br);
        for(int i = 0; i < testCase; i++)
            d.write_answer(br, bw);
        bw.flush(); bw.close(); br.close();
    }
}
