/*
    Prob
    https://www.acmicpc.net/submit/14502

    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/
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
 
int N, M;
int bd[8][8];
Coord VEC[4] = {{Coord(1, 0)}, {Coord(-1, 0)}, {Coord(0, -1)}, {Coord(0, 1)}};
vector<Coord> virus;

int bfs() {
    int bd2[8][8];
    queue<Coord> queue;
    memcpy(bd2, bd, sizeof(bd2));

    for(Coord vPos : virus) queue.push(vPos);

    while(!queue.empty()) {
        Coord now = queue.front(); queue.pop();
        bd2[now.y][now.x] = 2;
        for(Coord vec : VEC) {
            Coord next = now + vec;
            if(next.x < 0 || next.x >= M || next.y < 0 || next.y >= N || bd2[next.y][next.x] != 0) continue;

            queue.push(next);
        }
    } 
    // count safe area
    int cnt = 0;
    for(int y = 0; y < N; y++)
        for(int x = 0; x < M; x++)
            if(bd2[y][x] == 0) cnt++;
    return cnt;
}

int setWall(int pos, int wallCnt) {
    // set 3 walls and spread virus
    if(wallCnt == 3) return bfs();
    if(pos == M * N) return 0;

    // don't set wall at pos
    int ret = setWall(pos + 1, wallCnt);
    if(bd[pos / M][pos % M] == 0) {
        bd[pos / M][pos % M] = 1;
        // set wall at pos
        ret = max(ret, setWall(pos + 1, wallCnt + 1));
        bd[pos / M][pos % M] = 0;
    }
    return ret;
}

int solve() {
    return setWall(0, 0);
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);

    cin >> N >> M;
    for(int y = 0; y < N; y++)
        for(int x = 0; x < M; x++) {
            cin >> bd[y][x];
            if(bd[y][x] == 2) virus.push_back(Coord(x, y));
        }
    cout << solve() << "\n";
}