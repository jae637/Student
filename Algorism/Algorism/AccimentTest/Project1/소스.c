#include <stdio.h>
#include <time.h>
typedef struct tree                        // AVL tree node
{
	int data;                                        // data type is integer
	struct tree * left;                        // left subtree
	struct tree * right;                // right subtree
	struct tree * p;
	int height;                                        // height of the tree
}tree;

tree * root = NULL;                // root node, initially NULL
int counter;


//pre_order
void pre_order(tree* root, int level)
{
	if (root->data != NULL)
		printf("%d�� : %d\n", level, root->data);
	if (root->left != NULL)
		pre_order(root->left, level + 1);
	if (root->right != NULL)
		pre_order(root->right, level + 1);
}

//���� ���
int out_order(tree* root, FILE* fc)
{
	if (root->data != NULL)
		fprintf(fc, "%d\n", root->data);
	if (root->left != NULL)
		out_order(root->left, fc);
	if (root->right != NULL)
		out_order(root->right, fc);
}

//x�� y�� ū ���� ã�Ƽ� x�� ũ�� x, y�� ũ�� y ��ȯ
int max(int x, int y)
{
	return x > y ? x : y;
}

// ����� ���̸� ��ȯ�ϴ� ������ Ʈ���� ������ �ִ� ���̸� ��ȯ
int height_of(tree* x)
{
	return x == NULL ? -1 : x->height;        // ���� Ʈ���� ���� ���̶�� ���̸� -1�� �ӽ� ��ȯ�Ѵ�.
}

//�� ����� ���̸� ��������/ �����̼� �� �ٽ� ������ ��� ��. (�ؿ��� ����)
void reset_height(tree* x)                        
{
	if (x != NULL)
		x->height = max(height_of(x->left), height_of(x->right)) + 1;
}

// �������� �����ش�.
tree* rotate_left(tree* t)                
{
	tree* new_node = t->left;	//������ ����� ������ �� ���� ����
	t->left = new_node->right;			//������ ����� ������ �� ����� ���������� ����
	new_node->right = t;				//�� ����� �������� ������ ���� ����

	reset_height(t);					//������ ����� ���̸� �缳��
	reset_height(new_node);				//���ο� ����� ���̸� �缳��
	counter++;
	return new_node;					//���ο� ��带 ��ȯ�մϴ�.
}

//���������� �����ش�. ,�������� �����ִ� ���� �ݴ�
tree* rotate_right(tree* t)           
{
	tree* new_node = t->right;
	t->right = new_node->left;
	new_node->left = t;

	reset_height(t);
	reset_height(new_node);
	counter++;

	return new_node;
}


//������ ���� ����� �����̼�
tree* double_rotate_left(tree* t)           
{
	t->left = rotate_right(t->left);
	return rotate_left(t);
}

//���� ���� ����� �����̼�
tree* double_rotate_right(tree* t)       
{
	t->right = rotate_left(t->right);
	return rotate_right(t);
}

//��� ����
tree* insert(int insert_data, tree* x)          
{
	//x��� ����
	if (x == NULL)
	{
		x = (tree *)malloc(sizeof(tree));
		x->data = insert_data;
		x->left = NULL;
		x->right = NULL;
		x->height = 0;
	}
	//�Է°��� ����ġ ������ ���� ������
	else if (insert_data < x->data)                           
	{
		x->left = insert(insert_data, x->left);					//���� ��� ���ʿ� ���ο� ��� ����
		if (height_of(x->left) - height_of(x->right) == 2)		//���ο� ��� ������ �θ��忡�� ���̰� �ٽ� �Ǵ�.
		{
			if (insert_data < x->left->data)
				x = rotate_left(x);
			else {
				x = double_rotate_left(x);
				counter++;
			}
			counter++;
		}
	}
	else if (insert_data > x->data)
	{
		x->right = insert(insert_data, x->right);				//���� ��� �����ʿ� ���ο� ��� ����
		if (height_of(x->right) - height_of(x->left) == 2)
		{
			if (insert_data > x->right->data)
				x = rotate_right(x);
			else {
				x = double_rotate_right(x);
				counter++;
			}
			counter++;
		}
	}
	else
		printf("Insert Error : item duplicated!\n");          // when the item already exists

	reset_height(x);                                             // reset tree's height
	counter++;
	return x;
}

tree* delete_min(tree* x)        // delete minimum-valued node
{
	struct node* temp = x;

	if (x == NULL)
		printf("Delete Minimum Error : null tree!\n");
	else if (x->left != NULL)
	{
		x->left = delete_min(x->left);                    // traverse to left and left again
		if (height_of(x->right) - height_of(x->left) == 2)
		{
			if (height_of(x->right->left) > height_of(x->right->right))
				x = double_rotate_right(x);
			else
				x = rotate_right(x);
		}
	}
	else
	{
		x = x->right;                           // attach right subtree(or NULL) to parent
		free(temp);                                         // and deletee the old parent
	}

	reset_height(x);

	return x;
}

tree* deletee(int delete_data, tree* x)         // delete node
{
	tree* temp =x;

	if (x == NULL)
		printf("Delete Error : item not found!\n");
	else if (delete_data < x->data)              // delete data in left subtree
	{
		x->left = deletee(delete_data, x->left);
		if (height_of(x->right) - height_of(x->left) == 2)
		{
			if (height_of(x->right->left) > height_of(x->right->right))
				x = double_rotate_right(x);
			else {
				x = rotate_right(x);
				counter++;
			}
			counter++;
		}
		counter++;
	}
	else if (delete_data > x->data)
	{
		x->right = deletee(delete_data, x->right);
		if (height_of(x->left) - height_of(x->right) == 2)
		{
			if (height_of(x->left->right) > height_of(x->left->left))
				x = double_rotate_left(x);
			else {
				x = rotate_left(x);
				counter++;
			}
			counter++;
		}
		counter++;
	}
	else if (x->left != NULL && x->right != NULL)
	{
		temp = temp->right;
		while (temp->left != NULL) {
			temp = temp->left;       // find the minimum data of right subtree
			counter++;
		}
		x->data = temp->data;        // and set it to the parent
		x->right = delete_min(x->right);       // and deletee it in right subtree
	}
	else
	{
		x = (x->left != NULL) ? x->left : x->right;
		free(temp);                                   // free the old parent
	}
	reset_height(x);

	return x;
}

int main()
{
	FILE* fp;
	char buffer[11];
	int i,temp, savenum;
	int arraynum[4500];
	int insertnum;
	int count = 0;					// # of elements
	int select;						//�޴� ���� ����

	srand(time(NULL));
	for (i = 0; i < 4500; i++) {
		arraynum[i] = i + 1;
	}
	for (i = 0; i < 4500; i++) {
		savenum = (rand() % 4500);
		temp = arraynum[savenum];
		arraynum[savenum] = arraynum[i];
		arraynum[i] = temp;
	}
	while (1) {
		counter = 0;
		printf("1�� ����, 2�� ����, 3�� ���� (1~3):");
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
			printf("�� Ƚ���� %d �Դϴ�.\n", counter);
		}
		else if (select == 2) {
			printf("�Է��� ���ڸ� �־��ּ���");
			scanf("%d", &insertnum);
			root = insert(insertnum, root);
			count++;
			pre_order(root, 0);
			printf("�� Ƚ���� %d �Դϴ�.\n", counter);
		}
		else if (select == 3) {
			printf("�Է��� ���ڸ� �־��ּ���");
			scanf("%d", &insertnum);
			root = deletee(insertnum, root);
			count--;
			pre_order(root, 0);
			printf("�� Ƚ���� %d �Դϴ�.\n", counter);
		}
		else if (select == 4)
		{
			FILE *f;
			int i;
			f = fopen("a.txt", "w");
			out_order(root, f);
			fclose(f);
		}
		else if (select == 5)
		{
			for (i = 0; i < 4500; i++) {
				root = insert(arraynum[i], root);
				count++;
			}
			pre_order(root, 0);
			printf("�� Ƚ���� %d �Դϴ�.\n", counter);
		}

		else
			exit(1);
	}

	system("pause");
	return 0;
}