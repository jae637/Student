#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>

void handler(int signo)
{
	printf ("Signal Handler Signal Number : %d\n",signo);
	psignal(signo, "Received Signal");
}

int main(void)
{
	FILE * fp;
	void (*hand)(int);
	int pid_num[30];
	int lotto[30];
	int status;
	int MAX_NUM;
	pid_t pid[30];
	pid_t pid_child;

	int i;
	int childStatus;
	

	for(i=0;i<30;i++)
	{
		printf("응모할 숫자를 입력해 주세요 (0 입력시 종료) : ");
		scanf("%d",&lotto[i]);
		if (lotto[i]==0)
			break;
		pid[i]= fork();
		if(pid[i] ==0)
		{
			pid_num[i]=(int)getpid();
			exit(1);
		}
		else
		{
			pid_child = waitpid( pid[i],&status,0);
			pid_num[i]=(int)pid_child;
		}
	}	
	// Write included New Score
	fp = fopen("lotto_appl", "wt");
	printf("\npidNumbe\tlottoNumber\n");
	for(i=0;i<sizeof(pid_num);i++) {
		if ( lotto[i] == 0)
			break;
		fwrite( &pid_num[i], sizeof(int), 1, fp );
		fwrite( &lotto[i], sizeof(int), 1, fp );
		printf("%d\t%d\n",(int)pid_num[i],lotto[i]);
	}

	fclose(fp);
	return 1;
}
