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
	int type;		//타임아웃, 손실 상황 전달
	int data;		//데이터
	int winsize;	//윈도우 사이즈
	int crival;		//임계값
}Data;
