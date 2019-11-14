#include <iostream>
#include <cstring>
#include <cmath>


using namespace std;

int maxFrequency(int x){
	int digit;
	int n = 0;
	int len = 1;
	int tmp_x = x;

	// Get Length
	while(tmp_x >= 10){
		len ++;
		tmp_x = (int)tmp_x/10;
	}

	int tmp[len];
	int freq[len];

	// initialize frequency array and tmp array
	for(int i = len-1; i >=0; i--){
		tmp[i] = x - (x/10*10);
		freq[i] = 0;
		x = (int)x/10;
	}

	// Count the frequency
	for(int i = 0; i < len; i++){
		for(int j = 0; j < len; j++){
			if(tmp[i] == tmp[j])
				freq[i]++;
		}
	}


	//Search for most Frequent digit, starting with list significant digits
	for(int j = len-1; j >= 0; j--){
		if(freq[j] > n){
			n = freq[j];
			digit = tmp[j];
		}
	}

	return digit;
}

int main1(){

	int num;
	cout << "Enter value: " << endl;
	cin >> num;

	cout << "The Most Frequent Digit is " << maxFrequency(num);

	return 0;
}


