#include <iostream>
#include <string>
#include <sstream>

using std::string;
using std::stringstream;

using namespace std;

void ReverseSet(int *inputSet, int *outputSet, int len){
	for(int i = len-1; i >= 0; i--){
		outputSet[len-i-1] = inputSet[i];
	}

}


int main6()
{

	int tmpSet[10];
	string str = "";

	cout << "Please enter a set of numbers all in one line, separated by space" << endl;
	getline(cin, str);

	stringstream stream(str);

	int n, m=0;

	while(stream >> n){
		tmpSet[m++] = (int)n;
	}


	int mySet[m], revSet[m];


	cout << "\n\nYou entered Set: " << endl;
	cout << "[";
	for(int j = 0; j < m; j++){
			mySet[j] = tmpSet[j];
			cout << mySet[j];
			if(j < m-1)
				cout << ", ";
	}
	cout << "]";


	ReverseSet(mySet,revSet, m);
	cout << "\n\nThe Reverse Set is: " << endl;
	cout << "[";
	for(int j = 0; j < m; j++){
			cout << revSet[j];
			if(j < m-1)
				cout << ", ";
	}
	cout << "]";

	return 0;
}
