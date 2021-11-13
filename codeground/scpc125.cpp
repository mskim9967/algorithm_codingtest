/*
    Prob
    SCPC 예선 3번 사다리 게임
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

int Answer;

int cache[2005][2005];
int k, n, m;

int main(int argc, char** argv)
{
	int T, test_case;

	freopen("input.txt", "r", stdin);

	cin >> T;
	for(test_case = 0; test_case  < T; test_case++)
	{

		Answer = 0;
		/////////////////////////////////////////////////////////////////////////////////////////////

		memset(cache, 0, sizeof(cache[0][0]) * 2005 * 2005);
		
		cin >> n >> k >> m;
		for(int i = 1; i <= n; i++) 
			for(int j = 1; j <= n; j++)
				cache[i][j] = (i == j ? 0 : 999999);
				
		for(int i = 0, a, b; i < k; i++) {
			cin >> a >> b;
			
			for(int line = 1; line <= n; line++) {
				int aa = min(1 + cache[line][a], cache[line][b]); // line->a, line->b->a
				int bb = min(cache[line][a], 1 + cache[line][b]); // line->b, line->a->b
				cache[line][a] = aa;
				cache[line][b] = bb;
			}
		}
		
		for(int i = 0, a, b; i < m; i++) {
			cin >> a >> b;
			Answer += (cache[a][b] >= 999999 ? -1 : cache[a][b]);
		}

			


			/////////////////////////////////////////////////////////////////////////////////////////////
		
		// Print the answer to standard output(screen).
		cout << "Case #" << test_case+1 << endl;
		cout << Answer << endl;
	}

	return 0;//Your program should return 0 on normal termination.
}