#include <windows.h>
#include <iostream>

using namespace std;

int main()
{
	//Declare Variables
	double divisor, dividend, quotient = 0, remainder = 0, r = 10;


	//get input from user
	cout << "Please enter dividend " << endl;
	cin >> dividend;
	cout << "Please enter divisor " << endl;
	cin >> divisor;

	//Loop
	while (r != 0)
	{
		//subtract
		r = dividend - divisor;
		// If Both Dividend and Divisor have same signs, i.e. both are Positive or both are Negative
		// check if it is (Positive with r >= 0) or (Negative wit r <= 0)
		if((dividend > 0 && divisor > 0 && r >= 0) || (dividend < 0 && divisor < 0 && r <= 0)) {
			quotient++;  //increment quotient
			remainder = r;
		}else{
			//Correct remainder if it is negative, and end loop
			remainder = dividend;
			break;
		}
		dividend = r;

	}
	//Print Outputs
	cout << " Quotient is " << quotient << "   Remainder " << remainder << endl;

	system("PAUSE");
	return 0;
}
