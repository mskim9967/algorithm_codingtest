/*
    Prob
    https://www.acmicpc.net/problem/14888

    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/
#include <bits/stdc++.h> 
#include <fstream>
using namespace std;

int n, resMax = INT_MIN, resMin = INT_MAX;
int a[11], op[4];

void rec(int idx, int res) {
    if(idx == n) { // end Case
        resMax = max(res, resMax);
        resMin = min(res, resMin);
        return ;
    }
    for(int i = 0; i < 4; i++) {
        if(op[i] == 0) continue; // no more operator

        op[i]--;
        if(i == 0) rec(idx + 1, res + a[idx]);
        else if(i == 1) rec(idx + 1, res - a[idx]);
        else if(i == 2) rec(idx + 1, res * a[idx]);
        else rec(idx + 1, res / a[idx]);
        op[i]++;
    }
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);

    cin >> n;
    for(int i = 0; i < n; i++) cin >> a[i];
    for(int i = 0; i < 4; i++) cin >> op[i];
    
    rec(1, a[0]);

    cout << resMax << "\n" << resMin << "\n"; 

    return 0;
}