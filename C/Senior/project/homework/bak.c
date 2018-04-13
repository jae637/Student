#include<stdio.h>
#include<stdlib.h>
#include<netdb.h>
#include<string.h>
#include<fcntl.h>
#include<unistd.h>
#include<sys/types.h>
#include<sys/socket.h>
#include<netinet/in.h>
#include<arpa/inet.h>
#include "protocol.h"

#define PORTNUM 9000
#define BUFSIZE 254
#define IPNUM "127.0.0.1"

int main(int args, char* agrs[]){
	int sd,ns;
	int select; //MENU select option
	int selingame;	//GAME MENU select option
	int i,j,k,s,b;
	char buf[BUFSIZE];
	char answer[LEVEL];
	char solve[LEVEL];
	struct sc_packet_start* chk;
	struct sc_packet_marking marking;
	struct cs_packet_login usrid,insertid;	//check ID struct
	struct cs_packet_room room;		//create or join
	struct cs_packet_answer an;
	struct sockaddr_in sin;

	memset((char*)&sin, '\0', sizeof(sin));
	sin.sin_family = AF_INET;
	sin.sin_port = htons(PORTNUM);
	sin.sin_addr.s_addr = inet_addr(IPNUM);

	if((sd=socket(PF_INET,SOCK_STREAM,0))==-1){
		perror("socket");
		exit(1);
	}

	if(connect(sd, (struct sockaddr *)&sin, sizeof(sin))){
		perror("connect");
		exit(1);
	}
	while(1){
		printf("MENU(1:LOGIN,2:SIGN,3:EXIT) :");
		scanf("%d",&select);

		if(select==1){
			printf("ID:");
			scanf("%s",insertid.name);
			printf("PASSWORD:");
			scanf("%s",insertid.pw);
			insertid.type=(char)CS_LOGIN;
			if(send(sd,&insertid,sizeof(struct cs_packet_login),0)==-1){
				perror("send");
				exit(1);
			}
			recv(sd,buf,BUFSIZE,0);
			unsigned char* packet = (unsigned char*)buf;
			while((int)packet[1]==SC_LOGIN_SUC){
				printf("GAME MENU\n");
				printf("1.CREATE ROOM\n");
				printf("2.ENTER ROOM\n");
				printf("3.QUIT\n");
				printf("Select :");
				scanf("%d",&selingame);
				if (selingame ==1){
					room.type=(char)CS_CREATE_ROOM;
					if(send(sd,&room,sizeof(struct cs_packet_room),0)==-1){
						perror("send");
						exit(1);
					}
					printf("Wait a minute\n");
				}		
				else if (selingame ==2){
					room.type=(char)CS_ENTER_ROOM;
					if(send(sd,&room,sizeof(struct cs_packet_room),0)==-1){
						perror("send");
						exit(1);
					}
				}
				else{
					break;
				}
				memset(buf, '\0', sizeof(buf));

				recv(sd,buf,BUFSIZE,0);
				chk = (struct sc_packet_start*)buf;
				strcpy(solve,chk->quiz);
				printf("%s",solve);
				for(i=0;i<20;i++){
					printf("%d:",i+1);
					scanf("%s",answer);
					if(strcmp(solve,answer)==0)
					{
						printf("HomeRun~~! Your turn is %d",i+1);
						an.turn=i+1;
						if(send(sd,&an,sizeof(struct cs_packet_answer),0)==-1){
							perror("send");
							exit(1);
						}
						break;
					}
					else{
						s=0;
						b=0;
						for(j=0;j<3;j++){
							for(k=0;k<3;k++){
								if(answer[j]==solve[k]){
									if(j==k){
										s+=1;
										break;
									}
									b+=1;
								}
							}
						}
						printf("%d Strike / %d Ball! \n",s,b);
					}
				}
				memset(buf, '\0', sizeof(buf));
				
				recv(sd,buf,BUFSIZE,0);
				packet = (unsigned char*)buf;
				printf("Winner is %s",packet[2]);
			}
		}
		else if(select ==2){
			printf("Create ID:");
			scanf("%s",insertid.name);
			printf("Create PASSWORD:");
			scanf("%s",insertid.pw);
			insertid.type=(char)CS_SIGN;
			if(send(sd,&insertid,sizeof(struct cs_packet_login),0)==-1){
				perror("connect");
				exit(1);
			}
			printf("Complete!\n");
		}
		else{
			printf("Program Exit\n");
			break;
		}
		sleep(1000);
	}
	close(sd);

	return 0;
}
