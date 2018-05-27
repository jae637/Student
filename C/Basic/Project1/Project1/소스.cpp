#include<stdio.h>

int main() {
	int i, j, count = 0;
	for (i = 2; i <= 100; ++i) {
		for (j = 2; i%j; j++);

		if (j == i) {
			count++;
			printf("%d%s", i, count % 5 ? "," : "\n");
		}
	}
	return 0;
}