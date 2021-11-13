/*
    Prob
    2019 SCPC 예선 2번 공 굴리기
    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/


#include <bits/stdc++.h> 
#include <fstream>
#include <cmath>

using namespace std;

int tc;
double ans;
int R, S, E, N;

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0); cout << fixed; cout.precision(7);
    
	cin >> tc;
	
	for(int tt = 1; tt <= tc; tt++) {
		ans = 0;
		
		cin >> R >> S >> E >> N;
		
		double now = S;
		for(int i = 0; i < N; i++) {
			int l, r, h;
			cin >> l >> r >> h;
			
			ans += l - now - R;
			if(h >= R) {
				ans += 2 * h - 2 * R + r - l + R * M_PI;
				now = r + R;
			}
			else {
				ans += r - l + R - sqrt(2 * R * h - h * h) + (R * M_PI - 2 * (R * asin((R-h)/(double)R)));
				now = r + sqrt(2 * R * h - h * h);
			}
		}
		ans += E - now;
		
		cout << "Case #" << tt << "\n";
		cout << ans << "\n";
	}

    return 0;
}