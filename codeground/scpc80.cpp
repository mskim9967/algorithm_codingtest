/*
    Prob
    2018 SCPC 2차 예선 2번 메모지
    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/

#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

int tc, n;
int dp[2][10010];
int MINUS_INF = INT_MIN, IMPOSSIBLE = INT_MAX;

int ans() {
	for(int i = 0; i <= n; i++)
		if(dp[(n - 1) % 2][i] != IMPOSSIBLE) return i;
	return -1;
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0); cout << fixed; cout.precision(7);
	
	cin >> tc;
	for(int tt = 1; tt <= tc; tt++) {
		fill(&dp[0][0], &dp[1][10010], IMPOSSIBLE);
		vector<pair<int, int>> memo;
		
		cin >> n;
		for(int i = 0, x, y, h; i < n; i++) {
			cin >> x >> y >> h;
			memo.push_back({y, h});
		}
		sort(memo.begin(), memo.end(), less<>());
		
		dp[0][0] = memo[0].first;
		dp[0][1] = MINUS_INF;
		
		for(int i = 1; i < n; i++) {
			for(int j = 0; j <= i + 1; j++) {
				int &now = dp[i % 2][j] = IMPOSSIBLE;
				
				int &prev = dp[(i - 1) % 2][j - 1];
				if(j != 0)
					if(prev == MINUS_INF || prev == IMPOSSIBLE) now = prev;
					else now = prev + memo[i].second;
				
				prev = dp[(i - 1) % 2][j];
				if(prev <= memo[i].first) 
					now = min(now, max(memo[i].first, prev + memo[i].second));
			}
		}
		cout << "Case #" << tt << "\n";
		cout << ans() << "\n";	
	}
    return -1;
}