/*
    Prob
    2020 SCPC 2차 예선 2번 고구마
    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/

#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

int tc;
int n;
long long m;

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0); cout << fixed; cout.precision(7);
	
	cin >> tc;
	for(int tt = 1; tt <= tc; tt++) {
		long long sum = 0, ans = 0, price;
		set<long long> s;
		s.insert(0);
		
		cin >> n >> m;
		for(int i = 0; i < n; i++) {
			cin >> price;
			sum += price;
			s.insert(sum);
			
			auto itj = s.lower_bound(sum - m);
			if(itj != s.end()) ans = max(ans, sum - *itj);
		}
		
		cout << "Case #" << tt << "\n";
		cout << ans << "\n";
	}
    return 0;
}