#include "header.h"

int main() {
	FILE *fp;
	int i, select; //for��,if��, switch�� �ݺ� �μ�
	int selectdata;	//Ʈ���� ����, ������ ��
	int size;		//Ʈ���� ��� ����
	int *arrnum;	//Ʈ���� ��ǲ �����͵��� �����ϴ� �迭
	tree *avlheader;//AVL����Ʈ���� ����Ű�� ���
	tree *node;		//Ʈ�� �� ��ǲ �����͵�
	tree *selecttree;//���ǵ� Ʈ��
	time_t startTime = 0, endTime = 0;
	float gap;


	fp = fopen("input.txt", "r");
	fseek(fp, 0, SEEK_END);
	size = ftell(fp);
	arrnum = (int*)malloc(size + 1);
	fseek(fp, 0, SEEK_SET);
	i = 0;
	while (fscanf(fp, "%d\n", &arrnum[i]) != EOF) {
		i++;
	}
	fclose(fp);
	size = i;
	node = (tree*)malloc(sizeof(tree)*size);
	header = NULL;

	for (i = 0; i < size; i++) {
		node[i].data = arrnum[i];
		node[i].height = 0;
		node[i].left_node = NULL;
		node[i].right_node = NULL;
		node[i].p_node = NULL;
	}

	//���� �޴�
	while (1) {
		counter = 0;
		printf("1�� ����, 2�� ����, 3�� ���� (1~3):");
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
			selecttree->height = 0;
			size++;
			/*realloc(node, sizeof(tree)*(size));
			node[size - 1].data = selecttree.data;
			node[size - 1].height = 0;
			node[size - 1].left_node = selecttree.left_node;
			node[size - 1].right_node = selecttree.right_node;
			node[size - 1].p_node = selecttree.p_node;*/
			//tree_insert(&header, &node[size - 1]);
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
			pre_order(header, 0);
			printf("�� Ƚ���� %d�� �Դϴ�.\n", counter);
			break;
		default:
			return 0;
			break;
		}
	}
}