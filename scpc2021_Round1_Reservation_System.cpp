/*
    Prob
    2020 SCPC 2차 예선 1번 실력 맞추기
    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/

#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

int tc;
int n, m, oddCnt;
bool isOdd[20010];
int s[20010][4];
long long ans;

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0); cout << fixed; cout.precision(7);
	
	cin >> tc;
	for(int tt = 1; tt <= tc; tt++) {
		memset(s, 0, sizeof(s));
		memset(isOdd, 0, sizeof(isOdd));
		ans = 0;
		oddCnt = 0;
		int max1 = 0, max2 = 0, evenmax = 0;
		bool max1odd = false, max2odd = false;
		
		cin >> n >> m;
		for(int i = 0, l; i < n; i++) {
			cin >> l;
			if(l % 2 == 1) {
				isOdd[i] = true; 
				oddCnt++;
			}
			int a, b, c, d;
			a = b = c = d = 10000000;
			for(int j = 0, w; j < l; j++) {
				cin >> w;
				if(w < a) 
					d = c, c = b, b = a, a = w;
				else if(w < b)
					d = c, c = b, b = w;
				else if(w < c)
					d = c, c = w;
				else if(w < d)
					d = w;
			}
			s[i][0] = d, s[i][1] = c, s[i][2] = b, s[i][3] = a;
			if(c + d > max1) {
				max2 = max1, max1 = c + d;
				max2odd = max1odd, max1odd = l % 2;
			}
			else if(c + d > max2) {
				max2 = c + d;
				max2odd = l % 2;
			}
			
			if(isOdd[i]) {
				ans += d + c + b + a * 2;
			}
			else {
				ans += d + c + b + a;
				evenmax = max(evenmax, d + c);
			}
		}
		ans = ans - max1 - max2;
	
		if(oddCnt == 2 && max1odd && max2odd) {
			ans = ans + max2 - evenmax;
			long long newans = 0;
			for(int i = 0; i < n; i++) {
				if(isOdd[i]) newans += s[i][2] + s[i][3] * 2;
				else newans += s[i][3] * 2 + s[i][2] * 2 + s[i][1] + s[i][0];
			}
			ans = min(ans, newans);
		}
		
		
		cout << "Case #" << tt << "\n";
	
		cout << ans << "\n";
	}
    return 0;
}