#include <iostream>

using namespace std;

int fact1(int x){
	if(x <= 1)
		return x;
	else
		return x * fact1(x-1);
}

int fact2(int x){
	int n = 1;
	for(int i = x; i > 0; i--)
		n = n * i;
	return n;
}

int main(){

	int f;

	cout << "Find the Factorial By Recursion :" << endl;
	cin >> f;

	cout << "Factorial By Recursion is :" << fact1(f) << endl;


	cout << "Find the Factorial By For Loop :" << endl;
	cin >> f;

	cout << "Factorial By For Loop is :" << fact2(f) << endl;


}
