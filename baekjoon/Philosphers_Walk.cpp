/*
    Prob
    https://www.acmicpc.net/problem/14956

    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/
#include <bits/stdc++.h> 
#include <fstream>
using namespace std;

int n, m; 

pair<int, int> rec(int n, int m) {
    if(n == 2) {
        if(m == 0) return make_pair(1, 1);
        if(m == 1) return make_pair(1, 2);
        if(m == 2) return make_pair(2, 2);
        if(m == 3) return make_pair(2, 1);
    }
    pair<int, int> coord = rec(n / 2, (long)m % ((long)(n / 2) * (n / 2)));
    if(m / (long)((n / 2) * (n / 2)) == 0)
        return make_pair(coord.second, coord.first);
    if(m / (long)((n / 2) * (n / 2)) == 1)
        return make_pair(coord.first, coord.second + n / 2);
    if(m / (long)((n / 2) * (n / 2)) == 2)
        return make_pair(coord.first + n / 2, coord.second + n / 2);
    if(m / (long)((n / 2) * (n / 2)) == 3)
        return make_pair(n - coord.second + 1, n / 2 - coord.first + 1);
}

void solve() {
    pair<int, int> ans = rec(n, m - 1);
    cout << ans.first << " " << ans.second << "\n";
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);

    cin >> n >> m;
    solve();
}