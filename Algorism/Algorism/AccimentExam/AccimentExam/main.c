#include <stdio.h>

struct node                        // AVL tree node
{
	int data;                                        // data type is integer
	struct node * left;                        // left subtree
	struct node * right;                // right subtree
	int height;                                        // height of the tree
};

struct node * root = NULL;                // root node, initially NULL
int counter;


void pre_order(struct node* root, int level)
{
	if (root->data != NULL)
		printf("%d층 : %d\n", level, root->data);
	if (root->left != NULL)
		pre_order(root->left, level + 1);
	if (root->right != NULL)
		pre_order(root->right, level + 1);
}

int out_order(struct node* root,FILE* fc)
{
	if (root->data != NULL)
		fprintf(fc, "%d", root->data);
	if (root->left != NULL)
		out_order(root->left,fc);
	if (root->right != NULL)
		out_order(root->right,fc);
}

int max(int x, int y)
{
	return x > y ? x : y;
}

int height_of(struct node* tree)
{
	return tree == NULL ? -1 : tree->height;        // if the tree is NULL, its height is -1
}

void reset_height(struct node* tree)                        // after rotation, reset tree's height
{
	if (tree != NULL)
		tree->height = max(height_of(tree->left), height_of(tree->right)) + 1;
}

struct node* rotate_left(struct node* t)                // rotate left for balancing
{
	struct node* new_node = t->left;
	t->left = new_node->right;
	new_node->right = t;

	reset_height(t);
	reset_height(new_node);                                                // reset nodes' height

	return new_node;
}

struct node* rotate_right(struct node* t)                // right
{
	struct node* new_node = t->right;
	t->right = new_node->left;
	new_node->left = t;

	reset_height(t);
	reset_height(new_node);

	return new_node;
}

struct node* double_rotate_left(struct node* t)           // in some cases, need to rotate twice
{
	t->left = rotate_right(t->left);
	return rotate_left(t);
}

struct node* double_rotate_right(struct node* t)        // symmetric
{
	t->right = rotate_left(t->right);
	return rotate_right(t);
}

struct node* insert(int insert_data, struct node* tree)                // node insert
{
	if (tree == NULL)                                                           // if null, make new node
	{
		tree = (struct node *)malloc(sizeof(struct node));
		tree->data = insert_data;
		tree->left = NULL;
		tree->right = NULL;
		tree->height = 0;
	}
	else if (insert_data < tree->data)                             // insert data in left subtree
	{
		tree->left = insert(insert_data, tree->left);
		if (height_of(tree->left) - height_of(tree->right) == 2)
		{
			if (insert_data < tree->left->data)
				tree = rotate_left(tree);
			else {
				tree = double_rotate_left(tree);
				counter++;
			}
			counter++;
		}
	}
	else if (insert_data > tree->data)
	{
		tree->right = insert(insert_data, tree->right);
		if (height_of(tree->right) - height_of(tree->left) == 2)
		{
			if (insert_data > tree->right->data)
				tree = rotate_right(tree);
			else {
				tree = double_rotate_right(tree);
				counter++;
			}
			counter++;
		}
	}
	else
		printf("Insert Error : item duplicated!\n");          // when the item already exists

	reset_height(tree);                                             // reset tree's height
	counter++;
	return tree;
}

struct node* delete_min(struct node* tree)        // delete minimum-valued node
{
	struct node* temp = tree;

	if (tree == NULL)
		printf("Delete Minimum Error : null tree!\n");
	else if (tree->left != NULL)
	{
		tree->left = delete_min(tree->left);                    // traverse to left and left again
		if (height_of(tree->right) - height_of(tree->left) == 2)
		{
			if (height_of(tree->right->left) > height_of(tree->right->right))
				tree = double_rotate_right(tree);
			else
				tree = rotate_right(tree);
		}
	}
	else
	{
		tree = tree->right;                           // attach right subtree(or NULL) to parent
		free(temp);                                         // and deletee the old parent
	}

	reset_height(tree);

	return tree;
}

struct node* deletee(int delete_data, struct node* tree)         // delete node
{
	struct node* temp = tree;

	if (tree == NULL)
		printf("Delete Error : item not found!\n");
	else if (delete_data < tree->data)              // delete data in left subtree
	{
		tree->left = deletee(delete_data, tree->left);
		if (height_of(tree->right) - height_of(tree->left) == 2)
		{
			if (height_of(tree->right->left) > height_of(tree->right->right))
				tree = double_rotate_right(tree);
			else {
				tree = rotate_right(tree);
				counter++;
			}
			counter++;
		}
		counter++;
	}
	else if (delete_data > tree->data)
	{
		tree->right = deletee(delete_data, tree->right);
		if (height_of(tree->left) - height_of(tree->right) == 2)
		{
			if (height_of(tree->left->right) > height_of(tree->left->left))
				tree = double_rotate_left(tree);
			else {
				tree = rotate_left(tree);
				counter++;
			}
			counter++;
		}
		counter++;
	}
	else if (tree->left != NULL && tree->right != NULL)
	{
		temp = temp->right;
		while (temp->left != NULL) {
			temp = temp->left;       // find the minimum data of right subtree
			counter++;
		}
		tree->data = temp->data;        // and set it to the parent
		tree->right = delete_min(tree->right);       // and deletee it in right subtree
	}
	else
	{
		tree = (tree->left != NULL) ? tree->left : tree->right;
		free(temp);                                   // free the old parent
	}
	reset_height(tree);

	return tree;
}

int main()
{
	FILE* fp;
	char buffer[11];
	int insertnum;
	int count = 0;					// # of elements
	int select;						//메뉴 선택 변수

	while (1) {
		counter = 0;
		printf("1번 생성, 2번 삽입, 3번 삭제 (1~3):");
		scanf("%d", &select);
		if (select == 1) {
			fp = fopen("index.txt", "r");
			while (fgets(buffer, 10, fp) != NULL)           // read one line from index.txt
			{
				root = insert(atoi(buffer), root);                                // insert it
				count++;
			}
			fclose(fp);
			pre_order(root, 0);
			printf("비교 횟수는 %d 입니다.\n", counter);
		}
		else if (select == 2) {
			printf("입력할 숫자를 넣어주세요");
			scanf("%d", &insertnum);
			root = insert(insertnum, root);
			count++;
			pre_order(root, 0);
			printf("비교 횟수는 %d 입니다.\n", counter);
		}
		else if (select == 3) {
			printf("입력할 숫자를 넣어주세요");
			scanf("%d", &insertnum);
			root = deletee(insertnum, root);
			count--;
			pre_order(root, 0);
			printf("비교 횟수는 %d 입니다.\n", counter);
		}
		else if(select == 4)
		{
			FILE *f;
			int i;
			f = fopen("a.txt", "w");
			out_order(root, f);
			fclose(f);
		}

		else
			exit(1);
	}

	system("pause");
	return 0;
}