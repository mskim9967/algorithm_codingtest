/*
    Prob
    https://www.acmicpc.net/problem/2468

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
Coord VEC[4] = { Coord(0, 1), Coord(0, -1), Coord(1, 0), Coord(-1, 0) };

int N;
int m[100][100];
bool visited[100][100];

void bfs(int x, int y) {
    queue<Coord> queue;
    queue.push(Coord(x, y));
    visited[y][x] = true;
    while(!queue.empty()) {
        Coord pop = queue.front(); queue.pop();
        for(Coord dir : VEC) {
            Coord next = pop + dir;
            if(next.x < 0 || next.x >= N || next.y < 0 || next.y >= N) continue;
            if(visited[next.y][next.x] || m[next.y][next.x] == INT_MAX) continue;
            visited[next.y][next.x] = true;
            queue.push(next);
        }
    }
}

int solve() {
    int ret = 1;
    for(int hei = 1; hei <= 100; hei++) {
        int safeArea = 0;
        memset(visited, false, sizeof(visited));

        // check area under water
        for(int y = 0; y < N; y++)
            for(int x = 0; x < N; x++)
                if(m[y][x] <= hei) m[y][x] = INT_MAX;

        // search and count safe area
        for(int y = 0; y < N; y++) 
            for(int x = 0; x < N; x++) {
                if(visited[y][x] || m[y][x] == INT_MAX) continue;
                bfs(x, y);
                safeArea++;
            }

        ret = max(ret, safeArea);
    }
    return ret;
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);
    
    cin >> N;
    for(int i = 0; i < N; i++)
        for(int j = 0; j < N; j++)
            cin >> m[i][j];

    cout << solve() << "\n";
}