/*
    Prob
    https://www.acmicpc.net/problem/13458

    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/
#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

int a[1000000];
int n, b, c;

long solve() {
    // careful with overflow
    long ret = 0;
    for(int i = 0; i < n; i++) {
        a[i] -= b;
        ret++;
        
        if(a[i] > 0){
            ret += a[i] / c;
            if(a[i] % c != 0) ret++;
        } 
    }
    return ret;
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);

    cin >> n;
    for(int i = 0; i < n; i++) cin >> a[i];
    cin >> b >> c;
    cout << solve() << "\n";
}