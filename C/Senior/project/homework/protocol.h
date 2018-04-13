#pragma once

#define MAX_ID_SIZE	10
#define LEVEL		3
#define MAX_USER	10

#define R_FULL		1
#define R_PLAY		2
#define R_READY		3

#define CS_LOGIN	1	//
#define CS_SIGN		2	//
#define CS_LEVEL	3	
#define CS_CREATE_ROOM	4	//
#define CS_ENTER_ROOM	5

#define CS_ANSWER	6	//write answer
#define CS_OUT		7	//


#define SC_ID_MISS	1
#define SC_START	2
#define SC_QUIZ		3	//give quiz
#define SC_MARKING	4	//result of answer
#define SC_WINNER	5	//if someone's answer is correct
#define SC_LOGIN_SUC	6
#define SC_TURN		7
#define SC_NOT_TURN	8
#define SC_ROOM_SUC	9
#define SC_REMOVE	10

struct cs_packet_login{
	char size;
	char type;
	char name[MAX_ID_SIZE];
	char pw[MAX_ID_SIZE];
};

struct cs_packet_room{	//create or enter room you should define correct type
	char size;
	char type;
};

struct cs_packet_answer{
	char size;
	char type;
	int turn;
};

struct cs_packet_out{	
	char size;
	char type;
};

struct sc_packet_put_player{	//SC_LOGIN_SUC SC_ID_MISS login state suc or fail
	char size;
	char type;
	char name[MAX_ID_SIZE];
};

struct sc_packet_remove_player{	//SC_REMOVE when other player out
	char size;
	char type;
	char name[MAX_ID_SIZE];
};

struct sc_packet_start{	//SC_START when room count be 2 automatically this message send to , state 0 : ready state1 : full
	char size;
	char type;
	char quiz[3];
	int state; 
	int player_count;
};

struct sc_packet_marking{//marking of answer data is : S (S count) B (B count)
	char size;
	char type;
	char marking[4];
};


struct sc_packet_turn{	// SC_TURN, SC_NOT_TURN
	char size;
	char type;
};

struct sc_packet_winner{ //when someone say answer this message send to every clients of the room.
	char size;
	char type;
 	char name[MAX_ID_SIZE];
};
