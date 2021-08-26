#include <string>
#include <vector>
#include <bits/stdc++.h>

using namespace std;

bool sCmp(pair<int, int> a, pair<int, int> b) { return a.first < b.first; }

int solution(vector<string> lines) {
    vector<pair<int, int>> times;
    
    for(string line : lines) {
        int sSec = 0, eSec = 0, hh, mm, ss, sss;
        float t;
        sscanf(line.c_str(), "%*s %d:%d:%d.%d %f", &hh, &mm, &ss, &sss, &t);
        eSec = ((hh * 60 + mm) * 60 + ss) * 1000 + sss;
        sSec = eSec - (int)round(t * 1000) + 1;
        times.push_back({sSec, eSec});
    }
    
    int ans = 1;
    
    for(int i = 0; i < times.size(); i++) {
        int cnt = 1;
        for(int j = i + 1; j < times.size(); j++)
            if(times[j].first <= times[i].second + 999) 
                cnt++;
        
        ans = max(ans, cnt);
    }

    return ans;
}