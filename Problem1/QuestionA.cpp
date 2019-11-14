/*Problem 1, (a)
 * */
#include "windows.h"
#include <iostream>

using namespace std;


int main1(){
	int sum = 0;
	int n = 0;

	cout << "Please enter the value of n : " ;
	cin >> n;
	cin.clear();
	sum = n*(n+1)/2;

	cout << "The sum of consecutive integers from 1 to " << n << " is = " << sum << endl;
	system("PAUSE");
	return 0;

}
