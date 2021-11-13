#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

int n, k;
int cache[100010];
vector<int> coins;

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);
    
    cin >> n >> k;
    
    for(int input = 0, i = 0; i < n; i++) {
        cin >> input;
        // do not push overlapped coin
        if(cache[input] == 0) coins.push_back(input);
        cache[input] = 1;
    }

    for(int i = 1; i <= k; i++)
        for(int coin : coins) {
            if(i - coin < 0 || cache[i - coin] == 0) continue;
            
            if(cache[i] == 0) cache[i] = cache[i - coin] + 1;
            else cache[i] = min(cache[i - coin] + 1, cache[i]);
        }

    cout << (cache[k] ? cache[k] : -1) << "\n";
   
    return 0;
}