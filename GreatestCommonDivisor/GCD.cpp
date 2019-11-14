#include <iostream>

using namespace std;

int GCD(int n, int m){
	int gcd = m;
	if(n < m)
		gcd = n;

	while(gcd > 0){
		if(n%gcd == 0 && m%gcd == 0)  // Modulus 4 % 2 == 4/2 r=0
			return gcd;
		else
			gcd--;
	}
}

int main(){

	int num1, num2;

	cout << "Enter two numbers, separated by a space" << endl;

	cin >> num1 >> num2;

	cout << "Greatest Common Divisor is : " << GCD(num1, num2) << endl;
}



