#include <iostream>
#include <cmath>

using namespace std;


int factorail(int i){
	if(i <= 1)
		return i;
	else
		return i * factorail(i-1);
}


double cosine(float x){
	double c = 1;
	double err = 1;
	int n = 2;


	while((int)(err*100000) > 0){
		err = (pow(x,n)/factorail(n));
		c =	c + err;
		n++;
	}
	return c;

}


int main2(){

	float num[11] = {0,0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1.0};
	cout << "The Cosine Series for 0, 0.1, 0.2, 0.3, ... ,1 " << endl;

	for(int i = 0; i < 11; i++){
		cout << "cosine(" << num[i] <<") = " << cosine(num[i]) << endl;
	}

	return 0;
}


