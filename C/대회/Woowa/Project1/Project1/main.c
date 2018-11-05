#include <stdio.h>

struct Node{
	struct Node* pre;
	struct Node* next;
	int id;
	int data;
};

void InsertNode(struct Node **head, int data) {
	struct Node *new = malloc(sizeof(struct Node));
	static Node *last= *head;
	while(last->next!=nullptr){
		last=last->next;
	}
	new->data = data;
	new->pre = last;
	last->next = new;
}
struct Node* FindNode(int data, struct Node **head){
	struct Node *find= *head;
	for (;;){
		if(data==find->data){
			return find;
		}
		else{
			find=find->next;
		}
	}
}

void DeleteNode(int data,struct Node **head) {
	strcut Node *delete=FindNode(data,head);
	if(delete->pre==nullptr){
		*head=delete->next
		
	}
	else{
		delete->pre->next= delete->next;
		delete->next->pre=delete->pre;
	}
	free(delete);
}

void AlldeleteNode(strcut Node **head){
	struct Node *delete = *head;
	struct Node *tmp;
	while(delete->next != nullptr){
		delete= delete->next;
	}
	while(*head != delete){
		tmp=delete ->pre;
		free(delete);
		delete = tmp;
	}
}

int main() {
	struct Node **head = malloc(sizeof(struct Node*));
	struct Node *link = malloc(sizeof(struct Node));
	*head=link;
	link->data = 3;
	InsertNode(head, 5);
	InsertNode(head,7)

	printf("%d, %d\n", link->next->data,link->data);
	system("pause");
}