/*Problem 1, (c)
 * */
#include "windows.h"
#include <iostream>
using namespace std;

int main(){
	int sum = 0;
	int sum1 = 0;
	int sum2 = 0;

	int num1 = 0;
	int num2 = 0;

	cout << "Please enter the value of num1 : " ;
	cin >> num1;
	sum1 = num1*(num1+1)/2;
	cin.clear();

	cout << "Please enter the value of num2 : " ;
	cin >> num2;
	sum2 = num2*(num2+1)/2;

	sum = num1 + (sum2 - sum1);

	cout << "The sum of consecutive integers from " << num1 << " to " << num2 << " is = " << sum << endl;
	system("PAUSE");
	return 0;
}
