#include <iostream>

using namespace std;

class Rectangle{
	float length;
	float width;

public:
	Rectangle() {
		length = 1.0;
		width = 1.0;
	}
	void SetLength(float l){
		try{
			if (l <= 0 )
				throw "The length should be greater than 0!";
			else if (l >= 20.0)
				throw "The length should be less than 20!";
			length = l;
		}catch (const char* msg) {
			cerr << msg << endl;
		}
	}

	float GetLength(){
		return length;
	}

	void SetWidth(float w){
		try{
			if (w <= 0 )
				throw "The length should be greater than 0!";
			else if (w >= 20.0)
				throw "The length should be less than 20!";
			width = w;
		}catch (const char* msg) {
			cerr << msg << endl;
		}
	}

	float GetWidth(){
		return width;
	}

	float Perimeter(){
		return 2 * (length + width);
	}
	float Area(){
		return length * width;
	}

	void Draw(){
		for(int y = 25; y >= 0; y--){
			for(int x = 0; x < 25; x++){
				if((x == 0 && y <= width) || (y == 0 && x <= length) || (y == width && x <= length) || (x == length && y <= width))
						cout << "*"; // Draw Rectangle
				else if(x == 0)
						cout << "|"; // Draw Y - axis
				else if(y == 0)
						cout << "-"; // Draw X - axis
				else
					cout << " ";
				}
			cout << endl;
		}
	}

};

int main (){

	cout << "Object 1 : (8 x 11)" << endl;
	Rectangle rect1;
	rect1.SetLength(8);
	rect1.SetWidth(11);
	cout << "Length : " << rect1.GetLength() << endl;
	cout << "Width : " << rect1.GetWidth() << endl;
	cout << "Perimeter :" << rect1.Perimeter() << endl;
	cout << "Area :" << rect1.Perimeter() << endl;
	cout << "\nDraw Object 1\n" << endl;
	rect1.Draw();


	cout << "\n\nObject 2 : (8 x 5)" << endl;
	Rectangle rect2;
	rect2.SetLength(18);
	rect2.SetWidth(5);
	cout << "Length : " << rect2.GetLength() << endl;
	cout << "Width : " << rect2.GetWidth() << endl;
	cout << "Perimeter :" << rect2.Perimeter() << endl;
	cout << "Area :" << rect2.Perimeter() << endl;
	cout << "\nDraw Object 2\n" << endl;
	rect2.Draw();

	cout << "\n\nObject 3 : (19 x 19)" << endl;
	Rectangle rect3;
	rect3.SetLength(19);
	rect3.SetWidth(19);
	cout << "Length : " << rect3.GetLength() << endl;
	cout << "Width : " << rect3.GetWidth() << endl;
	cout << "Perimeter :" << rect3.Perimeter() << endl;
	cout << "Area :" << rect3.Perimeter() << endl;
	cout << "\nDraw Object 3\n" << endl;
	rect3.Draw();



	cout << "\n\nObject 4 : (15 x 10)" << endl;
	Rectangle rect4;
	rect4.SetLength(5);
	rect4.SetWidth(10);
	cout << "Length : " << rect4.GetLength() << endl;
	cout << "Width : " << rect4.GetWidth() << endl;
	cout << "Perimeter :" << rect4.Perimeter() << endl;
	cout << "Area :" << rect4.Perimeter() << endl;
	cout << "\nDraw Object 4\n" << endl;
	rect4.Draw();


	cout << "\nSet Length greater than 20" << endl;
	rect4.SetLength(25);
	cout << "Length : " << rect4.GetLength() << endl;


	cout << "\nSet Width greater than 20" << endl;
	rect4.SetWidth(70);
	cout << "Width : " << rect4.GetWidth() << endl;


	cout << "\nSet Length less than 0" << endl;
	rect4.SetLength(-5);
	cout << "Length : " << rect4.GetLength() << endl;

	cout << "\nSet Width less than 0" << endl;
	rect4.SetWidth(0);
	cout << "Width : " << rect4.GetWidth() << endl;


	return 0;


}

