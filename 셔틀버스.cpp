/*
    Prob
    https://programmers.co.kr/learn/courses/30/lessons/17678
    
    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/

#include <bits/stdc++.h>

using namespace std;

string time2str(int time) {
    return (time / 60 < 10 ? "0" : "") + to_string(time / 60) + ":" + (time % 60 < 10 ? "0" : "") + to_string(time % 60);
}

string solution(int n, int t, int m, vector<string> timetable) {
    vector<int> tt;
    int hh, mm;
    
    for(string time : timetable) {
        sscanf(time.c_str(), "%d:%d", &hh, &mm);
        tt.push_back(hh * 60 + mm);
    }
    sort(tt.begin(), tt.end(), greater<>());
    
    for(int i = 0, time = 540; i < n; i++) {
        vector<int> bus;
        
        while(!tt.empty() && bus.size() < m) {
            if(tt.back() > time) break;
            bus.push_back(tt.back());
            tt.pop_back();    
        }
        
        if(i == n - 1) // 막차 
            if(bus.size() == m) return time2str(bus.back() - 1);
            else return time2str(time);
        
        time += t;
    }
    return "";
}
