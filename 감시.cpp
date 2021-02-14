/*
    Prob
    https://www.acmicpc.net/problem/15683

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
Coord VEC[4] = { {Coord(1, 0)}, {Coord(0, 1)}, {Coord(-1, 0)}, {Coord(0, -1)} };
vector<Coord> cams;

vector<int> C[5] = { {0}, {0, 2}, {0, 3}, {0, 2, 3}, {0, 1, 2, 3} };

int count_blind_spot() {
    int cnt = 0;
    for(int y = 0; y < N; y++)
        for(int x = 0; x < M; x++)
            if(bd[y][x] == 0) cnt++;
    return cnt;
}

void update_watch_area(int cidx, int dir) {
    Coord cam = cams[cidx];
    for(int vidx : C[bd[cam.y][cam.x] - 1]) {
        Coord now = cam;
        while(true) {
            now = now + VEC[(vidx + dir) % 4];
            // is next out of range or next is wall
            if(now.x < 0 || now.x >= M || now.y < 0 || now.y >= N || bd[now.y][now.x] == 6) break;
            // next is watched area
            if(bd[now.y][now.x] == 0) bd[now.y][now.x] = -1;
        }
    }
}

int cam_set(int cidx) {
    // is bd updated by all camera's watch area 
    if(cidx == cams.size()) return count_blind_spot();

    int ret = INT_MAX;
    // for all direction
    for(int dir = 0; dir < 4; dir++) {
        int cp[8][8];
        memcpy(cp, bd, sizeof(bd));
        update_watch_area(cidx, dir);
        ret = min(ret, cam_set(cidx + 1));
        memcpy(bd, cp, sizeof(bd));
    }
    return ret;
}

int solve() {
    return cam_set(0);
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);

    cin >> N >> M;
    for(int y = 0; y < N; y++)
        for(int x = 0; x < M; x++) {
            cin >> bd[y][x];
            if(bd[y][x] != 0 && bd[y][x] != 6) cams.push_back(Coord(x, y));
        }
    cout << solve() << "\n";
}