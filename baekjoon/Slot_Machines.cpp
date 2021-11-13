/*
    Prob
    https://www.acmicpc.net/problem/14959

    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/
#include <bits/stdc++.h> 
#include <fstream>
using namespace std;

int n;
int a[1000005], f[1000005];

void solve() {
    int sum = INT_MAX, k, p;
    for(int i = 0, j = 0; i < n; i++) {
        while(j && a[i] != a[j])  j = f[j - 1]; 
        if(i && a[i] == a[j]) f[i] = ++j;

        if(sum > n - f[i]) // (n - 1 - i) + (i + 1 - f[i]) = n - f[i]
            sum = n - f[i], k = n - 1 - i, p = i + 1 - f[i];
    }

    cout << k << " " << p  << "\n";
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);
    
    cin >> n;
    for(int i = n - 1; i >= 0; i--) cin >> a[i];

    solve();
}