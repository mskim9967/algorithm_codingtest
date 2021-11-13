/*
    Prob
    2019 SCPC 2차 예선 2번 유사도
    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/

#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

int tc;
int n;
int a[5010], b[5010], smt[5010];

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0); cout << fixed; cout.precision(7);
	
	cin >> tc;
	for(int tt = 1; tt <= tc; tt++) {
		memset(smt, 0, sizeof(smt));
		
		cin >> n;
		for(int i = 1; i <= n; i++)	cin >> a[i];
		for(int i = 1; i <= n; i++)	cin >> b[i];

		for(int i = 1; i <= n; i++)
			smt[i] = a[i] == b[i] ? smt[i - 1] + 1 : smt[i - 1];
		
		int ans = smt[n];
		// 홀수
		for(int i = 1; i <= n; i++) {
			int r = 1, sum = a[i] == b[i] ? 1 : 0;
			for(; i - r >= 1 && i + r <= n; r++) {
				if(a[i + r] == b[i - r]) sum++;
				if(a[i - r] == b[i + r]) sum++;
				ans = max(ans, sum + smt[i - r - 1] + smt[n] - smt[i + r]);
			}
		}
		// 짝수
		for(int i = 1; i <= n; i++) {
			int r = 1, sum = 0;
			for(; i - r + 1 >= 1 && i + r <= n; r++) {
				if(a[i + r] == b[i - r + 1]) sum++;
				if(a[i - r + 1] == b[i + r]) sum++;
				ans = max(ans, sum + smt[i - r] + smt[n] - smt[i + r]);
			}
		}
		
		cout << "Case #" << tt << "\n";
		cout << ans << "\n";
	}
    return 0;
}