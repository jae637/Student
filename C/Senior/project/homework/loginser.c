#include <sys/types.h>
#include <sys/socket.h>
#include <sys/shm.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <algorithm>
#include <stdlib.h>
#include <string.h>
#include <queue>
#include <pthread.h>
#include <mutex>
#include "protocol.h"

using namespace std;
#define PORT "9000"
#define BUFSIZE 254
#define MSG_SIZE 1024

struct Client{
	char name[MAX_ID_SIZE];
	int sock;
	int state;	//play or ready or nothing
	bool bConnected;
};

Client _users[MAX_USER];

int cur_count = 0;

int server, client;
char input[BUFSIZE];

void InitializeServer(char*);
void SendIDMissPacket(int cl){
	sc_packet_put_player* packet;
	copy(_users[cl].name, _users[cl].name + BUFSIZE, packet->name);
	packet->type = SC_ID_MISS;
	packet->size = sizeof(packet);
	send(_users[cl].sock, reinterpret_cast<char*>(packet),packet->size,0); // size problem?
}

void SendIDSucPacket(int cl){
	sc_packet_put_player* packet;
	copy(_users[cl].name, _users[cl].name+BUFSIZE, packet->name);
	packet->type = SC_LOGIN_SUC;
	packet->size = sizeof(packet);
	send(_users[cl].sock, reinterpret_cast<unsigned char*>(packet), packet->size,0);
}

void* LoginPacket(void* arg){
	int* cl = (int*)arg;
	char buf[BUFSIZE];
	recv(_users[*cl].sock,buf, BUFSIZE, 0);
	unsigned char* packet = (unsigned char*)buf;
	switch((int)packet[1]){
	case CS_LOGIN:{
	      cs_packet_login* mypacket = (cs_packet_login*)packet;
	      ifstream inFile("data.txt");
	      char id[MAX_ID_SIZE], pw[MAX_ID_SIZE];
	      while(inFile>>id>>pw){
		      if(id == mypacket->name){
			if(pw == mypacket->pw){
				SendIDSucPacket(*cl);
				break;
			}
			else{
				SendIDMissPacket(*cl);
				break;
			}
		      }
		      else{
			      SendIDMissPacket(*cl);
			      break;
		      }
	      }
	      break;
	    }
	case CS_SIGN:{
	      cs_packet_login* mpacket = (cs_packet_login*)packet;
	      ofstream outFile("data.txt", ios::app);
	      outFile<<mpacket->name<<' '<<mpacket->pw<<endl;

	      break;
	      }
	}
}
void* ProcessPacket(void* arg){
	int* cl = (int*)arg;
	
}

void* AcceptThread(void* arg){

	cout<<"Wait Connection"<<endl;
	char buf[BUFSIZE];
	char temp[20];
	int len;
	memset(buf, 0x00, sizeof(buf));
	struct sockaddr_in clientAddr;
	len = sizeof(client);
	pthread_t packet_thread;
	while(1){
		int i, new_client_id;
		for(int i = 0; i<MAX_USER; ++i){
			if(_users[i].bConnected == false)
				new_client_id = i;
		}
		_users[new_client_id].sock = accept(server, (struct sockaddr*)&clientAddr,(socklen_t*)&len);

		if(client <0){
			perror("Accept Error!");
			exit(0);
		}

		inet_ntop(AF_INET, &clientAddr.sin_addr.s_addr, temp, sizeof(temp));
		_users[new_client_id].bConnected = true;
		cout<<"--Client Connected--"<<endl;

		if(pthread_create(&packet_thread, NULL, LoginPacket, (void*)&new_client_id) == -1){
			perror("Packet Thread Error!");
			exit(0);
		}
		pthread_join(packet_thread, NULL);
	}
}

int main(int argc, char* argv[]){
	
	pthread_t accept_thread;
	int res;
	char temp[20];
	int msg_size;
	int arg = 1;
	if(argc != 2){
	
		exit(0);
	}
	
	InitializeServer(argv[1]);

	res = pthread_create(&accept_thread, NULL, &AcceptThread,NULL);
	if(res < 0){
		perror("Thread Error!");
		exit(0);
	}

	pthread_join(accept_thread, NULL);

	close(server);
	return 0;

}


void InitializeServer(char* argv){
	
	cout<<"Initialize Server"<<endl;
	if((server = socket(AF_INET, SOCK_STREAM, 0)) == -1){
		perror("Socket Error!");
		exit(0);
	}

	struct sockaddr_in serverAddr;
	memset(&serverAddr, 0x00, sizeof(serverAddr));
	serverAddr.sin_family = AF_INET;
	serverAddr.sin_addr.s_addr = htonl(INADDR_ANY);
	serverAddr.sin_port = htons(atoi(PORT));

	if(bind(server, (struct sockaddr*)&serverAddr, sizeof(serverAddr))<0){
		perror("Bind Error!");
		exit(0);
	}

	if(listen(server, 10)< 0){
		perror("Listen Error!");
		exit(0);
	}

	cout<<"Initializing End"<<endl;
}

void CloseServer(){
	close(server);
}

