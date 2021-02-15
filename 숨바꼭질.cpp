/*
    Prob
    https://www.acmicpc.net/problem/1697

    Writer
    MyungSeung Kim(mskim9967@gmail.com)

    NOT DP!
*/
#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

int N, K;
// search range can be bigger than 100000
bool visited[200001];
queue<pair<int, int>> qu;

int solve() {
    // bfs
    qu.push(make_pair(N, 0));
    while(!qu.empty()) {
        pair<int, int> pop = qu.front(); qu.pop();

        if(pop.first == K) return pop.second;
        if(pop.first < 0 || pop.first > 200000 || visited[pop.first]) continue;
        
        visited[pop.first] = true;
        qu.push(make_pair(pop.first + 1, pop.second + 1));
        qu.push(make_pair(pop.first - 1, pop.second + 1));
        qu.push(make_pair(pop.first * 2, pop.second + 1)); 
    }
    return -1;
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);
    cin >> N >> K;
    cout << solve() << "\n";
}