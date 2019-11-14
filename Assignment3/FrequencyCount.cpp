#include <iostream>
#include <cstring>
#include <cmath>


using namespace std;

int maxFrequency1(char x[]){
	int digit;
	int n = 0;
	int len = strlen(x);
	int tmp[len];
	int freq[len];

// initialize frequency array and tmp array
	for(int i = 0; i < len; i++){
		tmp[i] = x[i] - '0';
		freq[i] = 0;
	}


	// Count the frequency
	for(int i = 0; i < len; i++){
		for(int j = 0; j < len; j++){
			if(tmp[i] == tmp[j])
				freq[i]++;
		}
	}


	//Search for most Frequent digit
	for(int j = len-1; j >= 0; j--){
		if(freq[j] > n){
			n = freq[j];
			digit = tmp[j];
		}
	}

	return digit;
}

int main22(){

	char num[100];
	cout << "Enter value: " << endl;
	cin.get(num,100);

	cout << "The Most Frequent Digit is " << maxFrequency1(num);

	return 0;
}


