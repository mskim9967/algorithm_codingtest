import java.util.*;
import java.io.*;

public class Main{
    class Coord {
        int x, y; 
        Coord(int x, int y) { this.x = x; this.y = y; }
    }
    final int H = 0, V = 1;
    int n, q;
    int[][] board;
    int[][][] hint;
    int[][][] hintVal;
    int[][][] hintSize, hintStart, hintEnd;
    int[][][] possibleNums;
    int[][] answer;

    Main(BufferedReader br) throws Exception {
        possibleNums = new int[10][46][1024];

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        hint = new int[2][n][n];
        hintVal = new int[2][n][n];
        hintSize = new int[2][n][n];
        hintStart = new int[2][n][n];
        hintEnd = new int[2][n][n];
        answer = new int[n][n];
        
        for(int i = 0; i < n; i++) {
            StringTokenizer token = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j++)
                board[i][j] = Integer.parseInt(token.nextToken());
        }

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                if(board[i][j] == 1)  answer[i][j] = -1;
        
        q = Integer.parseInt(br.readLine());
        for(int i = 0; i < q; i++) {
            String[] parse = br.readLine().split(" ");
            hint[Integer.parseInt(parse[2])][Integer.parseInt(parse[0]) - 1][Integer.parseInt(parse[1]) - 1] = Integer.parseInt(parse[3]);
        }
    }

    int get_size(int set) {
        int ret = 0;
        for(; set != 0; set = set >> 1)
            if((set & 1) == 1) ret++;
        return ret;
    }

    int get_sum(int set) {
        int ret = 0;
        for(int comp = 1, num = 1; comp <= set; comp = comp << 1, num++)
            if((set & comp) == comp) ret += num;
        return ret;
    }

    void init() {
        for(int set = 0; set < 512; set++) {
            int size = get_size(set), sum = get_sum(set);
            for(int subSet = set; subSet >= 0; subSet = subSet == 0 ? -1 : (subSet - 1) & set)
                possibleNums[size][sum][subSet] |= (set & ~subSet); 
        }
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 0) continue;
                
                int k = j;
                while(hint[H][i][k] == 0) k--;
                hintVal[H][i][j] = hint[H][i][k];

                k = i;
                while(hint[V][k][j] == 0) k--;
                hintVal[V][i][j] = hint[V][k][j];

                int start = j, end = j;
                while(board[i][start] == 1) {start--;}
                while(end != n && board[i][end] == 1) end++;
                hintStart[H][i][j] = ++start;
                hintEnd[H][i][j] = --end;
                hintSize[H][i][j] = end - start + 1;

                start = i; end = i;
                while(board[start][j] == 1) start--;
                while(end != n && board[end][j] == 1) end++;
                hintStart[V][i][j] = ++start;
                hintEnd[V][i][j] = --end;
                hintSize[V][i][j] = end - start + 1;
            }
        }
    }

    int get_possibleNums(int x, int y) {
        int knownH = 0, knownV = 0;
        
        for(int i = hintStart[H][y][x]; i <= hintEnd[H][y][x]; i++)
            if(answer[y][i] > 0) knownH |= (1 << (answer[y][i] - 1));

        for(int i = hintStart[V][y][x]; i <= hintEnd[V][y][x]; i++)
            if(answer[i][x] > 0) knownV |= (1 << (answer[i][x] - 1));

                System.out.println(possibleNums[hintSize[H][y][x]][hintVal[H][y][x]][knownH]);
        return possibleNums[hintSize[H][y][x]][hintVal[H][y][x]][knownH] &
            possibleNums[hintSize[V][y][x]][hintVal[V][y][x]][knownV];
    }

    boolean ans() {
        int x = -1, y = -1, min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(answer[i][j] == -1 && get_size(get_possibleNums(j, i)) < get_size(min)) {
                    min = get_possibleNums(j, i);
                    x = j; y = i;
                }
            }
        }
        System.out.println(x + " " + y + " " + min);

        if(min == 0) return false;
        if(x == -1) return true;
        
        for(int i = 0; i < 9; i++) {
            if(((min >> i) & 1) == 1) {
                System.out.println(i);
                int[][] cp = new int[n][n];
                for(int j = 0; j < n; j++)
                    cp[j] = answer[j].clone();
                answer[y][x] = i;    
                if(ans() == true) return true;
                answer = cp;
            }
        }
        return false;
    }

    void write_answer(BufferedWriter bw) throws Exception {
        init();
                                  System.out.println(ans());
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++)
                bw.write(answer[i][j] + " ");
            bw.write("\n");
        }
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