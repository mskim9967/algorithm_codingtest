import java.util.*;
import java.io.*;

public class Main{
    int h, w, r, c;
    byte[][] board;
    byte[][] block;
    ArrayList<byte[][]> blockList;

    Main(BufferedReader br) throws Exception {
        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        h = Integer.parseInt(token.nextToken());
        w = Integer.parseInt(token.nextToken());
        r = Integer.parseInt(token.nextToken());
        c = Integer.parseInt(token.nextToken());
        
        board = new byte[h][w];
        block = new byte[r][c];
        blockList = new ArrayList<byte[][]>();

        for(int i = 0; i < h; i++) {
            String line = br.readLine();
            for(int j = 0; j < w; j++)
                board[i][j] = (line.charAt(j) == '#') ? (byte)1 : (byte)0;
        }
        
        for(int i = 0; i < r; i++) {
            String line = br.readLine();
            for(int j = 0; j < c; j++)
                block[i][j] = (line.charAt(j) == '#') ? (byte)1 : (byte)0;
        }
    }

    void makeBlock() {
        for(int z = 0; z < 4; z++) {
            byte[][] rotatedBlock = (z % 2 == 0) ? new byte[r][c] : new byte[c][r];
            for(int i = 0; i < r; i++)
                for(int j = 0; j < c; j++) {
                    if(z == 0) rotatedBlock[i][j] = block[i][j];
                    if(z == 1) rotatedBlock[j][r - 1 - i] = block[i][j];
                    if(z == 2) rotatedBlock[r - 1 - i][c - 1 - j] = block[i][j];
                    if(z == 3) rotatedBlock[c - 1 - j][i] = block[i][j];
                }
            blockList.add(rotatedBlock);
        }
    }

    void write_answer(BufferedWriter bw) throws Exception {
        makeBlock();
        bw.write("\n");
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        for(int i = 0; i < testCase; i++) {
            Main l = new Main(br);
            l.write_answer(bw);
        }
        bw.flush(); bw.close(); br.close();
    }
}