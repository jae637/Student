#pragma once
#define _CRT_SECURE_NO_WARRNING
#define max(a,b) (((a)>(b))?(a):(b))
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

int counter;	//비교횟수 카운터
	//이진트리를 가르키는 헤더

// 왼쪽에 더 많으면 +, 오른쪽에 더 많으면 -
typedef struct tree {
	struct tree *left_node;
	struct tree *right_node;
	struct tree *p_node;
	int data;
	int height;
} tree;

tree *header;

int height_of(tree* tree);
void reset_height(tree* tree);
void left_rotate(tree** header, tree *x);
void right_rotate(tree **header, tree *x);
void lr_rotate(tree **header, tree* x);
void rl_rotate(tree **header, tree* x);
tree *tree_search(tree *x, int k);
tree *tree_minimum(tree *x);
tree *tree_maximum(tree* x);
void tree_insert(tree **header, tree* z);
void rotate2(tree** header, tree* z);
void set_height2(tree** header);
void transplant(tree **header, tree* u, tree* v);
void tree_delete(tree **header, tree* z);
void pre_order(tree* root, int level);

int height_of(tree* tree)
{
	return tree == NULL ? -1 : tree->height;        
}

void reset_height(tree* tree)                       
{
	if (tree != NULL)
		tree->height = max(height_of(tree->left_node), height_of(tree->right_node)) + 1;
}

void right_rotate(tree** header, tree *z) {
	tree* y,*x;
	x = z;
	y = x->left_node;
	x->left_node = y->right_node;
	y->right_node = x;
	y->p_node = x->p_node;
	x->p_node = y;

	reset_height(x);
	reset_height(y);
	
	if (y->p_node == NULL)
		*header = y;
}

void left_rotate(tree **header, tree *z) {
	tree *x,*y;
	x = z;
	y = x->right_node;
	x->right_node = y->left_node;
	y->left_node = x;
	y->p_node = x->p_node;
	x->p_node = y;

	reset_height(x);
	reset_height(y);
	
	if (y->p_node == NULL)
		*header = y;
}

void lr_rotate(tree **header, tree* z) {
	tree* x, *y;
	x = *header;
	y = z;
	right_rotate(&x, y->right_node);
	left_rotate(&x, y);
}

void rl_rotate(tree **header, tree* z) {
	tree* x, *y;
	x = *header;
	y = z;
	left_rotate(&x, (y->left_node));
	right_rotate(&x, y);
}

tree *tree_search(tree *x, int k) {
	counter++;
	if (x == NULL || k == x->data)
		return x;
	if (k < x->data)
		return tree_search(x->left_node, k);
	else if (k == x->data) {
		counter = 0;
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
		counter++;
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
	else if (z->data < y->data) {
		y->left_node = z;
	}
	else {
		y->right_node = z;
	}
	set_height2(header);
	rotate2(header,*header);
	counter++;
}

void rotate2(tree** header,tree* z) {
	tree *x=NULL,*y=NULL;
	if (z == NULL) {
		z = *header;
	}
	else {
		x = z;
	}
	if(x->right_node!=NULL)
		rotate2(header,x->right_node);
	if(x->left_node!=NULL)
		rotate2(header,x->left_node);

	if (x->p_node == NULL)
		return;
	if (height_of(x->p_node->left_node) - height_of(x->p_node->right_node) == 2) {
		if (height_of(x->left_node)-height_of(x->right_node ==1))
			right_rotate(header, &(x->p_node));
		else
			rl_rotate(header, x->p_node);
		counter++;
		return;
	}
	else if (height_of(x->p_node->right_node) - height_of(x->p_node->left_node) == 2) {
		if (height_of(x->right_node) - height_of(x->left_node) == 1)
			left_rotate(header, &(x->p_node));
		else
			lr_rotate(header, x->p_node);
		counter++;
		return;
	}
}

void set_height2(tree** header) {
	tree *x;
	x = *header;
	if (x != NULL) {
		set_height2(&(x->left_node));
		set_height2(&(x->right_node));
		reset_height(x);
//		counter++;
	}
}

//총 비교횟수 1회 u와 v의 위치를 바꾸는것.

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

void pre_order(tree* root, int level)
{
	if(root->data!=NULL)
		printf("%d층 : %d\n",level, root->data);
	if(root->left_node!=NULL)
		pre_order(root->left_node,level+1);
	if(root->right_node!=NULL)
		pre_order(root->right_node,level+1);
}