#include "header.h"

int main() {
	FILE *fp;
	int i, select; //for��,if��, switch�� �ݺ� �μ�
	int selectdata;	//Ʈ���� ����, ������ ��
	int size;		//Ʈ���� ��� ����
	int *arrnum;	//Ʈ���� ��ǲ �����͵��� �����ϴ� �迭
	tree *header;	//����Ʈ���� ����Ű�� ���
	tree *avlheader;//AVL����Ʈ���� ����Ű�� ���
	tree *node;		//Ʈ�� �� ��ǲ �����͵�
	tree *selecttree;//���ǵ� Ʈ��
	int num[4500];	//�������� ¥���� Ʈ���� ������ ����
	int temp, savenum;		//�迭�� ������ ���� ���� ����
	
	srand(time(NULL));
	for (i = 0; i < 4500; i++) {
		num[i] = i + 1;
	}
	for (i = 0; i < 4500; i++) {
		savenum = (rand() % 4500);
		temp = num[savenum];
		num[savenum]=num[i];
		num[i] = temp;
	}
	fp = fopen("input.txt", "r");
	fseek(fp, 0, SEEK_END);
	size = ftell(fp);
	arrnum = (int*)malloc(size + 1);
	fseek(fp, 0, SEEK_SET);
	i = 0;
	while(fscanf(fp, "%d\n", &arrnum[i]) != EOF) {
		i++;
	}
	fclose(fp);
	size = i;
	node = (tree*)malloc(sizeof(tree)*size);
	header = NULL;

	for (i = 0; i < size; i++) {
		node[i].data = arrnum[i];
		node[i].left_node = NULL;
		node[i].right_node = NULL;
		node[i].p_node = NULL;
	}

	//���� �޴�
	while (1) {
		counter = 0;
		printf("1�� ����, 2�� ����, 3�� ����,4�� ���� ����(4500��) (1~4):");
		scanf("%d", &select);
		switch (select)
		{
		case 1:
			printf("������ �ҷ��� Ʈ���� �����մϴ�.\n");
			for (i = 0; i < size; i++) {
				tree_insert(&header, &node[i]);
			}
			pre_order(header, 0);
			printf("�� Ƚ���� %d�� �Դϴ�.\n", counter);
			break;
		case 2:
			printf("Ʈ���� �����մϴ�.\n");
			printf("���� ������ ���� �Է��� �ּ��� :");
			scanf("%d", &selectdata);
			selecttree = (tree*)malloc(sizeof(tree));
			selecttree->data = selectdata;
			selecttree->left_node = NULL;
			selecttree->right_node = NULL;
			selecttree->p_node = NULL;
			size++;
			tree_insert(&header, selecttree);
			pre_order(header, 0);
			printf("�� Ƚ���� %d�� �Դϴ�.\n", counter);
			break;
		case 3:
			printf("Ʈ���� �����մϴ�.\n");
			printf("������ ������ ���� �Է��� �ּ��� :");
			scanf("%d", &selectdata);
			if (tree_search(header, selectdata) == NULL) {
				printf("���� �� �� ���� ����Դϴ�\n");
				break;
			}
			else {
				counter = 0;
				tree_delete(&header, tree_search(header, selectdata));
			}
			pre_order(header,0);
			printf("�� Ƚ���� %d�� �Դϴ�.\n", counter);
			break;
		case 4:
			for (i = 0; i < 4500; i++) {
				selecttree = (tree*)malloc(sizeof(tree));
				selecttree->data = num[i];
				selecttree->left_node = NULL;
				selecttree->right_node = NULL;
				selecttree->p_node = NULL;
				size++;
				tree_insert(&header, selecttree);
			}
			pre_order(header, 0);
			printf("�� Ƚ���� %d�� �Դϴ�.\n", counter);
			break;
		default:
			return 0;
			break;
		}
	}
}