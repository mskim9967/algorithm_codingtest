/*
    Prob
    SCPC 1회 예선 
    회로판 위의 배터리
    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/

#include <bits/stdc++.h> 
#include <fstream>
using namespace std;

int tc, n;
struct Pt{
  int x, y;
  Pt(int _x, int _y): x(_x), y(_y){}
  
};
struct Line{
  Pt pt1, pt2;
  Line(Pt _pt1, Pt _pt2): pt1(_pt1), pt2(_pt2){}
};

void solve() {
  vector<Line> lines;
  int mmax = 0;

  cin >> n;
  for(int i = 0; i < n; i++) {
    int x1, y1, x2, y2;
    cin >> x1 >> y1 >> x2 >> y2;
    lines.push_back(Line(Pt(x1, y1), Pt(x2, y2)));
  }

  for(Line lineA : lines) {
    int chebyshevA = 0, chebyshevB = 0;
    for(Line lineB : lines) {
      chebyshevA = max(chebyshevA, min( 
        max(abs(lineA.pt1.x - lineB.pt1.x), abs(lineA.pt1.y - lineB.pt1.y)),
        max(abs(lineA.pt1.x - lineB.pt2.x), abs(lineA.pt1.y - lineB.pt2.y))
      ));
      chebyshevB = max(chebyshevB, min(
        max(abs(lineA.pt2.x - lineB.pt2.x), abs(lineA.pt2.y - lineB.pt2.y)),
        max(abs(lineA.pt2.x - lineB.pt1.x), abs(lineA.pt2.y - lineB.pt1.y))
      ));
    }
    mmax = max(mmax, min(chebyshevA, chebyshevB));
  }

  if(mmax % 2) cout << mmax / 2.0 << '\n';
  else cout << mmax / 2 << '\n';
}

int main(void) {
  freopen("input.txt", "r", stdin);
  ios_base::sync_with_stdio(false);
  cin.tie(NULL); cout.tie(0); cout << fixed; cout.precision(1);

  cin >> tc;
	for(int tt = 1; tt <= tc; tt++) {
    cout << "Case #" << tt << "\n";
    solve();
  }
  return 0;
} 
