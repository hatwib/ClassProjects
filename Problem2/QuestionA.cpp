/*
 *Problem 2 (a) Design, write, compile and run a C++ program to solve the set of linear equations:
 *
 * a11*X1 + a12*X2 = c1
 * a21*X1 + a22*X2 = c2
 *
 */

#include "windows.h"
#include <iostream>
using namespace std;

int main(){
	double X1 = 0, X2 = 0, a11 = 0, a12 = 0, a21 = 0, a22 = 0, c1 = 0, c2 = 0;

	cout << "Please enter values of the Linear Equations  a11*X1 + a12*X2 = c1 and a21*X1 + a22*X2 = c2:" << endl;

	cout << "a11 : " ;
	cin >> a11;
	cin.clear();

	cout << "a12 : " ;
	cin >> a12;
	cin.clear();

	cout << "c1 : " ;
	cin >> c1;
	cin.clear();

	cout << "\na21 : " ;
	cin >> a21;
	cin.clear();

	cout << "a22 : " ;
	cin >> a22;
	cin.clear();

	cout << "c2 : " ;
	cin >> c2;
	cin.clear();

	X1 = (c1*a22-c2*a12)/(a11*a22-a12*a21);

	X2 = (c2*a11-c1*a21)/(a11*a22-a12*a21);

	cout << "\nThe Linear Equations are:" << endl;
	cout << a11 << "*X1 + " << a12 << "*X2 = " << c1 << endl;
	cout << a21 << "*X1 + " << a22 << "*X2 = " << c2 << endl;
	cout << "\nAnswer: X1 = " << X1 << ";  X2 = " << X2 << endl;
	system("PAUSE");
	return 0;
}


