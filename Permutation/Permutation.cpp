#include <iostream>
#include <string.h>

using namespace std;


int factorial(int x){
	if(x <= 1)
		return x;
	else
		return x * factorial(x-1);
}

void SwapByReference(char &x, char &y){
	char z = x;
	x = y;
	y = z;
}


void Permut(char L[], int i, int n, string nxt){
	string tmp;

	for(int j = i; j < strlen(L); j++){
			SwapByReference(L[i],L[j]); // Swap A with A, A with B, and A with D
			tmp.clear();
			for(int k = 0; k < n; k++)
				tmp[k] << L[k];// Fixed Value
			if(nxt.compare(tmp)){
				cout << nxt << "-" << tmp <<  endl;
				nxt = tmp;
				cout << "Previous is "<< tmp << endl;
				cout << "Next is "<< nxt << endl;
			}
			Permut(L, i+1, n,nxt);
			SwapByReference(L[j],L[i]);
		}

//	for(int k = 0; k < n; k++)
//		tmp[k] = L[k];//Fixed Value
//	for(int k = 0; k < strlen(res); k++)
//			tmp[k] = L[k];//Fixed Value
//	cout << endl;
}
int main(){

	char elements[] = "";
	int r;

	cout << "Enter list of elements" << endl;
	cin >> elements;

	cout << "Enter number of elements in combination" << endl;
	cin >> r;

	Permut(elements,0,r,"");

	return 0;

}



