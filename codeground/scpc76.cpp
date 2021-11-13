/*
    Prob
    2018 SCPC 예선 3번 우주정거장
    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/

#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

int tc, ans;
int n, m;
vector<int> g[200001];
int cnt[200001];
queue<int> q;

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0); cout << fixed; cout.precision(7);
    
	cin >> tc;
	for(int tt = 1; tt <= tc; tt++) {
		for(vector<int> &v : g) v.clear();
		
		cin >> n >> m;
		
		ans = n;
		
		for(int i = 0; i < m; i++) {
			int n1, n2;
			cin >> n1 >> n2;
			g[n1].push_back(n2);
			g[n2].push_back(n1);
		}
		
		for(int i = 1; i <= n; i++)
			if(g[i].size() == 2) q.push(i);
		
		while(!q.empty()) {
			int pop = q.front(); q.pop();
			if(g[pop].size() != 2) continue;
			int a = g[pop][0], b = g[pop][1];
			
			if(find(g[a].begin(), g[a].end(), b) != g[a].end()) {
				g[a].erase(remove(g[a].begin(), g[a].end(), pop), g[a].end());
				g[b].erase(remove(g[b].begin(), g[b].end(), pop), g[b].end());
				if(g[a].size() == 2) q.push(a);
				if(g[b].size() == 2) q.push(b);
				g[pop].clear();
				ans--;
			}
		}
		
		cout << "Case #" << tt << "\n";
		cout << ans << "\n";
	}
    return 0;
}