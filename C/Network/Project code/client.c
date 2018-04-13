#include "protocol.h"

/* argv[1]은 수와 점 표기의 IP 주소 */
int main(int argc, char *argv[]) {
	int sock;
	struct sockaddr_in serv_adr, from_adr;	// 소켓 프로그래밍 구조체
	socklen_t adr_sz;						// 소켓 프로그래밍 변수
	int i, len;		// 반복 변수, 소켓 변수
	char answ;		// Data 전송 플래그
	int tmp;		// rand변수
	int scnt = 1;	// 현재 데이터 진행 량
	int ws = 1;		// 윈도우 사이즈
	int th = 8;		// 임계값
	int snddata;	// 손실, 타임아웃 된 위치의 데이터
	int flag;		// 성공:1, 손실:2, 타임아웃:3 플래그
	Data sdata;		// 보내는 데이터 스트럭쳐
	Data rdata;		// 받는 데이터 스트럭쳐
	int ackc, wsch;	// 3ACK 플래그 변수
	int timec;		// 타임아웃 플래그 변수
	int savedata;

	// IP 지정
	if (argc != 2) {
		fprintf(stderr, "Usage: %s IP_address\n", argv[0]);
		exit(1);
	}

	/* 소켓 생성 */
	if ((sock = socket(AF_INET, SOCK_DGRAM, 0)) == -1) {
		perror("sock failed");
		exit(1);
	}
	memset(&serv_adr, 0, sizeof(serv_adr));
	serv_adr.sin_family = AF_INET;
	serv_adr.sin_addr.s_addr = inet_addr(argv[1]);
	serv_adr.sin_port = htons(PORT);
	adr_sz = sizeof(from_adr);

	sdata.winsize = ws;
	sdata.crival = th;

	//랜덤 함수 설정
	srand(time(NULL));

	//메인 메뉴
	while (1) {
		printf("현재 윈도우 사이즈 : %d\n", ws);
		printf("현재 임계값 : %d\n", th);
		printf("데이터를 전송하시겠습니까?(y/n) : ");
		gets(&answ);
		// 데이터 전송 승낙
		if (answ == 'y') {
			flag = 0;		//데이터 전송 성공 플래그
			//데이터 송신부
			for (i = 1; i <= ws; i++) {
				sdata.data = scnt;					//현재 입력된 데이터 부터 마저 진행
				tmp = rand() % 100;
				// 손실
				if (tmp > 95) {		//손실 확률
					sdata.type = LOSTDATA;
					if (flag == 0) {
						snddata = sdata.data;		//문제 데이터 저장
						flag = 1;					//손실 플래그
					}
					sendto(sock, &sdata, sizeof(sdata), 0, (struct sockaddr*)&serv_adr, sizeof(serv_adr));
					printf(" 송신 pkt : %d      (LT)\n", scnt);
					scnt++;
					continue;
				}
				tmp = rand() % 100;
				// 타임 아웃
				if (tmp > 95) {		// 타임 아웃 확률
					sdata.type = TIMEOUT;
					if (flag == 0) {
						snddata = sdata.data;		//문제 데이터 저장
						flag = 2;					//타임아웃 플래그
					}
					sendto(sock, &sdata, sizeof(sdata), 0, (struct sockaddr*)&serv_adr, sizeof(serv_adr));
					printf(" 송신 pkt : %d (TO)\n", scnt);
					scnt++;
					timec = 1;	//타임아웃으로 인한 임계값, 윈도우 사이즈 변하게 하는 플래그 on
					continue;
				}
				// 정상 진행
				else {
					sdata.type = SUCCES;
					sendto(sock, &sdata, sizeof(sdata), 0, (struct sockaddr*)&serv_adr, sizeof(serv_adr));
					printf(" 송신 pkt : %d\n", scnt);
					scnt++;
					continue;
				}
			}
			//윈도우 사이즈 만큼 보낸 뒤, 다 보냈다는 메세지
			sdata.type = WSOVER;
			sendto(sock, &sdata, sizeof(sdata), 0, (struct sockaddr*)&serv_adr, sizeof(serv_adr));

			// 손실, 타임아웃이 발생했을 경우
			if (flag == 1 || flag == 2) {
				scnt = snddata;	// 첫번째 문제발생 데이터 번째 로드
				flag = 0;		// 플래그 초기화
			}

			printf("\n");

			//데이터 수신부
			for (i = 1; i <= ws + 1; i++) {	//윈도우 사이즈 + 마지막 메세지
				len = recvfrom(sock, &rdata, sizeof(rdata), 0, (struct sockaddr*)&from_adr, &adr_sz);
				if (rdata.type == WSOVER)	//마지막 메세지
					break;
				if (rdata.type == LOSTDATA) {	// 손실 발생
					
				}
				if (rdata.type == TIMEOUT) {	// 타임아웃 발생
					timec = 1;
				}
				if(rdata.type==SUCCES) {		//타임아웃 시 수신 불가
					if (savedata == rdata.data)
						ackc++;
					printf(" 수신 ACK %d\n", rdata.data);
				}
				if (ackc >= 2) {
					//윈도우 사이즈 조절 명령
					wsch = 1;
				}
				savedata = rdata.data;
			}

			printf("\n");
			//타임아웃 윈도우, 임계값 변경
			if (timec == 1) {
				th = ws / 2;	// 임계값= 윈도우 사이즈/2
				ws = 1;		// 윈도우 사이즈 = 1
			}
			//3ACK 윈도우, 임계값 변경
			else if (wsch == 1) {
				// 명령 하달 시
				th = ws / 2;	// 임계값 사이즈 = 윈도우 사이즈 / 2
				if (ws == 1)	// 윈도우 사이즈 = 윈도우 사이즈 / 2
					ws = (ws + 1) / 2;		//윈도우 사이즈 0이 불가능
				else
					ws = ws / 2;
			}
			else {
				if (ws < th) {		// 임계값 미만 : 지수적 증가
					ws *= 2;
					if (ws > th)
						ws = th;
				}
				else if (ws >= th) {// 임계값 이상 : 선형적 증가
					ws += 1;
				}
			}
			timec = 0;			// 타임아웃 플래그 초기화
			wsch = 0;			// 3ACK 윈도우 사이즈 조절 명령 플래그 초기화
			ackc = 0;			// 3ACK 플래그 초기화
		}

		// 데이터 전송 거부
		else {
			break;
		}
		sdata.crival = th;
		sdata.winsize = ws;
	}
	close(sock);
	exit(1);
}
