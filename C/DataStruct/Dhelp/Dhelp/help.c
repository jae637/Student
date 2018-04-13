#define _CRT_SECURE_NO_WARRNING
//
//	���� ������ ���α׷�
//

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

#define MAX_CHAR_PER_LINE	1000
#define MAX_CHAR_PER_LINE 1000
#define MAX_NAME	256
void warning(char *);
void error(char *);

#define FALSE 0
#define TRUE 1

typedef struct {
	char a[MAX_CHAR_PER_LINE];
} element;
typedef struct ListNode {
	element data;
	struct ListNode *link;
} ListNode;
typedef struct {
	ListNode *head;     // ��� ������
	int length;		// ����� ����
} ListType;

void add_last(ListType *list, element data);

// phead: ����Ʈ�� ��� �������� ������
// p : ���� ���
// new_node : ���Ե� ��� 
void insert_node(ListNode **phead, ListNode *p,
	ListNode *new_node)
{
	if (*phead == NULL) {	// ���鸮��Ʈ�� ���
		new_node->link = NULL;
		*phead = new_node;
	}
	else if (p == NULL) { // p�� NULL�̸� ù��° ���� ����
		new_node->link = *phead;
		*phead = new_node;
	}
	else {			 // p ������ ����
		new_node->link = p->link;
		p->link = new_node;
	}
}
// phead : ��� �����Ϳ� ���� ������ 
// p: ������ ����� ���� ���
// removed: ������ ��� 
void remove_node(ListNode **phead, ListNode *p, ListNode *removed)
{
	if (p == NULL)
		*phead = (*phead)->link;
	else
		p->link = removed->link;
	free(removed);
}
// ����Ʈ�� �ʱ�ȭ�Ѵ�.
void init(ListType *list)
{
	if (list == NULL) return;
	list->length = 0;
	list->head = NULL;
}
// ����Ʈ�ȿ��� pos ��ġ�� ��带 ��ȯ�Ѵ�.
ListNode *get_node_at(ListType *list, int pos)
{
	int i;
	ListNode *tmp_node = list->head;
	if (pos < 0) return NULL;
	for (i = 0; i<pos; i++)
		tmp_node = tmp_node->link;
	return tmp_node;
}
// ����Ʈ�� �׸��� ������ ��ȯ�Ѵ�.
int get_length(ListType *list)
{
	return list->length;
}

// �־��� ��ġ�� �����͸� �����Ѵ�.
void add(ListType *list, int position, element data)
{
	ListNode *p;
	if ((position >= 0) && (position <= list->length)) {
		ListNode*node = (ListNode *)malloc(sizeof(ListNode));
		if (node == NULL) error("�޸� �Ҵ翡��");
		node->data = data;
		p = get_node_at(list, position - 1);
		insert_node(&(list->head), p, node);
		list->length++;
	}
	else {
		add_last(list, data);
	}
}
// ����Ʈ�� ���� �����͸� �����Ѵ�.
void add_last(ListType *list, element data)
{
	add(list, get_length(list), data);
}
// ����Ʈ�� ���� �����͸� �����Ѵ�.
void add_first(ListType *list, element data)
{
	add(list, 0, data);
}
//
int is_empty(ListType *list)
{
	if (list->head == NULL) return 1;
	else return 0;
}

// �־��� ��ġ�� �����͸� �����Ѵ�.
void delete(ListType *list, int pos)
{
	if (!is_empty(list) && (pos >= 0) && (pos < list->length)) {
		ListNode *p = get_node_at(list, pos - 1);
		remove_node(&(list->head), p, (p != NULL) ? p->link : NULL);
		list->length--;
	}
}
//
element get_entry(ListType *list, int pos)
{
	ListNode *p;
	if (pos >= list->length) error("��ġ ����");
	p = get_node_at(list, pos);
	return p->data;
}
//
void clear(ListType *list)
{
	int i;
	for (i = 0; i<list->length; i++)
		delete(list, i);
}
// ������ ������ ����Ѵ�. 
void display(ListType *buffer)
{
	int i;
	ListNode *tmp_node;
	tmp_node = buffer->head;

	printf("**************\n");
	for (i = 0; i<buffer->length; i++) {
		printf("%s\n", tmp_node->data.a);
		tmp_node = tmp_node->link;
	}
	printf("**************\n");
}
// 
void warning(char *message)
{
	fprintf(stderr, "%s\n", message);
}
//
void error(char *message)
{
	fprintf(stderr, "%s\n", message);
	exit(1);
}
//
void help()
{
	printf("**************\n");
	printf("i: �Է�\n");
	printf("d: ����\n");
	printf("r: �����б�\n");
	printf("w: ���Ͼ���\n");
	printf("q: ����\n");
	printf("**************\n");
}

// ��ũ ���Ϸκ��� �����͸� �д´�.
void read_file(ListType *buffer)
{
	char fname[MAX_NAME];
	FILE *fd;
	element p;

	if (!is_empty(buffer)) {
		clear(buffer);
	}
	init(buffer);

	printf("���� �̸�: ");
	scanf("%s", fname);
	if ((fd = fopen(fname, "r")) == NULL) {
		warning("������ ���������ϴ�.");
		return;
	}
	while (fgets(p.a, MAX_CHAR_PER_LINE, fd)) {
		add_last(buffer, p);
	}
	fclose(fd);
	display(buffer);
}
// ���ۿ� �ִ� �����͸� ��ũ ���Ͽ� ����.
void write_file(ListType *buffer)
{
	FILE *fd;
	char fname[MAX_NAME];
	element p;
	int i;

	printf("���� �̸�: ");
	scanf("%s", fname);
	if ((fd = fopen(fname, "w")) == NULL) {
		printf("������ ���������ϴ�.\n");
		return;
	}
	for (i = 0; i<get_length(buffer); i++) {
		p = get_entry(buffer, i);
		fputs(p.a, fd);
	}
	fclose(fd);
	display(buffer);
}

// �ϳ��� ������ �����
void delete_line(ListType *buffer)
{
	int position;

	if (is_empty(buffer))
		printf("���� ������ �����ϴ�.\n");
	else
	{
		printf("����� ���� ���ι�ȣ�� �Է��ϼ���:\n");
		scanf("%d", &position);
		delete(buffer, position);
	}
	display(buffer);
}
// �ϳ��� ������ �����Ѵ�.
void insert_line(ListType *buffer)
{
	int position;
	char line[MAX_CHAR_PER_LINE];
	element p;

	printf("�Է����ȣ�� �Է��ϼ���: \n");
	scanf("%d", &position);
	fflush(stdin);
	printf("������ �Է��ϼ���: ");
	fflush(stdin);
	scanf("%s", line);
	//fgets(line, MAX_CHAR_PER_LINE, stdin);
	strcpy(p.a,line);
	fflush(stdin);
	add(buffer, position, p);
	fflush(stdin);
	display(buffer);
	fflush(stdin);
}
//
void do_command(ListType *buffer, char command)
{
	switch (command)
	{
	case 'd':
		delete_line(buffer);
		break;
	case 'i':
		insert_line(buffer);
		break;
	case 'r':
		read_file(buffer);
		break;
	case 'w':
		write_file(buffer);
		break;
	case 'q':
		break;
	}
}



// ���� ������ ���� ���α׷�
void main()
{
	char command;
	ListType buffer;

	init(&buffer);
	do {
		help();
		command = getchar();
		do_command(&buffer, command);
		fflush(stdin);
	} while (command != 'q');
}