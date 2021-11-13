/*
    Prob
    SCPC 1회 예선 방속의 거울
    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/

#include <bits/stdc++.h> 
#include <fstream>
using namespace std;

int tc, n, k;
int mmap[1010][1010];
bool check[1010][1010];
int dx[4] = {1, 0, -1, 0}, dy[4] = {0, 1, 0, -1};

void solve() {
    int ans = 0, x = 0, y = 0, dir = 0;
    memset(check, false, sizeof(check));

    cin >> n;    
    for(int i = 0; i < n; i++) {
        string line;
        cin >> line;
        for(int j = 0; j < n; j++)
            mmap[i][j] = line[j] - '0';
    }

    while(x >= 0 && y >= 0 && x < n && y < n) { // 빛이 밖으로 나오는지 확인
        if(mmap[y][x] != 0) { // 빛이 거울에 닿았을 경우
            if(!check[y][x]) { // 비친 거울 갯수 확인
                check[y][x] = true;
                ans++;
            }
            // 반사 방향 정하기
            if(dir == 0) dir = mmap[y][x] == 1 ? 3 : 1; 
            else if(dir == 1) dir = mmap[y][x] == 1 ? 2 : 0; 
            else if(dir == 2) dir = mmap[y][x] == 1 ? 1 : 3; 
            else if(dir == 3) dir = mmap[y][x] == 1 ? 0 : 2; 
        }
        y += dy[dir], x += dx[dir]; // 빛 이동
    }    
    cout << ans << "\n";
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0); cout << fixed; cout.precision(7);

    cin >> tc;
	for(int tt = 1; tt <= tc; tt++) {
        cout << "Case #" << tt << "\n";
        solve();
	}
    return 0;
} 
