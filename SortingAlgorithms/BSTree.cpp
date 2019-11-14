/*
 * RTree.cpp
 *
 *  Created on: Sep 3, 2016
 *      Author: Hatwib
 */
#include <iostream>
#include <cstdlib>
#include <stddef.h>

using namespace std;

typedef struct Node{
       int data;
       struct Node* Right;
       struct Node* Left;
       };

typedef Node *Tree;

Tree find(Tree *temp, int val){
	Tree t_tree = *temp;

	if(t_tree==NULL)
		return *temp;
	else if(t_tree->data==val)
		return *temp;
	else if(t_tree->data>val){
		if(t_tree->Left==NULL)
			return *temp;
		else
			return(find(&t_tree->Left,val));
	}else{
		if(t_tree->Right==NULL)
			return *temp;
		else
			return(find(&t_tree->Right,val));
	}
}

Tree InsertNode(Tree *Root, int val){
	Tree temp = *Root;
	Tree place, start;
	place = find(&temp,val);

	if(place == NULL){
		start = (Tree)malloc(sizeof(Node));
		start->data=val;
		start->Left=NULL;
		start->Right=NULL;
		*Root = start;
	}

	else if (place->data > val){
		place->Left=(Tree)malloc(sizeof(Node));
		place->Left->data=val;
		place->Left->Left=NULL;
		place->Left->Right=NULL;
	}

	else if(place->data<val){
		place->Right=(Tree)malloc(sizeof(Node));
		place->Right->data=val;
		place->Right->Left=NULL;
		place->Right->Right=NULL;
	}
	return temp;

}


Tree find_min_t(Tree *root){
	Tree temp = *root;
	if(temp == NULL)
		return NULL;
	else if(temp->Left==NULL)
		return(temp);
	else
		find_min_t(&temp->Left);

}


Tree Ndelete(Tree *root, int val){

	Tree temp=*root;

	if(temp == NULL)
		return temp;
	else if(temp->data > val){
		Ndelete(&temp->Left,val);
	}else if(temp->data<val){
		Ndelete(&temp->Right,val);
	}else{//when no child
		if(temp->Left == NULL && temp->Right==NULL)
		{
			*root = NULL;
		}
		//...when node has one child
		else if (temp->Left ==NULL)
		{
			*root = temp->Right;
		}
		else if(temp->Right == NULL)
		{
			*root = temp->Left;
		}
		//... when node or root has 2 children
		else
		{
			Tree temp2 = find_min_t(&temp->Left);
			temp->data=temp2->data;
			Ndelete(&temp2->Right,temp->data);

		}
	}

	return *root;

}


int find_min(Tree temp)
{
	if(temp==NULL)
		return -1; //No return found
	return 0;
}

int PrintNodes(Tree temp) // Ascending order using inprder O(n)
{
	if(temp==NULL)
		return 0;
	else{
		PrintNodes(temp->Left);
		cout<<temp->data<< ", "; ;
		PrintNodes(temp->Right);
	}
	return 0;
}


char menu();

int main(){

	Node root = NULL;
	Tree temp = NULL;
	cout << sizeof(root) << endl;
	cout << sizeof(Tree) << endl;
	return 0;
	int choice;
	int x;
	do{
		choice = menu();
		switch(choice)
		{
		case '1':
			cout<<endl;
			cout<<" Insert the number into the BST"<<endl;
			cout<<" -------------------"<<endl;
			cin >> x;

			InsertNode(&temp,x);
			cout << sizeof(temp->data) << endl;
			break;
		case '2':
			cout<<"Show_Tree value of BST"<<endl;
			cout<<" -------------------"<<endl;
			PrintNodes(temp);
			break;
		case '3':
			cout<<"Delete Tree value of BST"<<endl;
			cout<<" -------------------"<<endl;
			cin >> x;
			Ndelete(&temp,x);
			break;
		case '4':
			cout<<" End the tree of BST "<<endl;
			cout<<" -------------------"<<endl;
			break;
		}
	}while(choice != '4');



	return 0;

}

char menu(){
	char choice;
	cout<<endl<<endl;
	cout<<" Binary Search Tree Operations "<<endl;
	cout<<" ----------------------------- "<<endl;
	cout<<" 1. Insert the value of BST "<<endl;
	cout<<" 2. Show_tree if value into tree of BST"<<endl;
	cout<<" 3. Delete tree "<<endl;
	cout<<" 4. End Program "<<endl;
	cout<<" Enter your choice : ";
	cin>>choice;
	return choice;

}
