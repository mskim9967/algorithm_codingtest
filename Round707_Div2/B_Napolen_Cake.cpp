#include <bits/stdc++.h> 
#include <fstream>
 
using namespace std;
 
int t, n;
int a[200010], ans[200010];
 
int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);
    
    cin >> t;
   
    while(t--) {
        cin >> n;
        for(int i = 1; i <= n; i++)
            cin >> a[i];
        
        int cnt = n;
        for(int i = n; i > 0; i--) {
            cnt = min(cnt, i - min(a[i], i));
            if(i > cnt) ans[i] = 1;
            else ans[i] = 0;
        }
        for(int i= 1; i <= n; i++)
            cout << ans[i] << " ";
        cout << "\n";
    }
    
    return 0;
}