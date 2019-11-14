#include <iostream>

using namespace std;

void fibonacci(int x){
	int a=0, b=1, tmp = 0;
	int i = 0;
	while (x > 0){
		if(i == 0){
			a = 0;
			b = 0;
		}else if(i == 1){
			a = 0;
			b = 1;
		}else{
			tmp = b;
			b = a+b;
			a = tmp;
		}
		cout << b << ", ";
		x--;
		i++;
	}

}


int main4(){

	int n;

	cout << "Find the n-th Fibonacci value :" << endl;
	cin >> n;

	fibonacci(n);

	return 0;


}
