#include <windows.h>
#include <iostream>
using namespace std;

int main()
{
	//Declare grade Variables
	double grade = -1;

	while(grade < 0 || grade > 100){
		//get input from grade
		cout << "Please enter a grade (0 - 100)" << endl;
		cin >> grade;
	}
	//Print grade
	cout << "\nThe Grade entered is " << grade << endl;
	system("PAUSE");
	return 0;
}
