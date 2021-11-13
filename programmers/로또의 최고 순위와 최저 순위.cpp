#include <bits/stdc++.h>

using namespace std;

map<int, int> pt {{6, 1}, {5, 2}, {4, 3}, {3, 4}, {2, 5}, {1, 6}, {0, 6}};

vector<int> solution(vector<int> lottos, vector<int> win_nums) {
    int undef = 0, minn = 0;
    for(int lotto : lottos)
        lotto == 0 ? undef++ : find(win_nums.begin(), win_nums.end(), lotto) != win_nums.end() ? minn++ : 0;
    
    return vector<int>{pt[minn + undef], pt[minn]};
}
