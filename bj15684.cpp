/*
    Prob
    https://www.acmicpc.net/problem/15684

    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/

#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

static int IMP = INT_MAX;
int N, M, H, ans = IMP;
bool lad[15][35];


bool isAns() {
	for(int start = 0; start < N; start++) {
		int fin = start, depth = 0;
		while(depth < H) {
			if(fin != N - 1 && lad[fin][depth]) 
				fin++;
			else if(fin != 0 && lad[fin - 1][depth])
				fin--;
			depth++;
		}
		if(fin != start) return false;
	}
	return true;
}

void dfs(int pos, int cnt) {
	if(isAns()) {
		ans = min(ans, cnt);
		return;
	}
	if(cnt == 3) return;
	
	int n = N - 1;
	for(int i = pos + 1; i < n * H; i++) {
		if(!lad[i % n][i / n] && (i % n == 0 || !lad[(i % n) - 1][i / n])) {
			lad[i % n][i / n] = true;
			dfs(i, cnt + 1);
			lad[i % n][i / n] = false;
		}
	}
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);

	cin >> N >> M >> H;
	for(int i = 0, a, b; i < M; i++) {
		cin >> a >> b;
		lad[b - 1][a - 1] = true;
	}
	dfs(-1, 0);
    cout << (ans == IMP ? -1 : ans) << "\n";
}