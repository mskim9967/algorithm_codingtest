/*
    Prob
    2018 SCPC 2차 예선 2번 메모지
    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/

#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

int tc;
long long r;

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0); cout << fixed; cout.precision(7);
	
	cin >> tc;
	for(int tt = 1; tt <= tc; tt++) {
		long long ans = 0;
		
		cin >> r;
		
		for(long long x = 1; x < r; x++) {
			double y = sqrt(r * r - x * x);
			ans += (long long)y;
			if(x * x + (long long)y * (long long)y == r * r) ans--;
		}
		
		ans += r - 1;
		ans *= 4;
		ans += 1;
		
		cout << "Case #" << tt << "\n";
		cout << ans << "\n";	
	}
    return -1;
}