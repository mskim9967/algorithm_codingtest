/*
    Prob
    2021 SCPC 2차 예선 2번 패턴 매칭(부분 점수)
    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/

#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

int tc, n, k;
//string p;
int si[2000010], pi[510], alp[30];
char s[2000010];

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    //cin.tie(NULL); cout.tie(0); cout << fixed; cout.precision(7);
	setbuf(stdout, NULL);
	
	scanf("%d", &tc);
	//cin >> tc;
	for(int tt = 1; tt <= tc; tt++) {
		long long ans = 0;
		memset(si, -1, sizeof(si));
		memset(alp, -1, sizeof(alp));
		scanf("%d %d", &n, &k);
		//cin >> n >> k;
		scanf("%s", s);

		for(int i = 0; i < n; i++) {
			if(alp[s[i] - 'a'] != i) si[i] = alp[s[i] - 'a'];
			alp[s[i] - 'a'] = i;
		}
		
		for(int kk = 1; kk <= k; kk++) {
			memset(pi, -1, sizeof(pi));
			memset(alp, -1, sizeof(alp));
			long long pnum = 0;
			char pp[510];
			scanf("%s", pp);
			string p = pp;
			//cin >> p;
			for(int i = 0; i < p.size(); i++) {
				if(alp[p[i] - 'a'] != i) pi[i] = alp[p[i] - 'a'];
				alp[p[i] - 'a'] = i;
			}
			
			int pii = 0, sii = 0, tr = 0;
			do {
				int ss = max(-1, si[sii] - tr);
				int pp = pi[pii];
				
				//cout << "sii: "<< sii << " " << ss << " "<< "pii: "<< pii << " " << pp<< "\n";
				if(ss == pp) {
					sii++;
					pii++;
					if(pii == p.size()) {
						//cout << "ans!!" << "\n";
						pnum++;
						pii = 0;
						tr++;
						sii = tr;
					}
				}
				else {
					pii = 0;
					tr++;
					sii = tr;
				}
			} while(sii != n && tr + p.size() <= n);
			ans += pnum * kk;
		}
		
		cout << "Case #" << tt << "\n";
		cout << ans << "\n";	
	}
    return -1;
}