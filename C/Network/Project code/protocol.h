#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <time.h>

#define PORT 7777  
#define BUFSIZE 1024

#define SUCCES		1
#define TIMEOUT 	2
#define LOSTDATA	3
#define WSOVER		4

typedef struct data{
	int type;		//Ÿ�Ӿƿ�, �ս� ��Ȳ ����
	int data;		//������
	int winsize;	//������ ������
	int crival;		//�Ӱ谪
}Data;
