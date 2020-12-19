import java.util.*;
import java.io.*;

public class Main{
    final int B = 5;
    int board;
    Boolean[] cache;
    final int[] move1 = {1, 1, B, B, -1, -1, -B, -B, 1, -B, -1, B};
    final int[] move2 = {B, -B, -1, 1, -B, B, 1, -1, 0, 0, 0, 0};

    Main(BufferedReader br) throws Exception {
        board = 0;
        for(int i = 0; i < B; i++) {
            String line = br.readLine();
            for(int j = 0; j < B; j++)
                if(line.charAt(j) == '#') board = (1 << (i * B + j)) | board;
        }
        cache = new Boolean[1 << B * B];
    }
    
    boolean isPossible(int board, int pos, int type) {
        if(isXfirst[type]) x += moveX[type];
        else    y += moveY[type];
        if(x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE)    return false;
        if((board >> (y * BOARD_SIZE + x) & 1) == 1)    return false;
        
        if(isXfirst[type]) y += moveY[type];
        else    x += moveX[type];
        if(x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE)    return false;
        if((board >> (y * BOARD_SIZE + x) & 1) == 1)    return false;
        
        return true;
    }

    int update_board(int board, int x, int y, int type) {
        board = (1 << (y * BOARD_SIZE + x)) | board;
        
        if(type == 0) {
            board = (1 << (y * BOARD_SIZE + ++x)) | board;
            board = (1 << (++y * BOARD_SIZE + x)) | board;
        }
        else if(type == 1) {
            board = (1 << (y * BOARD_SIZE + ++x)) | board;
            board = (1 << (--y * BOARD_SIZE + x)) | board;
        }
        else if(type == 2){
            board = (1 << (++y * BOARD_SIZE + x)) | board;
            board = (1 << (y * BOARD_SIZE + --x)) | board;
        }
        else if(type == 3) {
            board = (1 << (++y * BOARD_SIZE + x)) | board;
            board = (1 << (y * BOARD_SIZE + ++x)) | board;
        }
        else if(type == 4) {
            board = (1 << (y * BOARD_SIZE + --x)) | board;
            board = (1 << (--y * BOARD_SIZE + x)) | board;
        }
        else if(type == 5) {
            board = (1 << (y * BOARD_SIZE + --x)) | board;
            board = (1 << (++y * BOARD_SIZE + x)) | board;
        }
        else if(type == 6) {
            board = (1 << (--y * BOARD_SIZE + x)) | board;
            board = (1 << (y * BOARD_SIZE + ++x)) | board;
        }
        else if(type == 7) {
            board = (1 << (--y * BOARD_SIZE + x)) | board;
            board = (1 << (y * BOARD_SIZE + --x)) | board;
        }
        else if(type == 8)
            board = (1 << (y * BOARD_SIZE + ++x)) | board;
        else if(type == 9)
            board = (1 << (--y * BOARD_SIZE + x)) | board;
        else if(type == 10)
            board = (1 << (y * BOARD_SIZE + --x)) | board;
        else if(type == 11)
            board = (1 << (++y * BOARD_SIZE + x)) | board;
        return board;
    }

    Boolean isPlayerWin(int board) {
        if(cache[board] != null)  return cache[board];
        Boolean ret = false;
        for(int i = 0; i < B * B; i++) {
                if(((board >> (i)) & 1)== 1) continue;
                
                for(int k = 0; k < 12; k++) {
                    if(isPossible(board, i, k)) {
                        ret = !isPlayerWin(update_board(board, j, i, k));
                        if(ret == true) return cache[board] = ret;
                    }
                }
            }
        }
        return cache[board] = ret;
    }

    void write_answer(BufferedWriter bw) throws Exception {
        bw.write(isPlayerWin(board) ? "WINNING\n" : "LOSING\n");
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
