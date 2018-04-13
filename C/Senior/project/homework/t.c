#include<stdio.h>
#include<stdlib.h>
#include<netdb.h>
#include<sys/types.h>
#include<sys/socket.h>
#include "protocol.h"

#define IPNUM "192.168.162.133"

int main(int args, char* agrs[]){
	int sd,ns;
	int select; //MENU select option
	struct cs_packet_login usrid;	//check ID struct
	struct sockaddr_in sin;

	memset((char*)&sin, '\0', sizeof(sin));
	sin.sin_family = AF_INET;
	sin.sin_port = htons(PORTNUM);
	sin.sin_addr.s_addr = inet_addr(IPNUM);

	if((sd==socket(AF_INET,SOCK_STREAM,0))==-1){
		perror("socket");
		exit(1);
	}

	if(connect(sd, (struct sockaddr *)&sin, sizeof(sin))){
		perror("connect");
		exit(1);
	}

	printf("MENU(1:LOGIN,2:SIGN) :");
	scanf("%d",select);

	if(select==1){
		printf("ID:");
		scanf("%s",usrid.name);
		printf("PASSWORD:");
		scanf("%s",usrid.pw);
		if(send(sd,&usrid,sizeof(struct cs_packet_login),0)==-1){
		perror("send");
		exit(1);
		}
	}
	else if(select ==2){
		printf("ID:");
		scanf("%s",usrid.name);
		printf("PASSWORD:");
		scanf("%s",usrid.pw);
		if(send(sd,&usrid,sizeof(struct cs_packet_login),0)==-1){
			perror("connect");
			exit(1);
		}
	}
	else
		printf("Program Exit");
	
	close(sd);
}
