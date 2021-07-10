/*
    Prob
    2019 SCPC 예선 1번 오르락 내리락
    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/


#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

int n1, n2, tc, ans;
int cache[1000010], ps[1000010];

int dp(int i) {
	int &ret = cache[i];
	if(ret) return ret;
	
	if(i == 1) return 0;
	if(i % 2 == 1) return ret = 1 + dp(i + 1);
	return ret = 1 + dp(i / 2);
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);
    
	cin >> tc;
	
	for(int i = 1; i <= 1000000; i++)
		ps[i] = ps[i - 1] + dp(i);	
	
	for(int tt = 1; tt <= tc; tt++) {
		cin >> n1 >> n2;
		
		cout << "Case #" << tt << "\n";
		cout << ps[n2] - ps[n1 - 1] << "\n";
	}

    return 0;
}