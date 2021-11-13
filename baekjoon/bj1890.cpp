#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

int n;
int bd[100][100];
long long c[100][100];

long long dp(int x, int y) {
    if(x == n - 1 && y == n - 1) return 1;
    if(x >= n || y >= n || bd[y][x] == 0) return 0;
    
    long long &ret = c[y][x];
    if(ret != -1) return ret;

    return ret = dp(x + bd[y][x], y) + dp(x, y + bd[y][x]);
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);
    
    memset(c, -1, sizeof(c));

    cin >> n;
    for(int i = 0; i < n; i++) 
        for(int j = 0; j < n; j++)
            cin >> bd[i][j];
    
    cout << dp(0, 0) << "\n"; 

    return 0;
}