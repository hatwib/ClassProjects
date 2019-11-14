#include <windows.h>
#include <iostream>
#include <string>
#include <sstream>

using std::string;
using std::stringstream;

using namespace std;

int main()
{

	int pos_sum = 0, neg_sum = 0; //Declare pos_sum and pos_sum Variables
	string str = ""; //Declare string variable to hold all numbers in one line, separated by space

	cout << "Please enter a list of numbers (positive and negative), in one line, separated by space" << endl;
	getline(cin, str); // reads input stream and put the value into a string

	stringstream stream(str); // Extracts a sequence of characters from a string separated by space

	int n; //Declare a temporary integer value
	while(stream >> n){ //Get each characters from the string sequence
		if(n > 0)
			pos_sum = pos_sum + n; // Add positive numbers
		else
			neg_sum = neg_sum + n; // Add negative numbers
	}
	//Print Output
	cout << "\nThe sum of positive numbers is " << pos_sum << endl;
	cout << "The sum of negative numbers is " << neg_sum << endl;
	system("PAUSE");
	return 0;
}
