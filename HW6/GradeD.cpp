#include <windows.h>
#include <iostream>
using namespace std;

int main()
{
	//Declare grade Variables
	double grade = -1, n = 0;

	while(grade < 0 || grade > 100){
		//get input from grade
		cout << "Please enter a grade (0 - 100)" << endl;
		cin >> grade;
		n++;
		if(n == 5){
			cout << "\n5 Wrong attempts reached, terminating the Program"<< endl;
			system("PAUSE");
			return 0;
		}else if(!(grade >= 0 && grade <= 100))
			cout << "The Grade entered is invalid..." << endl;
	}
	//Print grade
	cout << "\nThe Grade entered is " << grade << endl;
	system("PAUSE");
	return 0;
}
