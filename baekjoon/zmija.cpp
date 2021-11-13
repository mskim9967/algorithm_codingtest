/*
    Prob
    https://www.acmicpc.net/problem/3190

    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/
#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

int N, K, L;
vector<pair<int, int>> apple;
vector<pair<int, char>> turn;
const pair<int, int> V[4] = {make_pair(1, 0), make_pair(0, 1), 
    make_pair(-1, 0), make_pair(0, -1)};

bool isInBoundary(pair<int, int> pos) {
    if(pos.first >= 0 && pos.first < N &&
        pos.second >= 0 && pos.second < N) return true;
    return false;
}

int solve() {
    sort(turn.begin(), turn.end());
    vector<pair<int, char>>::iterator iter = turn.begin();

    int map[N][N];
    memset(map, 0, sizeof(map));

    for(pair<int, int> aPos : apple)
        map[aPos.first][aPos.second] = 1;
    
    int dir = 0;
    pair<int, int> vec = V[dir];
    pair<int, int> pos = make_pair(0, 0);
    queue<pair<int, int>> queue;
    int time = 0;
    
    map[pos.first][pos.second] = 2;
    queue.push(pos);
    
    while(true){
        // for(int i = 0; i < N; i++){
        //     for(int j = 0; j < N; j++)
        //         cout << map[j][i];
        //     cout << "\n";
        // }
        // cout << "\n";
        time++;
        pos.first = pos.first + vec.first;
        pos.second = pos.second + vec.second;

        if(!isInBoundary(pos) || map[pos.first][pos.second] == 2) break;
    
        if(map[pos.first][pos.second] != 1) {
            pair<int, int> pop = queue.front();
            queue.pop();
            map[pop.first][pop.second] = 0;
        }
             
        map[pos.first][pos.second] = 2;
        queue.push(pos);

        if(time == iter->first) {
            if(iter->second == 'L') dir = ((dir - 1) + 4) % 4;
            else dir = (dir + 1) % 4;
            vec = V[dir];
            if(iter != turn.end()) iter++;
        }

    }
    return time;
}



int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);

    cin >> N >> K;
    for(int i = 0; i < K; i++) {
        int x, y;
        cin >> x >> y;
        apple.push_back(make_pair(y-1, x-1));
    }
    cin >> L;
    for(int i = 0; i < L; i++) {
        int time; char dir;
        cin >> time >> dir;
        turn.push_back(make_pair(time, dir));
    }

    cout << solve() << "\n";
}