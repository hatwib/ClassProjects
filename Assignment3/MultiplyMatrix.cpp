#include <iostream>

using namespace std;


int main8(){

	int A[3][3] = {{1,1,2},{2,2,3},{3,3,4}};
	int B[2][3] = {{3,3,1},{4,4,2}};

	cout << "The Multiple of Matrix A x B is impossible because columns in A (3) IS NOT EQUAL to rows in B (2)"<< endl;
	cout << "Thus we change to B x A = C, columns in B (3) = rows in A (3)\n"<< endl;


	cout << "B = \n";

	int rows = 2;
	int columns = 3;

	for (int i=0; i<rows; i++){
		cout << "[";
			for (int j=0; j < columns; j++){
			cout << B[i][j];
			if(j < columns-1)
				cout << ", ";
			}
		cout << "]\n";
	}

	rows = 3;
	columns = 3;
	cout << "\n   X\n\nA = \n";

	for (int i=0; i < rows; i++){
		cout << "[";
			for (int j=0; j < columns; j++){
			cout << A[i][j];
			if(j < columns-1)
				cout << ", ";
			}
		cout << "]\n";
	}



	int C[2][3] = {{0,0,0},{0,0,0}};

	rows = 2; // 2
	columns = 3;

	for(int i = 0; i < rows; i++){
		for(int j = 0; j < columns; j++){
			for(int k = 0,l = 0; k < columns && l < columns; k++,l++){
				C[i][j] = C[i][j] + (B[i][k]*A[l][j]);
			}
			cout << "\t ";
		}cout << "\n ";
	}





	cout << "C = \n";

	for (int k=0; k<rows; k++){
		cout << "[";
			for (int l=0; l<columns; l++){
			cout << C[k][l];
			if(l < columns-1)
				cout << ", ";
			}
		cout << "]\n";
	}

	return 0;

}
