#include <iostream>
#include <cmath>

using namespace std;

int maxSquare(int n){
	int s = (int)sqrt(n) * (int)sqrt(n);
	return s;
}

int main(){
	int x;
	cout << "Enter a number greater than 0: " << endl;
	cin >> x;

	cout << "Perfect Square its " << maxSquare(x);

	return 0;
}

