/*
    Prob
    2019 SCPC 2차 예선 1번 소수 수열
    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/

#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

int tc, ans;
int a, b;
bool pn[30000];
int cache[30000];

int dp(int a) {
	int &ret = cache[a];
	if(ret != -1) return ret;
	if(!pn[a]) return ret = 0;
	ret = 1;
	if(a / 10 == 0) return ret;
	
	for(int i = 1; i <= 10000 && i < a; i *= 10) 
		ret = max(ret, 1 + dp((a % i) + a / (i * 10) * i));
	
	return ret;
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0); cout << fixed; cout.precision(7);
    
	for(int i = 2; i < 30000; i++) pn[i] = true;
	memset(cache, -1, sizeof(cache));
	
	for(int i = 2; i < 30000; i++) {
		if(!pn[i]) continue;
		for(int j = 2; i * j < 30000; j++)
			pn[i * j] = false;
	}
	
	cin >> tc;
	for(int tt = 1; tt <= tc; tt++) {
		cin >> a >> b;

		cout << "Case #" << tt << "\n";
		cout << (dp(a) > dp(b) ? 1 : dp(a) == dp(b) ? 3 : 2) << "\n";
	}
    return 0;
}