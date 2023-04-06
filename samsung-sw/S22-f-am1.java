//https://www.codetree.ai/training-field/frequent-problems/hide-and-seek


import java.util.*;
import java.io.*;

public class Main {

	static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
	static Person[] runners = new Person[10000];
	static boolean[][] isTreeExist = new boolean[100][100];
	static Set<Integer>[][] runnerPos = new Set[100][100];
	static int n, m, h, k; 
	static Person me;
	
	static class Coord {
		int x, y;
		Coord(int a, int b){ x = a; y =b;}
		Coord(Coord c){ x = c.x; y =c.y;}
		public boolean equals(Coord c) {return x==c.x && y==c.y;}
		public String toString() {return String.format("(%d,%d)", x, y);}
	}
	
	static class Person {
		Coord pos;
		int dir, turnTime, turnCnt, repeatCnt, id;
		boolean isCw, isRunner, isDead;
		Person(Coord a, int b, boolean c, int d) {
			pos = a; dir =b; isCw=true; isRunner=c;
			turnCnt = 0;
			repeatCnt = 2;
			turnTime = 1;
			id = d;
			isDead = false;
			if(isRunner) runnerPos[pos.y][pos.x].add(id);
		}
		void turnRight(int t) {
			dir = (dir + t) % 4;
		}
		void turnBack() {
			turnRight(2);
		}
		void move(Coord c) {
			if(isRunner) runnerPos[pos.y][pos.x].remove(id);
			pos = new Coord(c); 
			if(isRunner) runnerPos[pos.y][pos.x].add(id);

		}
		boolean isMovable() {
			Coord next = new Coord(pos.x + dx[dir], pos.y + dy[dir]);
			return movable(next);
		}
		Coord nextPos() {
			return new Coord(pos.x + dx[dir], pos.y + dy[dir]);
		}
		boolean isIn3() {
			return (Math.abs(pos.x - me.pos.x) + Math.abs(pos.y - me.pos.y) <= 3); 
		}
	}
	static boolean movable(Coord c) {
		return c.x >= 0 && c.x < n && c.y >= 0 && c.y < n;
	}
	
	static void printRunners() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) System.out.print(runnerPos[i][j].size());
			System.out.println();
		}
		System.out.println();
	}
	
    public static void main(String[] args) throws FileNotFoundException {
    	//  File file = new File("input.txt");
         Scanner sc = new Scanner(System.in);
         
         n = sc.nextInt();m = sc.nextInt();h = sc.nextInt();k = sc.nextInt();
         
         for(int i = 0; i < n; i++)
        	 for(int j = 0; j < n; j++)
        		 runnerPos[i][j] = new HashSet<>();
         
         for(int i = 1; i <= m; i++) {
        	int y= sc.nextInt() - 1, x = sc.nextInt() -1, dir = sc.nextInt();
        	runners[i] = new Person(new Coord(x, y), dir, true, i);
         }
         for(int i = 1; i <= h; i++) {
         	int y= sc.nextInt()-1, x = sc.nextInt()-1;
         	isTreeExist[y][x] = true;
          }
         
         me = new Person(new Coord(n/2, n/2), 0, false, 0);
         int ans = 0;
         for(int rnd = 1; rnd <=k; rnd++) {
        	 for(int ri = 1; ri <= m; ri++) {
        		 Person runner = runners[ri];
        		 if(runner.isDead) continue;
        		 if(runner.isIn3()) {
        			 if(!runner.isMovable()) runner.turnBack();
        			 Coord next = runner.nextPos();
        			 if(!next.equals(me.pos)) runner.move(next);
        		 }
        		 
        	 }
        	 
    		 me.move(me.nextPos());
    		 me.turnCnt++;
        	 
    		 if(me.turnTime==n&&me.turnCnt == n - 1) {
    			 me.isCw = false;
    			 me.turnBack();
    			 me.turnCnt = 0;
    			 me.turnTime = n - 1;
    			 me.repeatCnt = 3;
    		 }
    		 else if(me.pos.equals(new Coord(n/2, n/2))) {
    			 me.isCw = true;
    			 me.turnBack();
    			 me.turnCnt = 0;
    			 me.turnTime = 1;
    			 me.repeatCnt = 2;
    		 }
    		 else if(me.turnTime == me.turnCnt) {
        		 if(me.repeatCnt == 1) {
        			 if(me.isCw) me.turnTime++;
        			 else me.turnTime--;
        			 
        			 me.repeatCnt = 2;
        		 }
        		 else {
        			 me.repeatCnt--;
        		 }
        		 me.turnCnt = 0;
        		 
        		 me.turnRight(me.isCw?1:3);
        	 }
//        	 System.out.print(me.pos);
    		 
    		 int cnt = 0;
    		 Coord coord = new Coord(me.pos);
    		 for(int i = 0; i < 3; i++) {
	    		 if(!isTreeExist[coord.y][coord.x]) {
	    			 cnt += runnerPos[coord.y][coord.x].size();
	    			 for(int ri : runnerPos[coord.y][coord.x]) runners[ri].isDead = true;
	    			 runnerPos[coord.y][coord.x].clear();
	    		 }
	    		 coord.y += dy[me.dir];
				 coord.x += dx[me.dir];
				 if(!movable(coord)) break;
    		 }
    		 ans += cnt * rnd;
    		 
         }
//          printRunners();   
         
         
        System.out.println(ans);
    }
}
