#include<stdio.h>
#include<stdlib.h>
#include<netdb.h>
#include<sys/types.h>
#include<sys/socket.h>
#define IPNUM 192.168.42.126


int main(){
	int sd,ns;
	struct cs_packet_login id;
	int select //MENU select option
	struct sockaddr_in sin;

	if((sd==sockket(AF_INET,SOCK_STREAM,0))==-1{
		perror("socket");
		exit(1);
	}
	
	memset((char*)&sin, '\0', sizeof(sin));
	sin.sin_family = AF_INET;
	sin.sin_port = htons(PORTNUM);
	sin.sin_addr.s_addr = inet_addr(IPNUM);
	
	if (ns=connect(sd, (struct sockaddr *)&sin, sizeof(sin)){
		perror("connect");
		exit(1);
	}

	printf("MENU(1:LOGIN,2:SIGN) :");
	scanf("%d",select);

	if(select==1){
		printf("ID:");
		scanf("%s",id.name);
		printf("PASSWORD:");
		scanf("%s",id.ps);
		if(send(ns,id,sizeof(struct sockaddr_in)==-1){
		perror("send");
		exit(1);
		}
	}
	else if(select ==2){
		printf("ID:");
		scanf("%s",id.name);
		printf("PASSWORD:");
		scanf("%s",id.ps);
		if(send(ns,id,sizeof(struct sockaddr_int)==-1){
			perror("connect");
			exit(1);
		}
	}
	else
		printf("Program Exit");
	
	close(sd);
}
