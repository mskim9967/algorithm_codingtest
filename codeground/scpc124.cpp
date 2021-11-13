/*
    Prob
    SCPC 예선 2번 카드 게임
    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/

/*
You should use the standard input/output

in order to receive a score properly.

Do not use file input and output

Please be very careful. 
*/

#include <iostream>
#include <cstring>
#include <cstdio>
#include <algorithm>

using namespace std;

int Answer1, Answer2;

int cache[3005][3005], aps[3005][3005], bps[3005][3005], ar[3005], br[3005];
int a[3005], b[3005];
int k, n;

int main(int argc, char** argv)
{
	int T, test_case;

	freopen("input.txt", "r", stdin);

	cin >> T;
	for(test_case = 0; test_case  < T; test_case++)
	{

		Answer1 = 0, Answer2 = 0;
		/////////////////////////////////////////////////////////////////////////////////////////////
		//fill(&cache[0][0], &cache[3004][3005], 0);
		memset(cache, 0, sizeof(cache[0][0]) * 3005 * 3005);

		cin >> n >> k;
		for(int i = 1, sum = 0, ii = 1; i <= n; i++) {
			cin >> a[i];
			sum += a[i];
			while(sum > k)
				sum -= a[ii++];
			ar[i] = ii - 1;
		}
		for(int i = 1, sum = 0, ii = 1; i <= n; i++) {
			cin >> b[i];
			sum += b[i];
			while(sum > k)
				sum -= b[ii++];
			br[i] = ii - 1;
		}
		
		cache[0][0] = 1;
		aps[0][0] = bps[0][0] = 1;
		
		for(int j = 0; j <= n; j++)
			for(int i = 0; i <= n; i++) {
				if(cache[j][i] == 1) continue;
				
				if(i > 0 && aps[j][i - 1] - (ar[i] < 1 ? 0 : aps[j][ar[i] - 1]) != i - ar[i]) cache[j][i] = 1;
				if(j > 0 && bps[j - 1][i] - (br[j] < 1 ? 0 : bps[br[j] - 1][i]) != j - br[j]) cache[j][i] = 1;
				
				aps[j][i] = bps[j][i] = cache[j][i];
				if(i > 0) aps[j][i] = aps[j][i - 1] + cache[j][i];
				if(j > 0) bps[j][i] = bps[j - 1][i] + cache[j][i];
			}
		
		for(int i = 0; i <= n; i++) 
			for(int j = 0; j <= n; j++) {
				if(cache[i][j] == 1) Answer1++;
				if(cache[i][j] == 0) Answer2++;			
			}


			/////////////////////////////////////////////////////////////////////////////////////////////
		
		// Print the answer to standard output(screen).
		cout << "Case #" << test_case+1 << endl;
		cout << Answer1 << " " << Answer2 << endl;
	}

	return 0;//Your program should return 0 on normal termination.
}