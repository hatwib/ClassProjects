//  Tree.hpp

//  Second Homework

//

//  Created by Muneer on 8/3/16.

//  Copyright © 2016 CodeTrilla. All rights reserved.

//

#include <iostream>

#include <cstdlib>

#include <stddef.h>


using namespace std;


typedef struct node_type

{

	int data;

	struct node_type *left, *right;

} node;


// Declare a list of type nodes

typedef node *tree;


//find the operation on best

tree find(tree *temp, int val){
	tree t_tree = *temp;

	if(t_tree==NULL)
		return *temp;
	else if(t_tree->data==val)
		return *temp;
	else if(t_tree->data>val){
		if(t_tree->left==NULL)
			return *temp;
		else
			return(find(&t_tree->left,val));
	}else{
		if(t_tree->right==NULL)
			return *temp;
		else
			return(find(&t_tree->right,val));
	}
}

tree insert(tree *root, int val){
	tree temp = *root;
	tree place, start;
	place = find(&temp,val);

	if(place == NULL){
		start=(tree)malloc(sizeof(node));
		start->data=val;
		start->left=NULL;
		start->right=NULL;
		*root = start;
	}

	else if (place->data > val){
		place->left=(tree)malloc(sizeof(node));
		place->left->data=val;
		place->left->left=NULL;
		place->left->right=NULL;
	}

	else if(place->data<val){
		place->right=(tree)malloc(sizeof(node));
		place->right->data=val;
		place->right->left=NULL;
		place->right->right=NULL;
	}
	return temp;

}

tree find_min_t(tree *root){
	tree temp = *root;
	if(temp == NULL)
		return NULL;
	else if(temp->left==NULL)
		return(temp);
	else
		find_min_t(&temp->left);

}


tree Ndelete(tree *root, int val){

	tree temp=*root;

	if(temp == NULL)
		return temp;
	else if(temp->data > val){
		Ndelete(&temp->left,val);
	}else if(temp->data<val){
		Ndelete(&temp->right,val);
	}else{//when no child
		if(temp->left == NULL && temp->right==NULL)
		{
			*root = NULL;
		}
		//...when node has one child
		else if (temp->left ==NULL)
		{
			*root = temp->right;
		}
		else if(temp->right == NULL)
		{
			*root = temp->left;
		}
		//... when node or root has 2 children
		else
		{
			tree temp2 = find_min_t(&temp->left);
			temp->data=temp2->data;
			Ndelete(&temp2->right,temp->data);

		}
	}

	return *root;

}


int find_min(tree temp)
{
	if(temp==NULL)
		return -1; //No return found
	return 0;
}

int show_tree(tree temp) // Ascending order using inprder O(n)
{
	if(temp==NULL)
		return 0;
	else{
		show_tree(temp->left);
		cout<<temp->data<< ", "; ;
		show_tree(temp->right);
	}
	return 0;
}

char menu();

int main2()

{

	tree temp = NULL;
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
			insert(&temp,x);
			break;
		case '2':
			cout<<"Show_Tree value of BST"<<endl;
			cout<<" -------------------"<<endl;
			show_tree(temp);
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

//char menu(){
//	char choice;
//	cout<<endl<<endl;
//	cout<<" Binary Search Tree Operations "<<endl;
//	cout<<" ----------------------------- "<<endl;
//	cout<<" 1. Insert the value of BST "<<endl;
//	cout<<" 2. Show_tree if value into tree of BST"<<endl;
//	cout<<" 3. Delete tree "<<endl;
//	cout<<" 4. End Program "<<endl;
//	cout<<" Enter your choice : ";
//	cin>>choice;
//	return choice;
//
//}
