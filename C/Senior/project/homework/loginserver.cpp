#include <sys/types.h>
#include <sys/socket.h>
#include <sys/shm.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <iostream>
#include <fstream>
#include <algorithm>
#include <stdlib.h>
#include <string.h>
#include <queue>
#include <pthread.h>
#include <mutex>
#include <vector>

#include "protocol.h"

using namespace std;
#define PORT "9000"
#define GAMEPORT 9001
#define BUFSIZE 1024
#define MSG_SIZE 1024
#define IP "127.0.0.1"

struct Client{
	char name[MAX_ID_SIZE];
	int sock;
	int state;	//play or ready or nothing
	bool bConnected;
	int turn{0};
	int room;
	Client(){bConnected = false;}
	Client& operator=(const Client& c){
		strcpy(name, c.name);
		sock = c.sock;
		state = c.state;
		bConnected = c.bConnected;
		return *this;
	}
};

struct PipeData{
	char type{0};
	bool t;
	char name[MAX_ID_SIZE]{0};
	int turn{0};
};


class Room{
public:
	Room(){}
	~Room(){}
	char player1[MAX_ID_SIZE]{0};
	char player2[MAX_ID_SIZE]{0};
	char quiz[LEVEL]{0};
	int state{0};
	int turn1{0}, turn2{0};
};

pthread_mutex_t rlock = PTHREAD_MUTEX_INITIALIZER;

class RoomManager{
	vector<Room> rooms;	
public:
	void PrintRoomInfo(){
		int n = 0;
		for(int i = 0; i<rooms.size(); ++i){
			Room d = rooms[i];
			cout<<"--------------------"<<endl;
			cout<<"room : "<<n++<<endl;
			cout<<"player1 : "<<d.player1<<endl;
			cout<<"player2 : "<<d.player2<<endl;
			cout<<"quiz : "<<d.quiz<<endl;
			cout<<"state : "<<d.state<<endl;
			cout<<" t1, t2 : "<<d.turn1<<","<<d.turn2<<endl;
		}
	}

	char* GetQuizByNum(int cl){
		return rooms[cl/2].quiz;
	}
	void CreateRoom(char name[MAX_ID_SIZE]){
		pthread_mutex_lock(&rlock);
		rooms.push_back(Room());
		int n = rooms.size()-1;
		strcpy(rooms[n].player1, name);
		rooms[n].state = R_READY;
		pthread_mutex_unlock(&rlock);
		PrintRoomInfo();
	}

	char* GetQuiz(int cl){
		int n = cl/2;
		cout<<"quiz romn :"<<n<<endl;
		cout<<rooms[n].quiz[0]<<endl;
		cout<<rooms[n].quiz<<endl;
		return rooms[n].quiz;
	}

	int GetRoomNumber(char name[MAX_ID_SIZE]){
		for(int i = 0; i<rooms.size(); ++i){
			if(!strcmp(name, rooms[i].player1) || !strcmp(name, rooms[i].player2))
				return i;
		}
		return -1;
	}

	char* GetEnemyName(char name[MAX_ID_SIZE]){
		int n = GetRoomNumber(name);
		if(!(strcmp(name, rooms[n].player1)))
			return rooms[n].player2;
		else
			return rooms[n].player1;
	}



	char* GetRandomNumber(){
		int n[LEVEL] = { rand()%10, rand()%10, rand()%10};
		char* c = new char[3];
	
		sprintf(c,"%d %d %d", n[0], n[1], n[2]);
		
		return c;
	}

	void SaveTrun(char name[MAX_ID_SIZE], int turn){
		cout<<"save name"<<name;
		int n = GetRoomNumber(name);
		pthread_mutex_lock(&rlock);
		if(!strcmp(rooms[n].player1, name))
			rooms[n].turn1 = turn;
		else
			rooms[n].turn2 = turn;
		pthread_mutex_unlock(&rlock);
	}

	int GetWinner(int cl){
		int n = cl/2;
		if(cl%2)
			cl-=1;

		if(rooms[n].turn1<rooms[n].turn2)
			return cl;
		else
			return cl+1;
			
	}

	int EnterRoom(char name[MAX_ID_SIZE]){

				pthread_mutex_lock(&rlock);
		int n = 0;
		cout<<"enteroom name : "<<name<<endl;
		cout<<"room size : " <<rooms.size()<<endl;
		PrintRoomInfo();
		for(int i = 0; i<rooms.size(); ++i){
			cout<<"before state check"<<endl;
			if(rooms[i].state == R_READY){
				cout<<"before name copy"<<endl;
				strcpy(rooms[i].player2, name);
				cout<<R_PLAY<<endl;
				rooms[i].state = R_PLAY;
				cout<<"before getnumber"<<endl;
				strcpy(rooms[i].quiz, GetRandomNumber());
				PrintRoomInfo();
				cout<<"room entered"<<endl;	
				pthread_mutex_unlock(&rlock);
				return n;
			}
			++n;
		}
		cout<<"no room"<<endl;
		
				pthread_mutex_unlock(&rlock);
				PrintRoomInfo();
		return n;
	}

	char* GetWinnerName(char name[MAX_ID_SIZE]){
		int n = GetRoomNumber(name);
		if(rooms[n].turn1<rooms[n].turn2){
			return rooms[n].player1;
		}
		else return rooms[n].player2;
	}

	void DeleteRoom(int i){}
};


struct Share{
	int current_player_count{0};
	int game;
	int turn1{0}, turn2{0};
	bool isLogin{false};
	RoomManager room;
	Client clients[MAX_USER];
	/*void share(Client c[MAX_USER]){
		for(int i = 0; i<MAX_USER; ++i)
			clients[i] = c[i];
	}

	int GetClientNumByName(char name[MAX_ID_SIZE]){
		for(int i = 0; i<current_player_count; ++i){

			if(!strcmp(clients[i].name, name))
				return i;
		}
		return -1;
	}*/
};



struct Share* sh;

int pp[2];
int pp2[2];
int cur_count = 0;

int server, client;
int game_server;
char input[BUFSIZE];


pthread_mutex_t login_lock = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t lock = PTHREAD_MUTEX_INITIALIZER;
char* GetRandomNumber();
void InitializeServer(char*);
void InitializeGameServer();
void CloseServer();
void SendIDMissPacket(int cl){
	sc_packet_put_player packet;
	strcpy(packet.name, sh->clients[cl].name);
	packet.type = SC_ID_MISS;
	packet.size = sizeof(packet);
	send(sh->clients[cl].sock, reinterpret_cast<char*>(&packet),sizeof(packet),0); // size problem?
	cout<<"IDMiss Send"<<endl;
}

void SendIDSucPacket(int cl){
	cout<<"----from send ---- "<<endl;
	cout<<cl<<","<<sh->clients[cl].sock<<endl;
	sc_packet_put_player packet;
	strcpy(packet.name, sh->clients[cl].name);
	packet.type = SC_LOGIN_SUC;
	packet.size = sizeof(packet);
	send(sh->clients[cl].sock, reinterpret_cast< char*>(&packet), sizeof(packet),0);
	cout<<"LoginSuc Send"<<endl;
}

void SendStartPacket(int cl){
	//start message with qui
	cout<<"start packet start"<<endl;
	sc_packet_start packet;
	packet.size = sizeof(packet);
	packet.type = SC_START;
	cout<<"before quiz"<<endl;
//	cout<<sh->room.GetQuizByNum(0)<<endl;
//	strcpy(packet.quiz,sh->room.GetQuizByNum(0));
//	strcpy(packet.quiz, quiz);	
	sprintf	(packet.quiz, "%d %d %d", rand()%4, rand()%3+4, rand()%3+7);
	cout<<"---------------------quiz answer : "<<packet.quiz<<endl;	
	packet.state = R_PLAY;
	packet.player_count = 2;
	cout<<"before send"<<endl;
	send(sh->clients[cl].sock, reinterpret_cast<char*>(&packet), sizeof(packet), 0);

	send(sh->clients[cl-1].sock, reinterpret_cast<char*>(&packet), sizeof(packet), 0);

	cout<<"Start Send"<<endl;

}

void SendWinnerPacket(int cl, char name[MAX_ID_SIZE]){
	sc_packet_winner packet;
	packet.size = sizeof(packet);
	packet.type = SC_WINNER;
	cout<<name<<endl;
	strcpy(packet.name, name);
	send(sh->clients[cl].sock, reinterpret_cast<char*>(&packet), sizeof(packet), 0);
	send(sh->clients[cl+1+(-2)*(cl%2)].sock, reinterpret_cast<char*>(&packet), sizeof(packet), 0);
	cout<<"winner send"<<endl;
}

char* GetRandomNumber(){
	int seed = rand()%4;
	int base = rand()%2+4;
	char* c = new char();
	sprintf(c, "%d %d %d", base, base-seed, base+seed);
	return c;
}


void LoginPacket(void* arg){
	int* cl = (int*)arg;
	char buf[BUFSIZE];
	cout<<"loginpacket"<<endl;
	int res;

	while(1){
	memset(buf, 0x00, BUFSIZE);
	res = recv(sh->clients[*cl].sock,buf, BUFSIZE, 0);
	if(res <= 0){
		close(sh->clients[*cl].sock);
		perror("login packet recv error");
		exit(0);
	}
	 cout<<"login recv"<<endl;
	unsigned char* packet = (unsigned char*)buf;
	cout << "size :" << (int)packet[0] << endl;

	switch((int)packet[1]){
	case CS_LOGIN:{
	      cs_packet_login* mypacket = (cs_packet_login*)packet;
	      ifstream inFile("data.txt");
	      char id[MAX_ID_SIZE], pw[MAX_ID_SIZE];
	      while(inFile>>id>>pw){
		      cout<<id<<","<<pw<<":"<<mypacket->name<<","<<mypacket->pw<<endl;
		      if(!strcmp(id, mypacket->name)){
			if(!strcmp(pw,mypacket->pw)){

				strcpy(sh->clients[*cl].name , id);
				char a[3] = {2,5,1};
	//			SendStartPacket(*cl,a );
				SendIDSucPacket(*cl);
		//		int n = sh->room.GetWinner(*cl);
		//		SendWinnerPacket(*cl, sh->clients[n].name);
				cout<<"---- "<<*cl<<","<<sh->clients[*cl].sock<<endl;
	//			login_checker = false;			
				sh->clients[*cl].bConnected = true;
				sh->game = *cl;
				sh->isLogin = true;
				
				cout<<"game : "<< sh->game<<endl;
				break;
			}
			else{
				SendIDMissPacket(*cl);
				break;
			}
		      }
		      else{
			      continue;
		      }
	      }
	      break;
	    }
	case CS_SIGN:{
	      cs_packet_login* mpacket = (cs_packet_login*)packet;

	      pthread_mutex_lock(&login_lock);
	      ofstream outFile("data.txt", ios::app);
	      outFile<<mpacket->name<<' '<<mpacket->pw<<endl;
	      pthread_mutex_unlock(&login_lock);

		cout<<mpacket->name<<"   "<<mpacket->pw<<endl;
	      break;
	      }
	case CS_CREATE_ROOM:
		     {
			     cs_packet_room* mypacket = (cs_packet_room*)packet;
			     PipeData d;
			     d.type = mypacket->type;
			     strcpy(d.name, sh->clients[*cl].name);
				
			    
			     cout<<"create room recv"<<endl;
			     pthread_mutex_lock(&login_lock);
			     write(pp[1], reinterpret_cast<char*>(&d),sizeof(d));
			     pthread_mutex_unlock(&login_lock);
				break;	
		     }
		     
	case CS_ENTER_ROOM:
		     {
			     cs_packet_room* mypacket = (cs_packet_room*)packet;
			     PipeData d;
			     d.type = mypacket->type;
			     strcpy(d.name, sh->clients[*cl].name);
			     cout<<d.name<<","<<d.type<<"date packaged"<<endl;
			     cout<<"enter room recv"<<endl;
			     pthread_mutex_lock(&login_lock);
			     write(pp[1], reinterpret_cast<char*>(&d), sizeof(d));
			     char pipe_buf[BUFSIZE];
			     memset(pipe_buf, 0x00, sizeof(pipe_buf));
				
			     read(pp2[0], pipe_buf, sizeof(pipe_buf));
			     int roomnum = (int)pipe_buf[0];
			     sh->clients[*cl].room = roomnum;
			     cout<<"room num"<<roomnum<<endl;
			    // if((bool)pipe_buf[1]){
				    
				     
			//	sh->clients[*cl].enemy =sh->GetClientNumByName(sh->room.GetEnemyName(sh->clients[*cl].name));
				SendStartPacket(*cl);
				pthread_mutex_unlock(&login_lock);
				//
			    // }
			     cout<<"Send Start"<<endl;
			     break;
			}
	case CS_ANSWER:
		     {
			     cs_packet_answer* mypacket = (cs_packet_answer*)packet;
			     PipeData d;
			     d.type = mypacket->type;
			     strcpy(d.name, sh->clients[*cl].name);
			     
			     pthread_mutex_lock(&login_lock);

			    
			     if((*cl)%2)
				     sh->turn2 = mypacket->turn;
			     else
				     sh->turn1 = mypacket->turn;
				if(sh->turn1) cout<<"turn1: "<<sh->turn1<<endl;
				if(sh->turn2) cout<<"turn2: "<<sh->turn2<<endl;
			     if(sh->turn1 != 0 && sh->turn2 != 0){
				if(sh->turn1<sh->turn2){
					int cal_id = *cl + (-1)*(*cl%2);
					if(*cl%2 == 1){
						SendWinnerPacket(*cl-1, sh->clients[*cl-1].name);
					}
					else
						SendWinnerPacket(*cl, sh->clients[*cl].name);
				}
				else{
					if(*cl%2 == 1)
						SendWinnerPacket(*cl, sh->clients[*cl].name);
					else
						SendWinnerPacket(*cl+1, sh->clients[*cl+1].name);
				}

				sh->turn1 = 0;
				sh->turn2 = 0;
			     }
			     pthread_mutex_unlock(&login_lock);
			     //send who is winner
			     break;
		     }
	default:{
			     cout<<"no "<<endl;
			     break;
		     }
	}
	
	}
	close(sh->clients[*cl].sock);
	cout<<"----client closed------"<<endl;
}
void* ProcessPacket(void* arg){
	int* cl;
	char buf[BUFSIZE];
	memset(buf, 0x00, sizeof(buf));
	cout<<"process packet"<<endl;
	int res;
	//SendIDSucPacket(*cl);
	int len = sizeof(sh->clients[*cl].sock);
	
	{
	
	
	
	unsigned char* packet = (unsigned char*)arg;
	PipeData* d = (PipeData*)packet;
	
	switch((int)packet[0]){
	case CS_CREATE_ROOM:
		{
			cout<<"create room start"<<endl;
			sh->room.CreateRoom(d->name);
			cout<<"room create success"<<endl;
			break;
		}
	case CS_ENTER_ROOM:
		{
			cout<<"enter room!!!!!"<<endl;
			int n;
			n = (char)sh->room.EnterRoom(d->name);
			cout<<"enter room success"<<endl;
			PipeData dd;
			dd.type = n;
			pthread_mutex_lock(&login_lock);
			write(pp2[1], reinterpret_cast<char*>(&dd), sizeof(dd));
			pthread_mutex_unlock(&login_lock);
		//	send(sh->clients[*cl].sock, reinterpret_cast<char*>(&packet),sizeof(packet),0);
			break;
		}
	case CS_ANSWER:
		{
			cout<<"answer room!!!"<<endl;
			

			break;
		}
	case CS_OUT:
		{
			break;
		}
	}
	sleep(1);
	}
	cout<<"proc packet end---"<<endl;
}

void AcceptThread(void* arg){


	char buf[BUFSIZE];
	char temp[20];
	int len;
	memset(buf, 0x00, sizeof(buf));
	struct sockaddr_in clientAddr;
	len = sizeof(client);
	pthread_t packet_thread;

	while(1){
		cout<<"Wait Connection"<<endl;
		int i, new_client_id;
		new_client_id = sh->current_player_count++;
		cout<<"new client id is : "<<new_client_id;

		sh->clients[new_client_id].sock = accept(server, (struct sockaddr*)&clientAddr,(socklen_t*)&len);

		if(sh->clients[new_client_id].sock <0){
			close(sh->clients[new_client_id].sock);
			perror("Accept Error!");
			exit(0);
		}

		inet_ntop(AF_INET, &clientAddr.sin_addr.s_addr, temp, sizeof(temp));
	
		cout<<"--Client Connected--"<<temp<<endl;
		switch(fork()){
			case 0:
		LoginPacket((void*)&new_client_id);
		}

		cout<<"login thread die"<<endl;
	}
	//pthread_join(packet_thread, NULL);
}

/*void* GameConnect(void* arg){
	cout<<"Game Wait Connection"<<endl;
	char temp[20];
	struct  sockaddr_in clientAddr;
	pthread_t packet_thread;
	int len = sizeof(client);
	while(len != 0){
		int i, new_client_id;

		for(int i = 0; i<MAX_USER; ++i){
			if(_users[i].bConnected == false)
				new_client_id = i;
		}

		_users[new_client_id].sock = accept(server, (struct sockaddr*)&clientAddr, (socklen_t*)&len);
		if(_users[new_client_id].sock < 0){
			perror("Game Connect Error");
			exit(0);
		}
		inet_ntop(AF_INET, &clientAddr.sin_addr.s_addr, temp, sizeof(temp));
		cout<<"Connected to game"<<endl;

		if(pthread_create(&packet_thread, NULL, ProcessPacket, (void*)&new_client_id) == -1){
			perror("Process Error");
			exit(0);
		}
		pthread_join(packet_thread, NULL);


	}
}*/

void PipeWork(void* a){
	cout<<"pipe working"<<endl;
	int res;
	char buf[BUFSIZE];
	pthread_t proc;
	static int before = 0;
	
	while(1){
		memset(buf, 0x00, BUFSIZE);
		read(pp[0], buf, sizeof(buf));
		
		cout<<"read arrived"<<endl;
		pthread_create(&proc, NULL, &ProcessPacket,(void*)&buf);
		pthread_join(proc, NULL);

		/*if(sh->isLogin){
			pthread_create(&proc, NULL, &ProcessPacket, (void*)&sh->game);
			pthread_join(proc, NULL);
			sh->isLogin = false;
		}*/
		sleep(1);

	}
}

int main(int argc, char* argv[]){
	int shmid;
	void* shared_memory = (void*)0;
	
	srand((unsigned int)getpid());

	shmid = shmget((key_t)1234, sizeof(struct Share), 0666|IPC_CREAT);
	shared_memory = shmat(shmid, (void*)0, 0);
	sh = (struct Share*)shared_memory;
	sh->game = 0;
	sh->current_player_count = 0;

	sh->isLogin = false;
	pipe(pp);
	pipe(pp2);
	pthread_t accept_thread, proc_thread;
	int res;
	int arg = 1;

	InitializeServer(argv[1]);
//	InitializeGameServer();

	switch(fork()){
	case 0:
	{
		cout<<"login going"<<endl;
		AcceptThread(NULL);
		cout<<"connected"<<endl;
	
		
		break;
	}
	default:
	{
		cout<<"game going"<<endl;
		PipeWork( NULL);
		cout<<"game connected"<<endl;
	}
	}
	
	CloseServer();

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

void InitializeGameServer(){
	cout<<"Initialize Game Server"<<endl;
	if((game_server = socket(AF_INET, SOCK_STREAM, 0))== -1){
		perror("game server sock error!");
		exit(0);
	}

	struct sockaddr_in serverAddr;
	memset(&serverAddr, 0x00, sizeof(serverAddr));
	serverAddr.sin_family = AF_INET;
	serverAddr.sin_addr.s_addr = inet_addr(IP);
	serverAddr.sin_port = htons(GAMEPORT);

	if(bind(game_server, (struct sockaddr*)&serverAddr, sizeof(serverAddr))<0){
		perror("game server bind error");
		exit(0);
	}

	if(listen(game_server, 10)<0){
		perror("Game server listen Error");
		exit(0);
	}


	cout<<"Game Server Initialize End"<<endl;
}


void CloseServer(){
	close(server);
	for(int i = 0 ; i<MAX_USER; ++i)
		close(sh->clients[i].sock);
}

