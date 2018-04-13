#include "header.h"

int main() {
	FILE *fp;
	int i, select; //for문,if문, switch문 반복 인수
	int selectdata;	//트리의 삽입, 삭제의 값
	int size;		//트리의 노드 갯수
	int *arrnum;	//트리로 인풋 데이터들을 저장하는 배열
	tree *avlheader;//AVL이진트리를 가르키는 헤더
	tree *node;		//트리 형 인풋 데이터들
	tree *selecttree;//정의된 트리
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

	//메인 메뉴
	while (1) {
		counter = 0;
		printf("1번 생성, 2번 삽입, 3번 삭제 (1~3):");
		scanf("%d", &select);
		switch (select)
		{
		case 1:
			printf("파일을 불러와 트리를 생성합니다.\n");
			for (i = 0; i < size; i++) {
				tree_insert(&header, &node[i]);
			}
			pre_order(header, 0);
			printf("비교 횟수는 %d번 입니다.\n", counter);
			break;
		case 2:
			printf("트리를 삽입합니다.\n");
			printf("넣을 데이터 값을 입력해 주세요 :");
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
			printf("비교 횟수는 %d번 입니다.\n", counter);
			break;
		case 3:
			printf("트리를 삭제합니다.\n");
			printf("삭제할 데이터 값을 입력해 주세요 :");
			scanf("%d", &selectdata);
			if (tree_search(header, selectdata) == NULL) {
				printf("삭제 할 수 없는 노드입니다\n");
				break;
			}
			else {
				counter = 0;
				tree_delete(&header, tree_search(header, selectdata));
			}
			pre_order(header, 0);
			printf("비교 횟수는 %d번 입니다.\n", counter);
			break;
		default:
			return 0;
			break;
		}
	}
}