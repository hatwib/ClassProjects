#include <iostream>
#include <cstdlib>
#include <stddef.h>

using namespace std;

typedef struct node_type {
	char name[10];
	int id;
	float height;
	float weight;
	struct node_type *next;
} node;

// Declare a list of type nodes
typedef node *stack;

char menu();
stack push(stack *head, int n);
stack pop(stack head);
void showStack(stack current);

//#ifndef NULL
//#ifdef __cplusplus
//#define NULL    0
//#else
//#define NULL    ((void *)0)
//#endif
//#endif

int main()
{
	stack head = NULL;
	stack temp; int i = 1;
	char choice;

	do {
		// Display the main menu
		choice = menu();
		// Read user choice
		switch (choice)
		{
		case '1':
			// Register a new baby
			push(&head, i);
			cout << endl;
			i++;
			break;
		case '2':
			// Remove a baby from the stack
			head = pop(head);
			cout << endl;
			if(i > 1)
				i--;
			break;
		case '3':
			// Display the stack of babies
			showStack(head);
			cout << endl;
			break;
		default:
			// End execution
			cout << " Goodbye! \n \n";
			break;
		}
		// Increment counter for the next baby ID number

	} while (choice != '4');

	return 0;
}

char menu()
{
	char choice;
	cout << "============MAIN MENU============\n";
	cout << " 1. PUSH ONTO STACK \n";
	cout << " 2. POP FROM STACK \n";
	cout << " 3. Display STACK \n";
	cout << " 4. Exit \n";
	cout << "=================================\n";
	cout << " Enter choice (1/2/3/4): \n";
	cin >> choice;
	cout << endl;
	return choice;
}

// Insert a node onto the stack
stack push(stack *head, int n)
{
	stack temp;
	temp = (stack)malloc(sizeof(node));
	// create temp pointer to a new baby
	temp->id = n;
	cout << endl << " Enter baby name :" << endl;
	cin >> temp->name;
	cout << " Enter baby height :" << endl;
	cin >> temp->height;
	cout << " Enter baby weight :" << endl;
	cin >> temp->weight;
	cout << '\t' << "Baby " << temp->name << " ID number: " << temp->id << endl << endl;
	temp->next = *head;
	*head = temp;
}

// Remove a node from the stack
stack pop(stack head)
{
	stack temp = NULL;
	if (head == NULL)
		cout << " The stack is already empty... \n";
	else
	{
		temp = head;
		head = head->next;
		delete temp;
	}
	return head;
}

// Display all the nodes
void showStack(stack current)
{
	cout << "\n Your stack contains \n";
	if (current == NULL)
		cout << "\t ...nothing! ";
	while (current != NULL)
	{
		cout << endl << " Name: " << current->name << "\n ID no.: " << current->id <<
			"\n Height: " << current->height << "\n Weight: " << current->weight << endl << endl;
		current = current->next;
	} // A stack of babies.
	cout << endl;
}
