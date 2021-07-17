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
int n, t;
string b;
int a[50010];

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0); cout << fixed; cout.precision(7);
	
	cin >> tc;
	for(int tt = 1; tt <= tc; tt++) {
		memset(a, 0, sizeof(a));
		
		cin >> n >> t >> b;
		for(int i = 1; i <= n; i++) {
			if(b[i - 1] == '0') continue;
			
			if(i <= n - t && i > t) {
				if(a[i + t] == 0 && a[i - t] == 0) {
					if(b[i - 1 + t + t] == '0') a[i - t] = 1;
					else  a[i + t] = 1;
				}
			}
			else if(i <= n - t)
				a[i + t] = 1;
			else if(i > t)
				a[i - t] = 1;
		}
		
		cout << "Case #" << tt << "\n";
		for(int i = 1; i <= n; i++)
			cout << a[i];
		cout << "\n";
	}
    return 0;
}