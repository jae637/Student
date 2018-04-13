#pragma once
#define _CRT_SECURE_NO_WARRNING
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

typedef struct tree {
	struct tree *left_node;
	struct tree *right_node;
	struct tree *p_node;
	int data;
} tree;

long counter;	//��Ƚ�� ī����
tree *tree_search(tree *x, int k);					//xƮ���� �������� k��带 ã��(���� ������ ����)
tree *tree_minimum(tree *x);						//xƮ���� �������� ���� ū ��带 ������ ��
tree *tree_maximum(tree* x);						//xƮ���� �������� ���� ���� ��带 ������ ��
void tree_insert(tree **header, tree* z);			//header�� ����Ű�� Ʈ���� ������ z��带 ����
void transplant(tree **header, tree* u, tree* v);	//header�� ����Ű�� Ʈ���� u�� v�� ��带 �ٲ�
void tree_delete(tree **header, tree* z);			//header�� ���� z��带 ����
void pre_order(tree* root, int level);				//�� ,����, ������ ������ ���

// ���ʿ� �� ������ +, �����ʿ� �� ������ -

tree *tree_search(tree *x, int k) {
	counter++;
	if (x == NULL || k == x->data)
		return x;
	if (k < x->data)
		return tree_search(x->left_node, k);
	else if (k == x->data) {
		return NULL;
	}
	else 
		return tree_search(x->right_node, k);
}

tree *tree_minimum(tree *x) {
	while (x->left_node != NULL) {
		counter++;
		x = x->left_node;
	}
	return x;
}

tree *tree_maximum(tree* x) {
	while (x->right_node != NULL)
		x = x->right_node;
	return x;
}

void tree_insert(tree **header, tree* z)
{
	tree *x, *y;
	y = NULL;
	x = *header;
	while (x != NULL)
	{
		y = x;
		if (z->data < x->data) {
			x = x->left_node;
			
		}
		else if (z->data == x->data){
			counter = 0;
			return;
		}
		else {
			x = x->right_node;
		}
		counter++;
	}
	z->p_node = y;
	if (y == NULL)
		*header = z;
	else if (z->data < y->data)
		y->left_node = z;
	else
		y->right_node = z;
	counter++;
}

//�� ��Ƚ�� 1ȸ u�� v�� ��ġ�� �ٲٴ°�.
void transplant(tree **header, tree* u, tree* v)
{
	counter++;
	if (u->p_node == NULL)
		*header = v;
	else if (u == u->p_node->left_node)
		u->p_node->left_node = v;
	else
		u->p_node->right_node = v;

	if (v != NULL)
		v->p_node = u->p_node;
}

void tree_delete(tree **header, tree* z) {
	tree *y;
	if (z->left_node == NULL)
		transplant(header, z, z->right_node);
	else if (z->right_node == NULL)
		transplant(header, z, z->left_node);
	else {
		y = tree_minimum(z->right_node);
		if (y->p_node != z) {
			transplant(header, y, y->right_node);
			y->right_node = z->right_node;
			y->right_node->p_node = y;
		}
		transplant(header, z, y);
		y->left_node = z->left_node;
		y->left_node->p_node = y;
	}
}

void pre_order(tree* root, int level){
	if(root->data!=NULL)
		printf("%d�� : %d\n",level, root->data);
	if(root->left_node!=NULL)
		pre_order(root->left_node,level+1);
	if(root->right_node!=NULL)
		pre_order(root->right_node,level+1);
}