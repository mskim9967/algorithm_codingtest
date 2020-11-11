import java.io.*;
import java.util.*;

class ClockSync {

	final static int[][] swh = { {0, 1, 2, -1, -1}, {3, 7, 9, 11, -1}, {4, 10, 14, 15, -1}, {0, 4, 5, 6, 7}, {6, 7, 8, 10, 12}, {0, 2, 14, 15, -1}, {3, 14, 15, -1, -1}, {4, 5, 7, 14, 15}, {1, 2, 3, 4, 5}, {3, 4, 5, 9, 13} };
	final static int CLK_CNT = 16, SWH_CNT = 10, INT_MAX = 9999;
	int[] clk;
  
	ClockSync(Scanner scanner) {
	  clk = new int[CLK_CNT];
    for(int i = 0; i < CLK_CNT; i++)
      clk[i] = scanner.nextInt() % 12;
	}
  
  void push_swh(int swhNum, int[] clk) {
    for(int i = 0; i < swh[swhNum].length && swh[swhNum][i] != -1; i++)
      clk[swh[swhNum][i]] = (clk[swh[swhNum][i]] + 3) % 12;
  }
  
  boolean isAllClkZero(int[] clk) {
    for(int i = 0; i < CLK_CNT; i++)
      if(clk[i] != 0) return false;
    return true;
  }
  
  int min(int a, int b) { return a < b ? a : b; }
  
  int cnt_min(int swhNum, int[] clk) {
    if(swhNum == SWH_CNT)
      return isAllClkZero(clk) ? 0 : INT_MAX;
    
    int cnt = INT_MAX;
    for(int i = 0; i < 4; i++) {
      int[] clkCp = clk.clone();
	    cnt = min(cnt, i + cnt_min(swhNum + 1, clkCp));
      push_swh(swhNum, clk);
    }
    return cnt;
  }
  
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testCase = sc.nextInt();
		for(int i = 0, ans = 0; i < testCase; i++) {
			ClockSync cs = new ClockSync(sc);
      ans = cs.cnt_min(0, cs.clk);
      System.out.println(ans >= INT_MAX ? -1 : ans);
		}
	}
}
