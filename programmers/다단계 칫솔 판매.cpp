/*
    Prob
    https://programmers.co.kr/learn/courses/30/lessons/77486

    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/

#include <bits/stdc++.h>
using namespace std;

void calc(map<string, string> &referrals, map<string, int> &revenue, int cost, string person) {
    int fee = cost * 0.1;
    revenue[person] += cost - fee;
    if(referrals[person] == "-" || fee == 0) return; // 수수료가 1원 미만이거나 추천인이 없으면 재귀 종료
    calc(referrals, revenue, fee, referrals[person]);
}

vector<int> solution(vector<string> enroll, vector<string> referral, vector<string> seller, vector<int> amount) {
    map<string, int> revenue;
    map<string, string> referrals;
    for(int i = 0; i < enroll.size(); i++) {
        referrals.insert({enroll[i], referral[i]});
        revenue.insert({enroll[i], 0});
    }

    for(int i = 0; i < seller.size(); i++) 
        calc(referrals, revenue, amount[i] * 100, seller[i]);

    vector<int> answer;
    for(string person : enroll) answer.push_back(revenue[person]);
    return answer;
}
