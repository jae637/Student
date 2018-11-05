#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#pragma warning(disable : 4996)

char* getShiftedString(char* s, int leftShifts, int rightShifts) {
	char *temp;
	char save = NULL;
	int leng, i, j;
	leng = strlen(s);
	temp = (char*)malloc(sizeof(char)*leng);
	strcpy(temp, s);


	for (i = 0; i < leftShifts; i++) {
		save= temp[0];
		for (j = 0; j < leng-1; j++)
			temp[j] = temp[j + 1];
		temp[leng-1]= save;
	}

	save = temp[leng];
	for (i = 0; i < rightShifts; i++) {
		save = temp[leng-1];
		for (j = leng-1; j > 0; j--)
			temp[j] = temp[j - 1];
		temp[0] = save;
	}
	return temp;
}

int main() {
	char* a;
	a = (char*)malloc(sizeof(char) * 5);
	a = "abcde";
	printf("%s",getShiftedString(a,1,2));
	system("pause");
}
