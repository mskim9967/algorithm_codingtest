/*
    Prob
    SCPC 1회 균일수

    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/

#include <bits/stdc++.h> 
#include <fstream>
using namespace std;

int t, n;

int solve() {
    cin >> n;
    
    for(int base = 2; base <= 31623; base++) {
        int nn = n, digit;
        do {
            digit = nn % base;
            nn /= base;
            if(nn == 0) return base;
        } while(digit == nn % base);
    }
    
    for(int digit = 31623; digit >= 2; digit--)
        if(n % digit == 0) return n / digit - 1;
    
    return n - 1;
}

int main(void) {
    //freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0); cout << fixed; cout.precision(7);

    cin >> t;
	for(int tt = 1; tt <= t; tt++) 
        cout << "Case #" << tt << "\n" << solve() << "\n";

    return 0;
}
