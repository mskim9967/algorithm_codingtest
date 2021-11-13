/*
    Prob
    https://www.acmicpc.net/problem/13460

    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/
#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

// Operator Overloading
template <typename T,typename U>                                                   
pair<T, U> operator+(const pair<T, U> &l, const pair<T,U> &r) {   
    return { l.first + r.first, l.second + r.second };                                    
}  
template <typename T,typename U>   
pair<T, U> operator-(const pair<T, U> &l, const pair<T,U> &r) {   
    return { l.first - r.first, l.second - r.second };                                    
}  

typedef pair<int, int> Coord;
const int MAX = 10, R = 0, L = 1, U = 2, D = 3;
Coord VEC[4] = { make_pair(1, 0), make_pair(-1, 0), make_pair(0, -1), make_pair(0, 1)};
int N, M;
char board[MAX][MAX];
Coord blue, red, hole;
struct MV {
    Coord pos;
    int wei;
    MV(Coord _pos, int _wei): pos(_pos), wei(_wei){}
};
queue<MV> rq, bq;

// return coord after tilt to dir(inside hole, or beside wall)
Coord move(Coord pos, int dir) {
    while(board[pos.second][pos.first] != 'O' && board[pos.second][pos.first] != '#')
         pos = pos + VEC[dir];
    return board[pos.second][pos.first] == 'O' ? pos : pos - VEC[dir];
}

int solve() {
    Coord rPos = red, bPos = blue;
    int wei = 0;
    rq.push(MV(rPos, wei)); bq.push(MV(bPos, wei));

    // BFS! queues are both pushed and poped
    while(!rq.empty()) {
        wei = rq.front().wei + 1;
        rPos = rq.front().pos; rq.pop();
        bPos = bq.front().pos; bq.pop();

        // for each direction(RIGHT, LEFT, UP, DOWN)
        for(int dir = 0; dir < 4; dir++) {
            Coord rPosNext = move(rPos, dir), bPosNext = move(bPos, dir);
            
            // if beads don't move when tilt to dir
            if(rPosNext == rPos && bPosNext == bPos) continue;

            if(rPosNext == bPosNext) {
                //if both beads fall in hole
                if(bPosNext == hole) continue;

                // adjust bead's coord
                if(dir == R) {
                    if(rPos.first < bPos.first) rPosNext.first--;
                    else bPosNext.first--;
                }
                else if(dir == L) {
                    if(rPos.first < bPos.first) bPosNext.first++;
                    else rPosNext.first++;
                }
                else if(dir == U) {
                    if(rPos.second < bPos.second) bPosNext.second++;
                    else rPosNext.second++;
                }
                else if(dir == D) {
                    if(rPos.second < bPos.second) rPosNext.second--;
                    else bPosNext.second--;
                }
            }

            if(bPosNext == hole) continue;
            if(rPosNext == hole) return wei;
            
            if(wei < 10) {
                rq.push(MV(rPosNext, wei));
                bq.push(MV(bPosNext, wei));
            }
        }
    }
    return -1;
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);

    cin >> N >> M;
    for(int i = 0; i < N; i++)
        for(int j = 0; j < M; j++) { 
            cin >> board[i][j];
            if(board[i][j] == 'B') blue = make_pair(j, i);
            else if(board[i][j] == 'R') red = make_pair(j, i);
            else if(board[i][j] == 'O') hole = make_pair(j, i);
        }

    cout << solve() << "\n";
}