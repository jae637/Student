#include<stdio.h>

void customSort(int arr_count, int* arr) {
	int *temp;
	//int = (int*)malloc(sizeof(int))
}

int main() {
	int b[6] = { 1,2,3,4,4,5 };
	int *a;
	int c;
	a = (int*)malloc(sizeof(int) * 6);
	a = b;
	c = sizeof(a) / sizeof(a[1]);
	printf("%d",c);
	system("pause");
}