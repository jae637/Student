#include <stdio.h>
int main() {
	int num1, num2;
	char art;

	printf("���� 2���� �����ڸ� �Է��ϼ���.\n\n");
	printf("�����ڴ� �Ʒ��� �����ϴϴ�.\n");

	printf("���ϱ� : +, ���� : -, ���ϱ� : *, ������ : /\n\n");
	printf("�Է� ���� : ����1 ������ ����2\n\n");

	scanf("%d %c %d", &num1, &art, &num2);

	switch (art)
	{
	case'+': printf("%d%c%d=%d\n", num1, art, num2, num1 + num2);
		break;
	case'-': printf("%d%c%d=%d\n", num1, art, num2, num1 - num2);
		break;
	case'*': printf("%d%c%d=%d\n", num1, art, num2, num1*num2);
		break;

	case'/': printf("%d%c%d=%d\n", num1, art, num2, num1 / num2);
		break;
	dafault: printf("�����ڸ� �߸� �Է��ϼ̽��ϴ�.");
		break;
	}
	system("pause");
	return 0;
}