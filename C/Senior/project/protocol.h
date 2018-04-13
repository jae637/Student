#pragma once

#define MAX_ID_SIZE	10
#define LEVEL		3
#define MAX_USER	10

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

struct cs_packet_login{
	BYTE size;
	BYTE type;
	WCHAR name[MAX_ID_SIZE];
	WCHAR pw[MAX_PW_SIZE];
}

struct cs_packet_room{
	BYTE size;
	BYTE type;
}

struct cs_packet_answer{
	BYTE size;
	BYTE type;
	WCHAR answer[LEVEL];
}

struct cs_packet_out{
	BYTE size;
	BYTE type;
}

struct sc_packet_put_player{
	BYTE size;
	BYTE type;
	WORD id;
}

struct sc_packet_remove_player{
	BYTE size;
	BYTE type;
	WORD id;
}

struct sc_packet_start{
	BYTE size;
	BYTE type;
	BYTE state; //room ready or play
	BYTE player_count;
}

struct sc_packet_marking{
	BYTE size;
	BYTE type;
	WCHAR marking[LEVEL];
}

struct sc_packet_winner{
	BYTE size;
	BYTE type;
	WORD id;
}
