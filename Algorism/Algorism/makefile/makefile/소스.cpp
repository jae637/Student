#include <stdio.h>

int main()
{
	FILE *f;
	int i;
	f = fopen("a.txt", "w");
	for (i = 1; i <= 10000; i++) {
		fprintf(f, "%d\n",i);
	}
	fclose(f);
}
