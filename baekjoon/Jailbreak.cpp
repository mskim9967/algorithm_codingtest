/*
    Prob
    https://www.acmicpc.net/problem/9376

    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/
#include <bits/stdc++.h> 
#include <fstream>
using namespace std;
struct Coord {
    int x, y;
    Coord(){};
    Coord(int _x, int _y) : x(_x), y(_y){};
    Coord operator+(const Coord& o) const{
        return Coord(x + o.x, y + o.y);
    }
};
Coord VEC[4] = { Coord(0, 1), Coord(0, -1), Coord(1, 0), Coord(-1, 0) };
struct cmp {
    bool operator()(pair<int, Coord> a, pair<int, Coord> b) {
        return a.first > b.first;
    }
};

int h, w, minDoorCnt;
char m[102][102];
int c1[102][102], c2[102][102], c3[102][102];
Coord p1, p2, p3;

void bfs(int (&c)[102][102], Coord start) {
    priority_queue<pair<int, Coord>, vector<pair<int, Coord>>, cmp> pq;
    c[start.y][start.x] = 0;
    pq.push(make_pair(0, start));

    while(!pq.empty()) {
        pair<int, Coord> pop = pq.top(); pq.pop();
        Coord now = pop.second;

        for(Coord dir : VEC) {
            Coord next = dir + now;
            if(next.x < 0 || next.y < 0 || next.x >= w + 2 || next.y >= h + 2) continue;
            if(m[next.y][next.x] == '*' || c[next.y][next.x] != -1) continue;

            int nextDoorCnt = pop.first;
            if(m[next.y][next.x] == '#') nextDoorCnt++;
            c[next.y][next.x] = nextDoorCnt;
            pq.push(make_pair(nextDoorCnt, next));
        }
    }
}


int solve() {
    p3 = Coord(0, 0);
    memset(c1, -1, sizeof(c1));
    memset(c2, -1, sizeof(c2));
    memset(c3, -1, sizeof(c3));

    // start bfs from p and save door's opening count at c
    bfs(c1, p1);
    bfs(c2, p2);
    bfs(c3, p3);

    for(int y = 0; y < h + 2; y++) {
        for(int x = 0; x < w + 2; x++) {
            if(c1[y][x] == -1) continue;
            int sum = c1[y][x] + c2[y][x] + c3[y][x];
            if(m[y][x] == '#') sum -= 2;
            minDoorCnt = min(minDoorCnt, sum);
        }
    }
    return minDoorCnt;
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);
    int tc;
    cin >> tc;
    while(tc--) {
        minDoorCnt = INT_MAX;
        bool p1Found = false;
        cin >> h >> w;
        for(int i = 0; i < h + 2; i++)
            for(int j = 0; j < w + 2; j++) {
                //surround map by empty space
                if(i == 0 || j == 0 || i == h + 1 || j == w + 1) m[i][j] = '.';
                else {
                    cin >> m[i][j];
                    if(m[i][j] == '$') {
                        if(p1Found) p2 = Coord(j,i);
                        else p1 = Coord(j, i);
                        p1Found = true;
                    }
                }
            }
        cout << solve() << "\n";
    }
    
}

/*
dfs(time over)
#include <bits/stdc++.h> 
#include <fstream>
using namespace std;
struct Coord {
    int x, y;
    Coord(int _x, int _y) : x(_x), y(_y){};
    Coord operator+(const Coord& o) const{
        return Coord(x + o.x, y + o.y);
    }
};
Coord VEC[4] = { Coord(0, 1), Coord(0, -1), Coord(1, 0), Coord(-1, 0) };

int h, w;
char m[102][102];
int minDoorCnt;
bool visited[102][102], visitedCP[102][102];

void dfs(Coord now, int doorCnt, bool findOne) {
    if(doorCnt >= minDoorCnt) return;

    if(m[now.y][now.x] == '$') {
        if(findOne) minDoorCnt = min(minDoorCnt, doorCnt);
        else {
            memcpy(visitedCP, visited, sizeof(visited));
            memset(visited, false, sizeof(visited));
            visited[now.y][now.x] = true;
            m[now.y][now.x] = '.';
            dfs(now, doorCnt, true); 
            m[now.y][now.x] = '$';
            memcpy(visited, visitedCP, sizeof(visited)); 
        }   
    }
    else {
        for(Coord dir : VEC) {
            Coord next = dir + now;
            if(next.x < 0 || next.y < 0 || next.x >= w + 2 || next.y >= h + 2) continue;
            if(m[next.y][next.x] == '*' || visited[next.y][next.x]) continue;
            
            visited[next.y][next.x] = true;
            if(m[now.y][now.x] == '#') {
                m[now.y][now.x] = '.';
                dfs(next, 1 + doorCnt, findOne);
                m[now.y][now.x] = '#';
            } 
            else
                dfs(next, doorCnt, findOne);
            visited[next.y][next.x] = false;
        }
    }
} 

int solve() {
    visited[0][0] = true;
    dfs(Coord(0,0), 0, false);
    return minDoorCnt;
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);
    int tc;
    cin >> tc;
    while(tc--) {
        memset(m, 0, sizeof(m));
        minDoorCnt = INT_MAX;
        memset(visited, false, sizeof(visited));
        cin >> h >> w;
        for(int i = 0; i <= h + 1; i++)
            for(int j = 0; j <= w + 1; j++) {
                if(i == 0 || j == 0 || i == h + 1 || j == w + 1) m[i][j] = '.';
                else cin >> m[i][j];
            }
        cout << solve() << "\n";
    }
    
}
*/