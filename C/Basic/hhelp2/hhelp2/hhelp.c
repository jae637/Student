#include <stdio.h>
int main() {
	int num1, num2;
	char art;

	printf("숫자 2개와 연산자를 입력하세요.\n\n");
	printf("연산자는 아래와 같습니니다.\n");

	printf("더하기 : +, 빼기 : -, 곱하기 : *, 나누기 : /\n\n");
	printf("입력 순서 : 정수1 연산자 정수2\n\n");

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
	dafault: printf("연산자를 잘못 입력하셨습니다.");
		break;
	}
	system("pause");
	return 0;
}