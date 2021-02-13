/*
    Prob
    https://www.acmicpc.net/problem/14500

    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/
#include <bits/stdc++.h> 
#include <fstream>

using namespace std;
typedef pair<int, int> Coord;

int N, M;
int board[500][500], visit[500][500];

Coord VEC[4] = {make_pair(1, 0), make_pair(-1, 0), make_pair(0, 1), make_pair(0, -1)};
Coord EX[4][3] = { {make_pair(1, 0), make_pair(-1, 0), make_pair(0, 1)},
                    {make_pair(1, 0), make_pair(-1, 0), make_pair(0, -1)},
                    {make_pair(1, 0), make_pair(0, 1), make_pair(0, -1)},
                    {make_pair(-1, 0), make_pair(0, 1), make_pair(0, -1)} };

struct ele {
    int dep, wei;
    Coord coord, before;
    ele(int _dep, int _wei, Coord _coord, Coord _before) : dep(_dep), wei(_wei), coord(_coord), before(_before){};
};

int bfs(Coord c) {
    int ret = 0;
    queue<ele> q;
    q.push(ele(1, board[c.second][c.first], c, c));

    while(!q.empty()) {
        ele pop = q.front(); q.pop();
        if(pop.dep == 4) {
            ret = max(ret, pop.wei);
            continue;
        }
        for(Coord vec : VEC) {
            Coord next = make_pair(pop.coord.first + vec.first, pop.coord.second + vec.second);
            if(next.first < 0 || next.first >= M || next.second < 0 || next.second >= N || next == pop.before) continue;

            q.push(ele(pop.dep + 1, pop.wei + board[next.second][next.first], next, pop.coord));
        }
    }
    return ret;
}

int dfs(Coord now, int depth) {
    int ret = 0;
    if(depth == 4) return board[now.second][now.first];
    visit[now.second][now.first] = 1;
    for(Coord vec : VEC) {
        Coord next = make_pair(now.first + vec.first, now.second + vec.second);
        if(next.first < 0 || next.first >= M || next.second < 0 || next.second >= N || visit[next.second][next.first]) continue;
        ret = max(ret, board[now.second][now.first] + dfs(next, depth + 1));
    }
    visit[now.second][now.first] = 0;
    return ret;
}
 
int solve() {
    int ret = 0;
    // for each cell
    for(int i = 0; i < N; i++)
        for(int j = 0; j < M; j++) {
            // dfs
            ret = max(ret, dfs(make_pair(j, i), 1));
            
            // calculate exceptions(ㅗㅓㅏㅜ)
            for(int k = 0; k < 4; k++) {
                bool possible = true;
                int wei = board[i][j];
                for(Coord c : EX[k]) {
                    Coord next = make_pair(j + c.first, i + c.second);
                    wei += board[next.second][next.first];
                    if(next.first < 0 || next.first >= M || next.second < 0 || next.second >= N) {
                        possible = false;
                        break;
                    }
                }
                if(possible) ret = max(ret, wei);
            }
        }
    return ret;
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);

    cin >> N >> M;
    for(int i = 0; i < N; i++)
        for(int j = 0; j < M; j++)
            cin >> board[i][j];

    cout << solve() << "\n";
}