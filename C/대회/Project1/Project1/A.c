#include <stdio.h>

int main() {
	//�Է�
	unsigned long number;
	int answer;
	scanf_s("%d", &number);
	if (number < 3 || number > 1000000000000000000) {
		return 0;
	}
	
	answer = change(number);
	printf("%d", answer);
	scanf_s("%d", &number);
	return 0;
}
//2��������
int change(int num) {
	int two = 2;
	int answer = 0;
	while (1) {
		if (num < two) return (answer-1);

		two *= 2;
		answer++;
	}
}