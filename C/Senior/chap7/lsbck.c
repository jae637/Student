#include <stdio.h>
#include <dirent.h>

int my_double_ls(const char *name)
{
	struct dirent *d;
	DIR *dp;

	if ((dp=opendir(name))==NULL) 
		return -1;
	printf("\n");
	while(d=readdir(dp)){
		if(d->d_ino !=0)
			printf("%s\t", d->d_name);
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
		my_double_ls(string);
	}
	else if(argc==2)
	{
		if(argv[1]=="-l")
			my_double_ls(string);
		if(argv[1]=="-al")	
			my_double_ls(string);
		if(argv[1][0]!='-')
			my_double_ls(argv[1]);
	}
	else if(argc==3)
	{
		if(argv[1]=="-l")
			my_double_ls(argv[2]);
		if(argv[1]=="-al")	
			my_double_ls(argv[2]);
	}
	return 0;
}
