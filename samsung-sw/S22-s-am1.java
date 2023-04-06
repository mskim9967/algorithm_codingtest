//https://www.codetree.ai/training-field/frequent-problems/codetree-mon-bread

import java.util.*;

public class Main {

    static int n, m;
    static int[][] map = new int[20][20];
    static boolean[][] chk = new boolean[20][20];
    static Coord[] aimConvs = new Coord[40];
    public static HashMap<Integer, Coord> persons = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    static int[] dx = {0, -1, 1, 0}, dy = {-1, 0, 0, 1};

    public static class Coord {
        int x, y, firstDir;
        Coord(int a, int b) {x = a; y = b;}
        Coord(int a, int b, int c) {x = a; y = b; firstDir =c;}
        @Override
        public String toString() {
            return String.format("%d,%d", x, y);
        }
    }
    static boolean isMovable(Coord coord) {
        if(coord.x < 0 || coord.x >= n || coord.y < 0 || coord.y >= n ) return false;
        return !chk[coord.y][coord.x];
    }
    public static Boolean move(Integer id) {
        Coord aimConv = aimConvs[id];
        Coord person = persons.get(id);
        // System.out.println("im" + id+ "and in" + person);
        Queue<Coord> q = new LinkedList<>();
        q.add(new Coord(person.x, person.y));
        boolean isFirst = true;
        while(!q.isEmpty()) {
            Coord curr = q.poll();
            for(int dir = 0; dir < 4; dir++) {
                Coord next = isFirst ? new Coord(curr.x + dx[dir], curr.y + dy[dir], dir)
                 : new Coord(curr.x + dx[dir], curr.y + dy[dir], curr.firstDir);
                //System.out.println("next pos is" + next);
                if(isMovable(next)){
                    //System.out.println("movable");
                    q.add(next);
                    
                    if(next.y == aimConv.y && next.x == aimConv.x) {
                        //System.out.println("zzzzzzzzzzzz");
                        if(isFirst) {
                            // persons.remove(id);
                            //System.out.println(id + " arrived");
                            chk[next.y][next.x] = true;
                            return true;
                        }
                        else persons.put(id, new Coord(person.x + dx[next.firstDir], person.y + dy[next.firstDir]));
                        return false;
                    }
                }
            }
            isFirst = false;
        }
        return false;
    }

    static Coord findNearBc(Coord conv) {
        Queue<Coord> q = new LinkedList<>();
        q.add(new Coord(conv.x, conv.y));
        while(!q.isEmpty()) {
            Coord curr = q.poll();
            for(int dir = 0; dir < 4; dir++) {
                Coord next = new Coord(curr.x + dx[dir], curr.y + dy[dir]);
                if(isMovable(next)){
                    q.add(next);
                    if(map[next.y][next.x] == 1) {
                        chk[next.y][next.x] = true;
                        return next;
                    }
                }
            }
        }
        return null;
    }
    
    public static void main(String[] args) {

        n = sc.nextInt();
        m = sc.nextInt();

        for(int y = 0; y < n; y++)
            for(int x = 0; x < n; x++)
                map[y][x] = sc.nextInt();

        for(int i = 1; i <= m; i++){
            int y = sc.nextInt() - 1;
            int x = sc.nextInt() - 1; 
            aimConvs[i] = new Coord(x, y);
        }
        
        int time = 0;
        do {
            time++;
            //System.out.println("currTIme: " + time);
            persons.entrySet().removeIf(e->move(e.getKey()));
            // for(int pid : persons.keySet()) {
            //         if(move(pid)) persons.remove(pid);
            // }
            if(time <= m)
                persons.put(time, findNearBc(aimConvs[time]));

            
        } while(persons.size() != 0);
        System.out.println(time);
    }
}
