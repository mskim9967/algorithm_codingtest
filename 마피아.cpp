/*
    Prob
    https://www.acmicpc.net/problem/1079

    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/
#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

int N, me;
int wei[17], R[17][17];

int solve(int left) {
    if(left == 1) return 0; // mafia win
    if(left == 2) return 1; // mafia win

    if(left % 2 == 1) { // is day
        int die = 0;
        for(int i = 1; i < N; i++) // kill person
            if(wei[die] < wei[i]) die = i;
        if(die == me) return 0; // mafia lose
        wei[die] = INT_MIN;
        return solve(left - 1);
    }
    else { // is night
        int ret = 0;
        int cp[17];
        memcpy(cp, wei, sizeof(cp));
        // for each person
        for(int i = 0; i < N; i++) {
            memcpy(wei, cp, sizeof(cp));
            // pass if person is already dead or me
            if(i == me || wei[i] == INT_MIN) continue;
            wei[i] = INT_MIN;
            // update wei
            for(int j = 0; j < N; j++)
                if(wei[j] != INT_MIN) wei[j] += R[i][j];
            ret = max(ret, 1 + solve(left - 1));
       }
       return ret;
    }
    return -1;
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);
    cin >> N;
    for(int i = 0; i < N; i++) 
        cin >> wei[i];
    for(int i = 0; i < N; i++)
        for(int j = 0; j < N; j++)
            cin >> R[i][j];
    cin >> me;

    cout << solve(N) << "\n";
}