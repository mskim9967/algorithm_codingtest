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
int n;
long long a[200010], b[200010];

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0); cout << fixed; cout.precision(7);
    
	cin >> tc;
	for(int tt = 1; tt <= tc; tt++) {
		long long cache[200010][2][2];

		cin >> n;
		
		for(int i = 1; i <= n; i++) cin >> a[i];
		for(int i = 1; i <= n; i++) cin >> b[i];
		
		sort(a + 1, a + n + 1);
		sort(b + 1, b + n + 1);
		
		for(int i = 1; i <= n; i++) {
			cache[i][0][0] = cache[i - 1][0][0] + abs(a[i] - b[i]);
			cache[i][1][0] = min(cache[i - 1][0][0], cache[i - 1][1][0] + abs(a[i] - b[i - 1]));
			cache[i][0][1] = min(cache[i - 1][0][0], cache[i - 1][0][1] + abs(a[i - 1] - b[i]));
			cache[i][1][1] = min(min(cache[i][1][0], cache[i][0][1]), cache[i - 1][1][1] + abs(a[i] - b[i]));
		}
		
		cout << "Case #" << tt << "\n";
		cout << min(cache[n][0][0], cache[n][1][1]) << "\n";
	}
    return 0;
}