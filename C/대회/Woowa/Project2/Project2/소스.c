#include<stdio.h>
#include<stdlib.h>
#include<math.h>
int solution(int A[], int B[], int N, int M, int X, int Y) {
	// A,B 각각 사람의 무게와 키로수, N 대기열, M 최고 층수,X=최대수용 인원, Y= 무게 제한
	// write your code in C99 (gcc 6.2.0)
	int counter = 0;
	int *target;
	int sumweight = 0;
	int order = 0;
	int passager = N;
	int inner = 0;
	int now = 0;
	int i, j, k;

	target = (int*)malloc(sizeof(int)*X);

	while (now <= passager) {
		for (i = 0; i < X; i++) {
			target[i] = 0;
		}
		order = 0;
		k = 0;
		inner = 0;
		sumweight = 0;
		for (i = 0; i < X; i++) {
			if (sumweight + A[i + now] > Y)
				break;
			else {
				if (i + now > N)
					break;
				sumweight = sumweight + A[i + now];
				inner++;
				for (j = 0; j <= order; j++) {
					if (target[j] == B[i + now]) {
						order--;
						break;
					}
				}
				target[order] = B[i+now];
				order++;
			}
		}
		counter = counter + order+1;
		now = now + inner;
	}
	return counter;
}

int main() {
	int A[5] = { 40,40,100,80,20 };
	int B[5] = {3, 3, 2,2,3};
	int num = solution(A,B, 5,3, 5, 200);
	printf("%d\n",num);
	system("pause");
}
