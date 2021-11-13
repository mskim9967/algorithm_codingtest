/*
    Prob
    2018 SCPC 예선 1번 버스 타기
    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/

#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

int tc, ans;
int n, k;
int arr[200010];

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0); cout << fixed; cout.precision(7);
    
	cin >> tc;
	
	for(int tt = 1; tt <= tc; tt++) {
		ans = 0;
		
		cin >> n >> k;
		for(int i = 0; i < n; i++)
			cin >> arr[i];

		sort(arr, arr + n);
		
		for(int i = 0; i < n; i++) 
			if(arr[i] - arr[i - ans] <= k) ans++;
		
		cout << "Case #" << tt << "\n";
		cout << ans << "\n";
	}
    return 0;
}