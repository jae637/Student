#include<fcntl.h>
#include<errno.h>
#include<emp.h>
#define MAX 10

int main(argc,argv)
int argc;
char *argv[];
{
	struct flock lock;
	struct emp record;
	int fd, open(), fcntl(), sum=0, try=0;

	if((fd=open(argv[1],O_RDONLY))==-1){
		perror(argv[1]);
		exit(1);
	}
	lock.l_type = F_RDLCK;
	lock.l_whence =0;
	lock.l_start=0L;
	lock.l_len=0L;

	while(
}
