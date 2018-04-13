#include <stdio.h>
#include <stdlib.h>
#include <sys/shm.h>
#include <sys/wait.h>
#include <string.h>
#include <unistd.h>
#include <time.h>

#define SHARED_MEMORY_KEY 1005

#define READ_CLIENT_FLAG 0
#define READ_SERVER_FLAG 1
#define PRINT_CLIENT_FLAG 2

int main()
{
	pid_t pid;
	int count=0;
    int shmid;
    char *buffer;
    char *string;

    // make space that shared-memory
    shmid = shmget((key_t)SHARED_MEMORY_KEY, 0, 0);
    if(shmid == -1)
    {
            perror("shmat failed : ");
            exit(0);
    }

    // attach shared memory
    buffer = (char *)shmat(shmid, NULL, 0);
    if(buffer == (char *)-1){
        perror("shmat failed : ");
        exit(0);
    }

    buffer[0] = READ_CLIENT_FLAG;
    string = buffer  + 1;
	pid=fork();
  
	if(pid==0)
	{
	 	while(1)
		{
			if(buffer[0] == READ_CLIENT_FLAG)
			{
				gets(string);
				buffer[0] = READ_SERVER_FLAG;
				count=1;
			}
		}
	}
	else 
	{
		while(1)
		{
			if(buffer[0] == READ_SERVER_FLAG)
			{
				if(count==1)
					sleep(1);
				printf("Other : ");
				puts(string);
				buffer[0] = READ_CLIENT_FLAG;
			}
		}
	}
}
