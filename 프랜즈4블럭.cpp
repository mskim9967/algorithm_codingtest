/*
    Prob
    https://programmers.co.kr/learn/courses/30/lessons/17679
    
    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/

#include <bits/stdc++.h> 
using namespace std;

char bd[30][30];
bool chk[30][30];

void check(int x, int y) {
    if(bd[y][x] != 0 && bd[y][x] == bd[y + 1][x] && bd[y][x] == bd[y][x + 1] && bd[y][x] == bd[y + 1][x + 1])
        chk[y][x] = chk[y + 1][x] = chk[y][x + 1] = chk[y + 1][x + 1] = true;
}

int update(int m, int n) {
    int cnt = 0;
    for(int y = m - 1; y >= 0; y--) // 위에서 아래로
        for(int x = 0; x < n; x++)
            if(chk[y][x]) {
                cnt++;
                for(int yy = y; yy < m; yy++) 
                    bd[yy][x] = (yy == m - 1) ? 0 : bd[yy + 1][x];
            }
    return cnt;
}

int solution(int m, int n, vector<string> board) {
    int ans = 0;
    for(int y = 0; y < m; y++)
        for(int x = 0; x < n; x++) 
            bd[m - y - 1][x] = board[y][x];

    while(true) {
        memset(chk, 0, sizeof(chk));
        for(int y = 0; y < m - 1; y++)
            for(int x = 0; x < n - 1; x++) 
                check(x, y);
        int blockCnt = update(m, n);
        if(blockCnt == 0) break; // 깰 블럭이 없으면 종료
        ans += blockCnt;
    }
    
    return ans;
}
