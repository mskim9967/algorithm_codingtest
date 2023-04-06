//https://www.codetree.ai/training-field/frequent-problems/tree-tycoon

package codingtest;

import java.util.*;
import java.io.*;

public class Main {

	static int n, m, medCnt;
	static int[] dx = {1, 1, 0, -1, -1, -1, 0, 1}, dy = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] crosses = {1, 3, 5, 7};
	static int[][] map = new int[20][20];
	static Coord[] meds = new Coord[400];
	static int[][] medDirs = new int[120][2];
	
	static void moveMed(int id, int dir, int cnt) {
		Coord med = meds[id];
		for(int i = 0; i < cnt; i++) {
			med.x = ((med.x - 1+ dx[dir] + n) % (n)) + 1;
			med.y = ((med.y - 1+ dy[dir] + n) % (n)) + 1;
		}
	}
	
	static class Coord {
		int x, y;
		Coord(int a, int b) { x=a;y=b;}
		Coord(Coord c) { x=c.x;y=c.y;}
		public String toString() {return String.format("(%d,%d)",x,y);}
	}
	
	
    public static void main(String[] args) throws FileNotFoundException {
    	 File file = new File("input.txt");
         Scanner sc = new Scanner(file);
         n = sc.nextInt(); m = sc.nextInt();
         for(int y= 1; y <= n; y++)
        	 for(int x = 1; x <= n; x++)
        		 map[y][x] = sc.nextInt();
         
         for(int i= 0; i < m; i++) {
        	 medDirs[i][0] = sc.nextInt() - 1;
        	 medDirs[i][1] = sc.nextInt();
         }
         
         meds[0] = new Coord(1, n-1);
         meds[1] = new Coord(2, n-1);
         meds[2] = new Coord(1, n);
         meds[3] = new Coord(2, n);
         medCnt = 4;
        	 
         
         for(int yr = 0; yr < m; yr++) {
        	 for(int i = 0; i < medCnt; i++) {
        		 moveMed(i, medDirs[yr][0], medDirs[yr][1]);
        		 map[meds[i].y][meds[i].x]++;
        	 }
        	 boolean[][] chk = new boolean[20][20];
        	 
        	 for(int i = 0; i < medCnt; i++) {
        		 chk[meds[i].y][meds[i].x] = true;
        		 for(int ci = 0; ci < 4; ci++) {
        			 if(map[meds[i].y+dy[crosses[ci]]][meds[i].x+dx[crosses[ci]]] != 0)
        				 map[meds[i].y][meds[i].x]++;
        		 }
        	 }
        	 
        	 medCnt = 0;
        	 
        	 for(int y= 1; y <= n; y++)
            	 for(int x = 1; x <= n; x++) {
            		 if(map[y][x] < 2 || chk[y][x]) continue;
            		 meds[medCnt] = new Coord(x, y);
            		 map[y][x] -= 2;
            		 medCnt++;
            	 }
         }
         
         int ans = 0;
         for(int y= 1; y <= n; y++)
        	 for(int x = 1; x <= n; x++) 
        		 ans+=map[y][x];
         System.out.println(ans);
         
    }
}


