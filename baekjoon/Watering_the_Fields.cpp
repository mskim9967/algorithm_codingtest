/*
    Prob
    https://www.acmicpc.net/problem/10021
    Writer
    MyungSeung Kim(mskim9967@gmail.com)

    MST with Union-Find
*/
#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

struct Vert {
    int x, y;
    Vert(int _x, int _y) : x(_x), y(_y) {};
}; 
struct Edge {
    int v1, v2, wei;
    Edge(int _wei, int _v1, int _v2) : wei(_wei), v1(_v1), v2(_v2) {};
};

const int NMAX = 2010;
int n, c, ans = 0, edgeCnt = 0;
vector<Vert> verts;
vector<Edge> edges;
int dset[NMAX];

int find(int v) {
    if(v == dset[v]) return v;
    return dset[v] = find(dset[v]);
}
void uni(int v1, int v2) {
    dset[v1] = v2;
}

bool cmp(Edge &e1, Edge &e2) {
    return e1.wei < e2.wei;
};

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);
    
    cin >> n >> c;
    for(int i = 0; i < n; i++) {
        int x, y;
        cin >> x >> y;
        verts.push_back(Vert(x, y));
        for(int j = 0; j < i; j++) 
            if((verts[i].x - verts[j].x) * (verts[i].x - verts[j].x) + (verts[i].y - verts[j].y) * (verts[i].y - verts[j].y) >= c)
                edges.push_back(Edge((verts[i].x - verts[j].x) * (verts[i].x - verts[j].x) + (verts[i].y - verts[j].y) * (verts[i].y - verts[j].y), i, j));
    }
    for(int i = 0; i < NMAX; i++) dset[i] = i;

    sort(edges.begin(), edges.end(), cmp);

    for(Edge edge : edges) {
        if(find(edge.v1) == find(edge.v2)) continue;
        uni(find(edge.v1), find(edge.v2));
        ans += edge.wei;
        if(++edgeCnt == n - 1) break;
    }

    if(edgeCnt == n - 1) cout << ans << "\n";
    else cout << -1 << "\n";
    
    return 0;
}