#include <stdio.h>
#include <dirent.h>
#include <string.h>

//select == 1 then ls, 2 then ls -l, 3 then ls-al
int my_double_ls(const char *name,int select)
{
	struct dirent *d;
	DIR *dp;

	if ((dp=opendir(name))==NULL) 
		return -1;
	printf("\n");
switch(select){
case 1:
	while(d=readdir(dp)){
		if(d->d_ino !=0){
		if(strcmp(d->d_name,".")==0||strcmp(d->d_name,"..")==0){}
		else
			printf("%s\t", d->d_name);
		}
	}
	break;
case 2:
	while(d=readdir(dp)){
		if(d->d_ino !=0){
		if(strcmp(d->d_name,".")==0||strcmp(d->d_name,"..")==0){}
		else
		if(d->d_ino !=0)
			printf("%s\n", d->d_name);
		}
	}
	break;
case 3:
	while(d=readdir(dp)){
		if(d->d_ino !=0)
			printf("%s\n", d->d_name);
	}
	break;
}
	printf("\n");

	closedir(dp);
	return 0;
}

int main(int argc, char* argv[]){
	char* string;
	int i;
	string=".";
	if(argc==1)
	{
		my_double_ls(string,1);
	}
	else if(argc==2)
	{
		if(strcmp(argv[1],"-l")==0)
			my_double_ls(string,2);
		if(strcmp(argv[1],"-al")==0)	
			my_double_ls(string,3);
		if(argv[1][0]!='-')
			my_double_ls(argv[1],1);
	}
	else if(argc==3)
	{
		if(strcmp(argv[1],"-l")==0)
			my_double_ls(argv[2],2);
		if(strcmp(argv[1],"-al")==0)	
			my_double_ls(argv[2],3);
		else{
			printf("error!! use help manual\n");
		}
	}
	return 0;
}
