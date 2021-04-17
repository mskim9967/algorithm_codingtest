#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

int n, m;

int gcd(int n, int m) {
    if(n % m == 0) return m;
    return gcd(m, n % m);
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);
    
    cin >> n >> m;
    
    cout << (m - gcd(m, n)) << "\n";
   
    return 0;
}