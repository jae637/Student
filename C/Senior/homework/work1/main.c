#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

extern int addnum(int a, int b);
extern int subnum(int a, int b);
extern int multnum(int a, int b);
extern int divnum(int a, int b);

int main(int argc, char* argv[]) {
        int sum;
        int i;
        char slt;
        int x,y;
	pid_t pid;

	if((pid=fork())<0) {
			perror("fork error");
			exit(1);
		}
	else if(pid==0){
	        if(4<argc||argc <2||argc==3){
	                printf("Error!! Use help commend\n");
	                exit(1);
		}
	        slt= argv[1][1];

	        if(slt=='h'&&argc==2){

	                printf("3Parameter\n\t 1: option\n\t 2: number1\n\t 3:number2\n");
	                printf("Using this program option. \n\t -a = add\n");
	                printf("\t -s = subtract\n");
	                printf("\t -m = multifly\n");
	                printf("\t -d = divied \n");
	                printf("\t -h = help!\n");
	                exit(1);
	        }

	        x=atoi(argv[2]);
		y=atoi(argv[3]);

	        if(slt=='a')
	                sum = addnum(x,y);
	        if(slt=='s')
	                sum = subnum(x,y);
	        if(slt=='d')
               		 sum = divnum(x,y);
	        if(slt=='m')
       		         sum = multnum(x,y);

	        printf("result : %d\n", sum);
		exit(1);
	}
	else
	{		
		sleep(1);	
		exit(1);
	}
}
