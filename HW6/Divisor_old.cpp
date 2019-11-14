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

	while(dividend < 0 || divisor < 0){
		if(dividend < 0){
			cout << "Invalid Dividend: Please enter only POSITIVE dividend,\n Or enter -1 to END Program" << endl;
			cin >> dividend;
		}
		if(dividend == -1){
			cout << "Program will END now...." << endl;
			system("PAUSE");
			return 0;
		}else if(divisor < 0){
			cout << "Invalid Divisor: Please enter a POSITIVE  divisor" << endl;
			cin >> divisor;
		}

	}

	//r = divisor; //Avoid ZERO divisor

	//Loop
	while (r > 0)
	{

		//subtract
		r = dividend - divisor;

		remainder = r;
		// check if it is positive
		if (remainder >= 0)
		{
			quotient++;  //increment quotient
		}

		if(remainder > divisor)
			dividend = remainder;
		else
			break;
		//Correct remainder if it is negative
		if (remainder < 0)
		{
			remainder = remainder + divisor;
		}
		r = r + remainder;


	}
	//Print Outputs
	cout << " Quotient is " << quotient << "   Remainder " << remainder << endl;

	system("PAUSE");
	return 0;
}
