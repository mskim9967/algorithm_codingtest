/*
    Prob
    https://www.acmicpc.net/problem/14499

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
struct Dice {
    int a, b, c, d, e, f;
    Dice(int _a, int _b, int _c, int _d, int _e, int _f) : 
        a(_a), b(_b), c(_c), d(_d), e(_e), f(_f){};
};

 
int N, M, K;
int bd[20][20];
int order[1000];
Coord dPos(0, 0);
Coord VEC[4] = {{Coord(1, 0)}, {Coord(-1, 0)}, {Coord(0, -1)}, {Coord(0, 1)}};

void solve() {
    Coord now = dPos;
    Dice dice = Dice(0, 0, 0, 0, 0, 0);
    for(int move : order) {
        if(move == 0) break;
        Coord next = now + VEC[move - 1];
        if(next.x < 0 || next.x >= M || next.y < 0 || next.y >= N) continue;

        if(bd[now.y][now.x] == 0)
            bd[now.y][now.x] = dice.d;
        else {
            dice.d = bd[now.y][now.x];
            bd[now.y][now.x] = 0;
        }
        
        if(move == 1) dice = Dice(dice.a, dice.e, dice.c, dice.f, dice.d, dice.b);
        else if(move == 2) dice = Dice(dice.a, dice.f, dice.c, dice.e, dice.b, dice.d);
        else if(move == 3) dice = Dice(dice.b, dice.c, dice.d, dice.a, dice.e, dice.f);
        else if(move == 4) dice = Dice(dice.d, dice.a, dice.b, dice.c, dice.e, dice.f);

        cout << dice.b << "\n";

        now = next;
    }
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);

    cin >> N >> M >> dPos.y >> dPos.x >> K;
    for(int y = 0; y < N; y++)
        for(int x = 0; x < M; x++)
            cin >> bd[y][x];
    for(int i = 0; i < K; i++)
        cin >> order[i];

    solve();
}