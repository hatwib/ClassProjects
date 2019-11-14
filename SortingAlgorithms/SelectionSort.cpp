#include <iostream>
using namespace std;

void Swap(int Ary[], int x, int y){
	int tmp = Ary[x];
	Ary[x] = Ary[y];
	Ary[y] = tmp;
}

int SelectionSort(){

	int A[] = {14,33,27,10,35,19,42,44};
	int N = 8;


	for(int i = 0; i < N-1; i++){
		int min = A[i];
		int min_index = i;
		for(int j = i; j < N; j++){
			if(A[j] < min){ 		// Find the smallest value less than A[i]
				min = A[j];			// Store the Minimum value
				min_index = j;		// Store index of the Minimum value
			}
		}

		if(i != min_index){ 		// Swap only if index of Minimum value has changed
			cout << "Swap " << A[i] << " with " << A[min_index] << ", " << endl;
			Swap(A,i,min_index);
		}
	}
	return 0;
}

