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
int n, m, k;
int g[510][510], gg[510][510];

void update(int a, int b) {
	if(g[a][b] == 1) return;
	g[a][b] = 1;
	for(int j = 1; j <= n; j++)
		if(g[j][a] == 1) update(j, b);
	for(int j = 1; j <= n; j++)
		if(g[b][j] == 1) update(a, j);
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0); cout << fixed; cout.precision(7);
	
	cin >> tc;
	for(int tt = 1; tt <= tc; tt++) {
		memset(g, 0, sizeof(g));
		
		cin >> n >> m >> k;
		for(int i = 0, a, b; i < m; i++) {
			cin >> a >> b;
			update(a, b);
		}
		
		cout << "Case #" << tt << "\n";
		for(int i = 0, a, b; i < k; i++) {
			cin >> a >> b;
	
			if(g[b][a] == 0) {
				cout << 0;
				update(a, b);
			} 
			else {
				cout << 1;
				update(b, a);
			}	
		}
		cout << "\n";
	}
    return 0;
}