/*
    Prob
    https://www.acmicpc.net/problem/15486

    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/
#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

int N;
//pair<int, int> work[1500000];
int wd[1500000], pay[1500000];
long cache[1500000];

long dp(int today);

long solve() {
    memset(cache, -1, sizeof(cache));
    return dp(0);
}

long dp(int today) {
    if(today >= N)  return 0;
    long &ret = cache[today];
    if(ret != -1) return ret;

    ret = dp(today + 1);
    if(today + wd[today] <= N)
        ret = max(ret, pay[today] + dp(today + wd[today]));

    return ret;
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);

    cin >> N;
    for(int i = 0; i < N; i++) {
        cin >> wd[i] >> pay[i];
    }

    cout << solve() << "\n";
}