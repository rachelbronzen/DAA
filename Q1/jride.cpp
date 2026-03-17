#include <iostream>
using namespace std;

int main() {
    int b; //rute
    int r; //setiap rute, ada berapa halte ini
    int s; //brp kali stop di rute. 2<=s<=100000. Jadi nomor stops itu s-1
    int i; //begining bus stop
    int nice; //jill suka road ini
    cin >> b;
    for (r=0; r<b; r++) {
        cin >> s;
        int beststarthalte = 1;
        int beststophalte = 1;
        int currhalte = 1;
        int bestscore = -1;
        int currscore = 0;
        for (i=1; i<s; i++){
            cin >> nice;
            currscore += nice;
            if (currscore > bestscore) { // jika nilai total > rekor sebelum
                bestscore = currscore;
                beststarthalte = currhalte;
                beststophalte = i+1;
            }
            else if (currscore == bestscore) {
                if ((i+1 - currhalte)>(beststophalte - beststarthalte)) {
                    beststarthalte = currhalte;
                    beststophalte = i+1;
                }
            }
            if (currscore < 0) {
                currscore = 0;
                currhalte = i+1;
            }
        }
        if (bestscore > 0) {
            cout << "The nicest part of route " << r+1 << " is between stops " << beststarthalte << " and " << beststophalte << endl;
        }
        else {
            cout << "Route " << r+1 << " has no nice parts" << endl;
        }
    }
}