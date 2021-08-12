/*
    Prob
    2021 SCPC 2차 예선 2번 직8각형(오답)
    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/

#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

int tc, k;
set<pair<int, int>> pt;

int dist(pair<int, int> a, pair<int, int> b) {
	return abs(a.first - b.first) + abs(a.second - b.second);
}


int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0); cout << fixed; cout.precision(7);
	
	cin >> tc;
	for(int tt = 1; tt <= tc; tt++) {
		int ans = 100000;
		bool ff = true;
		cin >> k;
		
		for(int i = 0, x, y; i < 8; i++) {
			cin >> x >> y;
			pt.insert({x, y});
			if(x > 20 || y > 20 || x < 1 || y < 1) ff = false;
		}
		if(k <= 5 && ff) {
			for(int x = 1; x <= 20; x++)
				for(int y = 1 ; y <= 20; y++) {
					set<pair<int, int>> cp = pt;
					int sum = 0;

					auto mit = cp.begin();
					int md = 100000;
					for(auto it = cp.begin(); it != cp.end(); it++) {
						int d = dist(*it, {x , y});
						if(md > d) mit = it, md = d;
					}
					sum += md;
					cp.erase(mit);	

					md = 100000;
					for(auto it = cp.begin(); it != cp.end(); it++) {
						int d = dist(*it, {x , y+k});
						if(md > d) mit = it, md = d;
					}
					sum += md;
					cp.erase(mit);	

					md = 100000;
					for(auto it = cp.begin(); it != cp.end(); it++) {
						int d = dist(*it, {x+k , y+k+k});
						if(md > d) mit = it, md = d;
					}
					sum += md;
					cp.erase(mit);	

					md = 100000;
					for(auto it = cp.begin(); it != cp.end(); it++) {
						int d = dist(*it, {x+k , y-k});
						if(md > d) mit = it, md = d;
					}
					sum += md;
					cp.erase(mit);	

					md = 100000;
					for(auto it = cp.begin(); it != cp.end(); it++) {
						int d = dist(*it, {x+k+k , y+k+k});
						if(md > d) mit = it, md = d;
					}
					sum += md;
					cp.erase(mit);	

					md = 100000;
					for(auto it = cp.begin(); it != cp.end(); it++) {
						int d = dist(*it, {x+k+k , y-k});
						if(md > d) mit = it, md = d;
					}
					sum += md;
					cp.erase(mit);	

					md = 100000;
					for(auto it = cp.begin(); it != cp.end(); it++) {
						int d = dist(*it, {x+k+k+k , y+k});
						if(md > d) mit = it, md = d;
					}
					sum += md;
					cp.erase(mit);	

					md = dist(*cp.begin(), {x+k+k+k , y});
					sum += md;
					
					ans = min(sum, ans);

				}
		}
		
		
		cout << "Case #" << tt << "\n";
		cout << ans << "\n";	
	}
    return 0;
}