#include <iostream>
#include <cmath>

using namespace std;

double  Dist(int x[], int y[]){
	double d = sqrt(pow(y[0]-x[0],2)+pow(y[1]-x[1],2));
	return d;
}


int main5(){

	int A[2], B[2];

	cout << "Enter two points, values of each point in one line separated by space \n" << endl;

	cout << "Enter Point A:" << endl;
	cin >> A[0] >> A[1];
	cout << "Enter Point B:" << endl;
	cin >> B[0] >> B[1];
	cout << "\nThe Euclidian Distance between A("<<A[0] << "," << A[1] << ") and B("<<B[0] << "," << B[1] << ") is " << Dist(A, B);

	return 0;


}
