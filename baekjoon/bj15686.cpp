/*
    Prob
    https://www.acmicpc.net/problem/15686

    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/
#include <bits/stdc++.h> 
#include <fstream>
using namespace std;

int n, m, ans = INT_MAX;
int mmap[50][50];
vector<pair<int, int>> chics, homes;
bool chk[13];

void solve() {
    int sum = 0;
    for(pair<int, int> home : homes) {
        int mmin = INT_MAX;
        for(int i = 0; i < chics.size(); i++) {
            if(!chk[i]) continue;
            mmin = min(mmin, abs(home.first - chics[i].first) + abs(home.second - chics[i].second));
        }
        sum += mmin;
    }
    ans = min(ans, sum);
}

void comb(int now, int cnt) {
    if(cnt == m) {
        solve();
        return;
    }
    if(now == chics.size()) return;

    chk[now] = true;
    comb(now + 1, cnt + 1);
    chk[now] = false;
    comb(now + 1, cnt);
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);

    cin >> n >> m;
    for(int i = 0; i < n; i++) 
        for(int j = 0; j < n; j++) {
            cin >> mmap[i][j];
            if(mmap[i][j] == 1) homes.push_back(make_pair(j, i)); 
            else if(mmap[i][j] == 2) chics.push_back(make_pair(j, i));
        }

    comb(0, 0);

    cout << ans << "\n"; 

    return 0;
}