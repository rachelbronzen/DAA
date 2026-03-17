#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>
using namespace std;

int main() {
    int n; //n: banyak persegi , h: tinggi persegi
    while (cin >> n && n!= 0) {
        vector<long long> h(n);
        for (int a = 0; a<n; a++){
            cin >> h[a];
        }
        stack<int> s; //nomor urut
        long long maxarea = 0;
        int i = 0;
        while (i<n) {
            if (s.empty() || h[s.top()] <= h[i]) { //kosong ato tinggi skrng lrbh besardr batang terakhir
                s.push(i++);
            }
            else {
                int longest = s.top();
                s.pop();
                long long width;
                if (s.empty()) { //tumpukan sudah kosong
                    width = i;
                } else { //tumpukan masih ada isinya
                    width = i - s.top() - 1;
                }
                long long area = h[longest] * width;
                maxarea = max(maxarea, area);
            }
        }
        while (!s.empty()) {
            int longest = s.top();
            s.pop();
            long long width = s.empty() ? i : (i - s.top() - 1);
            long long area = h[longest] * width;
            maxarea = max(maxarea, area);
        }
        cout << maxarea << endl;
    }
}
