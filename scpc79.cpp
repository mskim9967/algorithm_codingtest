/*
    Prob
    2018 SCPC 2차 예선 1번 Quick Sort
    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/

#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

int tc, n, ans;
int arr[200010], mmin[200010], mmax[200010];

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0); cout << fixed; cout.precision(7);
	
	cin >> tc;
	for(int tt = 1; tt <= tc; tt++) {
		ans = 0;
		cin >> n;
		for(int i = 1; i <= n; i++) {
			cin >> arr[i];
			mmax[i] = max(mmax[i - 1], arr[i]);
		}
		
		mmin[n + 1] = 10000001;
		for(int i = n; i > 0; i--) 
			mmin[i] = min(mmin[i + 1], arr[i]);
		
		for(int i = 1; i <= n; i++) 
			if(arr[i] > mmax[i - 1] && arr[i] < mmin[i + 1]) ans++;
		
		cout << "Case #" << tt << "\n";
		cout << ans << "\n";
	}
    return 0;
}