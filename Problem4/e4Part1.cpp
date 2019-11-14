/*
 * e4Part1.cpp
 *
 *  Created on: Jun 14, 2016
 *      Author: Mubarak
 */

#include "windows.h"
#include <iostream>

using namespace std;

int main(){

	int a, b, c, d, e; // Declare 5 integer variables to store input values.
	int h; // Declare an integer variable to store the result of the expression.
	bool h_value; // Declare the boolean value to be used to print the result of logical expression.


	//prompt user for input
	cout << "Please enter five integers (use same line and space)\n";

	cin >> a >> b >> c >> d >> e;


	h = a > b;
	if(h == 1){ // Test the logical expression a > b
		h_value = true;  // If h = 1 then set h_value = TRUE
	}else{
		h_value = false; // ELSE, set h_value = FALSE
	}
	// Print Booleans and results
	cout << "a > b = " << boolalpha << h_value << noboolalpha << ", h = " << h << "\n"; // Use boolalpha and noboolalpha to SET and UNSET textual representation of TRUE and FALSE


	h = a != b;
	if(h == 1){ // Test the logical expression a > b
		h_value = true;  // If h = 1 then set h_value = TRUE
	}else{
		h_value = false; // ELSE, set h_value = FALSE
	}
	// Print Booleans and results
	cout << "a != b = " << boolalpha << h_value << noboolalpha << ", h = " << h << "\n"; // Use boolalpha and noboolalpha to SET and UNSET textual representation of TRUE and FALSE


	h = d %b == c% b;
	if(h == 1){ // Test the logical expression a > b
		h_value = true;  // If h = 1 then set h_value = TRUE
	}else{
		h_value = false; // ELSE, set h_value = FALSE
	}
	// Print Booleans and results
	cout << "d %b == c% b = " << boolalpha << h_value << noboolalpha << ", h = " << h << "\n"; // Use boolalpha and noboolalpha to SET and UNSET textual representation of TRUE and FALSE


	h = a *c != d * b;
	if(h == 1){ // Test the logical expression a > b
		h_value = true;  // If h = 1 then set h_value = TRUE
	}else{
		h_value = false; // ELSE, set h_value = FALSE
	}
	// Print Booleans and results
	cout << "a *c != d * b = " << boolalpha << h_value << noboolalpha << ", h = " << h << "\n"; // Use boolalpha and noboolalpha to SET and UNSET textual representation of TRUE and FALSE


	h = d * b == c * e;
	if(h == 1){ // Test the logical expression a > b
		h_value = true;  // If h = 1 then set h_value = TRUE
	}else{
		h_value = false; // ELSE, set h_value = FALSE
	}
	// Print Booleans and results
	cout << "d * b == c * e = " << boolalpha << h_value << noboolalpha << ", h = " << h << "\n"; // Use boolalpha and noboolalpha to SET and UNSET textual representation of TRUE and FALSE


	h = !(c*b);
	if(h == 1){ // Test the logical expression a > b
		h_value = true;  // If h = 1 then set h_value = TRUE
	}else{
		h_value = false; // ELSE, set h_value = FALSE
	}
	// Print Booleans and results
	cout << "!(c*b) = " << boolalpha << h_value << noboolalpha << ", h = " << h << "\n"; // Use boolalpha and noboolalpha to SET and UNSET textual representation of TRUE and FALSE


	h = !(a % b * c);
	if(h == 1){ // Test the logical expression a > b
		h_value = true;  // If h = 1 then set h_value = TRUE
	}else{
		h_value = false; // ELSE, set h_value = FALSE
	}
	// Print Booleans and results
	cout << "!(a % b * c) = " << boolalpha << h_value << noboolalpha << ", h = " << h << "\n"; // Use boolalpha and noboolalpha to SET and UNSET textual representation of TRUE and FALSE


	h = b % c * a;
	if(h == 1){ // Test the logical expression a > b
		h_value = true;  // If h = 1 then set h_value = TRUE
	}else{
		h_value = false; // ELSE, set h_value = FALSE
	}
	// Print Booleans and results
	cout << "b % c * a = " << boolalpha << h_value << noboolalpha << ", h = " << h << "\n"; // Use boolalpha and noboolalpha to SET and UNSET textual representation of TRUE and FALSE

	system("PAUSE");
	return 0;

}





