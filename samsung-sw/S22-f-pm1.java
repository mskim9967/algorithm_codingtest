//https://www.codetree.ai/training-field/frequent-problems/tail-catch-play

import java.util.*;

public class Main {

    public static class Coord {
        int x, y;
        Coord(int a, int b) {
            x = a; y = b;
        }
        public boolean equals(Object o) {
            return ((Coord)o).x == x && ((Coord)o).y == y;
        };
        public String toString() {
            return String.format("(%d,%d)", x, y);
        }
    }
    public static class Team {
        Coord head, tail;
        int point, length;
        Team(Coord a, Coord b, int c) { head = a; tail = b; point = 0; length = c;}
    }

    static Scanner sc = new Scanner(System.in);
    static int n, m, k;
    static int[][] map = new int[30][30];
    static int[][] teamIdx = new int[30][30];
    static Team[] teamList = new Team[30];
    static int[] dx = {1, 0, 0, -1}, dy = {0, -1, 1, 0};
    final static int TAILTMP = 99999999;
    public static boolean movable(Coord coord) {
        if(coord.x < 0 || coord.x >= n || coord.y < 0 || coord.y >= n) return false;
        return true;
    }

    public static Coord find(Coord curr, int target, Coord except) {
        for(int dir = 0; dir < 4; dir++) {
            Coord next = new Coord(curr.x + dx[dir], curr.y + dy[dir]);
            if(movable(next) && map[next.y][next.x] == target)
                if(except == null || !except.equals(next)) return next;
        }
        return null;
    }

    public static void move(int teamId) {
        Team team = teamList[teamId];
        
        // if(team.head == null) print();

        Coord newHead = find(team.head, -1, null);
        if(newHead == null) newHead = find(team.head, team.length, null);
        team.head = newHead;
        map[team.head.y][team.head.x] = 1;
        
        Coord newTail = find(team.tail, team.length - 1, null);
        if(map[team.tail.y][team.tail.x] != 1) map[team.tail.y][team.tail.x] = -1;
        team.tail = newTail;

        Coord curr = team.head;
        int findVal = 1;
        while(true) {
            curr = find(curr, findVal, null);
            if(curr == null) break;
            map[curr.y][curr.x] = ++findVal;
        }
    }
    public static void print(){
        for(int y = 0; y < n; y++) {
            for(int x = 0; x < n; x++) {
                System.out.print((map[y][x] == -1 ? 9 : map[y][x]) + " ");
            }
            System.out.println();
        }
        System.out.println();

    }

    public static void turn(int teamId) {
        Team team = teamList[teamId];

        Coord curr = team.head, before = null, next = null;
        int findVal = 2;
        while(true) {
            map[curr.y][curr.x] = Math.abs(map[curr.y][curr.x] - team.length) + 1;
            next = find(curr, findVal, before);
            if(next == null) break;
            findVal++;
            before = curr;
            curr = next;

        }
        
        Coord tmp = new Coord(team.tail.x, team.tail.y);
        team.tail = new Coord(team.head.x, team.head.y);
        team.head = tmp;
    }

    public static void main(String[] args) {
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        for(int y = 0; y < n; y++) 
            for(int x = 0; x < n; x++) {
                int input = sc.nextInt();
                if(input == 4) input = -1;
                else if(input == 3) input = TAILTMP;
                map[y][x] = input;
            }

        int teamCnt = 1;
        for(int y = 0; y < n; y++) 
            for(int x = 0; x < n; x++) {
                
                if(map[y][x] != 1) continue;

                Coord head = new Coord(x, y);
                teamIdx[head.y][head.x] = teamCnt;
                Coord body = find(head, 2, null), beforeBody = null, nextBody = null, tail;
                teamIdx[body.y][body.x] = teamCnt;
                int bodyNum = 3;
                while(true) {
                    tail = find(body, TAILTMP, null);
                    if(tail != null)  break;

                    nextBody = find(body, 2, beforeBody);
                    beforeBody = body;
                    body = nextBody;
                    teamIdx[body.y][body.x] = teamCnt;
                    map[body.y][body.x] = bodyNum++;
                }
                teamIdx[tail.y][tail.x] = teamCnt;
                map[tail.y][tail.x] = bodyNum;
                teamList[teamCnt] = new Team(head, tail, bodyNum);
                // System.out.println(teamCnt + ": " +head + tail);

                beforeBody = body;
                body = tail;
                while(true) {
                    tail = find(body, 1, null);
                    if(tail != null)  break;

                    nextBody = find(body, -1, beforeBody);
                    beforeBody = body;
                    body = nextBody;
                    teamIdx[body.y][body.x] = teamCnt;
                }
                teamCnt++;
            }
        
       
        
        for(int round = 0; round < k; round++) {
            // System.out.println("rnd"+round);
            //  if(round >= 4) print();

            for(int teamId = 1; teamId <= m; teamId++) {
                move(teamId);
            }

            int cal = round % (n * 4);

            if((cal / n) == 0) {
                for(int x = 0; x < n; x++) {
                    if(map[cal][x] == -1 || map[cal][x] == 0) continue;
                    // System.out.println("col! " + cal + " " + x);
                    teamList[teamIdx[cal][x]].point += map[cal][x] * map[cal][x];
                    turn(teamIdx[cal][x]);
                    break;
                }
            }
            else if((cal / n) == 2) {
                cal = n - 1 - (cal % n);
                
                for(int x = n -1; x >= 0; x--) {
                    if(map[cal][x] == -1 || map[cal][x] == 0) continue;
                    teamList[teamIdx[cal][x]].point += map[cal][x] * map[cal][x];
                    turn(teamIdx[cal][x]);
                    break;
                }
            }
            else if((cal / n) == 1) {
                cal = (cal % n);
                for(int y = n - 1; y >= 0; y--) {
                    if(map[y][cal] == -1 || map[y][cal] == 0) continue;
                    teamList[teamIdx[y][cal]].point += map[y][cal] * map[y][cal];
                    turn(teamIdx[y][cal]);
                    break;
                }
            }
            else {
                cal = n - 1 - (cal % n);
                for(int y = 0; y < n; y++) {
                    if(map[y][cal] == -1 || map[y][cal] == 0) continue;
                    teamList[teamIdx[y][cal]].point += map[y][cal] * map[y][cal];
                    turn(teamIdx[y][cal]);
                    break;
                }
            }

        }
        int ans = 0;
        for(int teamId = 1; teamId <= m; teamId++) {
            ans+= teamList[teamId].point;
        }
        System.out.println(ans);
    }
}
