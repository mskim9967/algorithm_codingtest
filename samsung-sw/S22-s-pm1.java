//https://www.codetree.ai/training-field/frequent-problems/battle-ground


import java.util.*;
import java.io.*;

public class Main {

    static class Coord {
        int x, y;
        Coord(int a, int b) {x = a; y = b;}
        Coord(Coord o) {x = o.x; y = o.y;}
        public boolean equals(Coord o) { return o.x == x && o.y == y; }
        public String toString() { return String.format("(%d,%d)", x, y);}
    }

    static class Person {
        Coord pos;
        int dir, wei, gunWei, point, id;
        Person(Coord a, int b, int c, int d, int e) {pos = a; dir = b; wei = c; gunWei = d; point = 0; id = e;}
        public String toString() { return String.format("[%s,%d,wei:%d,gunWei:%d]", pos, dir,wei,gunWei);}
        public void turnBack() {
        	if(dir == 0) dir = 2;
        	else if(dir == 2) dir = 0;
        	else if(dir == 1) dir = 3;
        	else if(dir == 3) dir = 1;
        }
        public void turnRight() {
        	dir = (dir + 1) % 4;
        }
        public boolean move(Coord c) {
        	Coord next = new Coord(c);
        	if(movable(next)) {
        		personMap[pos.y][pos.x] = 0;
        		pos = next;
        		personMap[pos.y][pos.x] = id;
        		return true;
        	}
        	return false;
        }
        public Coord getnextoord() {
        	Coord next = new Coord(pos.x + dx[dir], pos.y + dy[dir]);
        	if(movable(next)) return next;
        	turnBack();
        	return new Coord(pos.x + dx[dir], pos.y + dy[dir]);
        }
        public Coord getLoserCoord() {
        	Coord next = new Coord(pos.x + dx[dir], pos.y + dy[dir]);
        	while(!movable(next) || personMap[next.y][next.x] != 0) { 
        		turnRight();
        		next = new Coord(pos.x + dx[dir], pos.y + dy[dir]);
        	}
        	return next;
        }
        public void compareGun() {
        	if(map[pos.y][pos.x].peek() > gunWei) {
        		int tmp = gunWei;
        		gunWei = map[pos.y][pos.x].poll();
        		map[pos.y][pos.x].add(tmp);
        	}
        }
        public void dropGun() {
    	
    		map[pos.y][pos.x].add(gunWei);
        	
        	gunWei = 0;
        }
    }

    static boolean movable(Coord c) {
    	if(c.x < 0 || c.x >= n || c.y < 0 || c.y >= n) return false;
    	return true;
    }
    
    
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int n, m, k;
    static PriorityQueue<Integer>[][] map = new PriorityQueue[30][30];
    static int[][] personMap = new int[30][30];
    static Person[] personList = new Person[40];

    public static void main(String[] args) throws FileNotFoundException {
    	//  File file = new File("input.txt");
         Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        for(int y = 0; y < n; y++)
            for(int x = 0; x < n; x++) {
                map[y][x] = new PriorityQueue<Integer>((a,b)->{return a < b? 1 : -1;});
                map[y][x].add(sc.nextInt());
            }
        for(int i = 1; i <= m; i++) {
        	int y = sc.nextInt() - 1;
        	int x = sc.nextInt() - 1;
            personList[i] = new Person(new Coord(x, y), sc.nextInt(), sc.nextInt(), 0, i);
            personMap[personList[i].pos.y][personList[i].pos.x] = i;
        }
        
        for(int round = 1; round <= k; round++) {
        	for(int i = 1; i <= m; i++) {
        		Person person = personList[i];
        		Coord next = person.getnextoord();
        		int beatPersonId = personMap[next.y][next.x];
        		person.move(next);
        		if(beatPersonId != 0) { //fight        			
        			Person loser, winner;
        			Person beatP = personList[beatPersonId];
        			if((beatP.gunWei + beatP.wei < person.gunWei + person.wei) || 
        				((beatP.gunWei + beatP.wei == person.gunWei + person.wei) && beatP.wei <person.wei)
					) {
        				loser = beatP;
        				winner = person;
        			}
        			else {
        				winner = beatP;
        				loser = person;
        			}
        			winner.point += (winner.gunWei + winner.wei - loser.gunWei - loser.wei);
        			
        			loser.dropGun();
        			loser.move(loser.getLoserCoord());
        			loser.compareGun();
        			
        			personMap[winner.pos.y][winner.pos.x] = winner.id;
        			winner.compareGun();
        			
        			
        		}
        		else { //get gun
        			person.compareGun();
        		}
//        		for(int yy = 0; yy < n; yy++) {
//        			for(int xx = 0; xx < n; xx++) System.out.print(personMap[yy][xx] + " ");
//        			System.out.println();
//        		}
        			
        	}
        }
        for(int i = 1; i <= m; i++)
        	System.out.print(personList[i].point + " ");
    }
}
