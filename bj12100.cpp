/*
    Prob
    https://www.acmicpc.net/problem/12100

    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/
#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

int n, ans;
int mmap[22][22];
int dx[4] = {1 , -1, 0, 0}, dy[4] = {0, 0, 1 , -1};

void move(int dir) {
    bool check[22][22] = {false, };
    for(int i = (dir==1||dir==3)?0:n*n-1; (dir==1||dir==3)?(i < n * n): (i >= 0); (dir==1||dir==3)?i++:i--) {
        int x = (i % n) + 1, y = i / n + 1, nx = x + dx[dir], ny = y + dy[dir];
        if(mmap[y][x]==0) continue;

        while(mmap[ny][nx] == 0)
            ny += dy[dir], nx += dx[dir];

        if(mmap[ny][nx] == mmap[y][x] && !check[ny][nx]) 
            mmap[ny][nx] *= 2, mmap[y][x] = 0, check[ny][nx] = true;
        else if(x != nx - dx[dir] || y != ny - dy[dir])
            mmap[ny - dy[dir]][nx - dx[dir]] = mmap[y][x], mmap[y][x] = 0;
    }
}

void rec(int moveCnt) {
    if(moveCnt == 5) {
        for(int y = 1; y < n + 1; y++)
            for(int x = 1; x < n + 1; x++) 
                ans = max(ans, mmap[y][x]);
        return ;
    }
 
    int mmapCp[22][22]; 
    memcpy(mmapCp, mmap, sizeof(mmap));
    for(int dir = 0; dir < 4; dir++) {
        memcpy(mmap, mmapCp, sizeof(mmap));
        move(dir);
        rec(moveCnt + 1);
    }
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);

    memset(mmap, -1, sizeof(mmap));
    
    cin >> n;
    for(int y = 1; y < n + 1; y++)
        for(int x = 1; x < n + 1; x++)
            cin >> mmap[y][x];

    rec(0);
    
    cout << ans << "\n";
    return 0;
}