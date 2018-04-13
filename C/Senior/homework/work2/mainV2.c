#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

int main(int argc, char *argv[]){

	pid_t pid, d_pid;
	int exit_status;

	if((pid=fork())<-1){
		perror("fork error");
	}
	else if(pid==0){
		execl("./calc",argv[0],argv[1],argv[2],argv[3],(char*) 0);
		printf("child process!");
		exit(1);
	}
	else{
		sleep(1);
		exit(1);
	}

	printf("hello");
	return 0;
}
