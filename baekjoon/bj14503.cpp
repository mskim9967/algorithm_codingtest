/*
    Prob
    https://www.acmicpc.net/problem/14503

    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/
#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

int n, m, r, c, d, ans;
int mmap[50][50];
int dx[4] = {0, 1, 0, -1}, dy[4] = {-1, 0, 1, 0};

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);
    
    cin >> n >> m >> r >> c >> d;
    for(int y = 0; y < n; y++)
        for(int x = 0; x < m; x++)
            cin >> mmap[y][x];

    bool canMove = false;
    do {
        mmap[r][c] = 2; // clear area

        canMove = false;
        for(int i = 0; i < 4; i++) {
            d = (d - 1 + 4) % 4; // rotate
            if(mmap[r + dy[d]][c + dx[d]] == 0) { // can clear rotated area
                canMove = true;
                r += dy[d], c += dx[d];
                break;
            }
            if(i == 3) { // can't clear all 4 areas
                if(mmap[r - dy[d]][c - dx[d]] != 1) { // can go back
                    canMove = true;
                    r -= dy[d], c -= dx[d];
                }
            }
        }
    } while(canMove); // break when can't move
    
    for(int y = 0; y < n; y++)
        for(int x = 0; x < m; x++)
            if(mmap[y][x] == 2) ans++; // count clear area

    cout << ans << '\n';

    return 0;
}