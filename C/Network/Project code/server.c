#include "protocol.h"

int main()
{
	int sockfd;			//소켓 생성
	struct sockaddr_in servAddr;//	서버연결 소켓
	struct sockaddr_in clntAddr;//	클라이언트 소켓
	int clntLen;	// 클라이언트 소켓 연결 길이
	int recvLen;	// 리시브 데이터 길이량
	Data rdata;		// 리시브 데이터
	int set = 0;		// 문제가 걸린 숫자
	int flag;		// 손실, 정상 플래그

	/* 인터넷으로 연결된 프로세스들 간에 통신을 하고 UDP 방법을 이용하는 소켓을 생성 */
	if ((sockfd = socket(AF_INET, SOCK_DGRAM, 0)) == -1) {
		perror("sock failed");
		exit(1);
	}

	/* servAddr를 0으로 초기화 */
	memset(&servAddr, 0, sizeof(servAddr));
	/* servAddr에 IP 주소와 포트 번호를 저장 */
	servAddr.sin_family = AF_INET;
	servAddr.sin_addr.s_addr = htonl(INADDR_ANY);
	servAddr.sin_port = htons(PORT);

	/* sockfd 소켓에 주소 정보 연결 */
	if (bind(sockfd, (struct sockaddr*)&servAddr, sizeof(servAddr)) == -1) {
		perror("bind failed");
		exit(1);
	}

	/* 무한 반복 */
	while (1) {
		clntLen = sizeof(clntAddr);

		if ((recvLen = recvfrom(sockfd, &rdata, sizeof(rdata), 0, (struct sockaddr*)&clntAddr, &clntLen)) == -1) {
			perror("recvfrom failed");
			exit(1);
		}

		// 수신 받은 데이터 출력부 (타임아웃의 경우 수신은 받지만 송신이 제시간에 가지 않음)
		if (rdata.type == SUCCES || rdata.type == TIMEOUT)
			printf("수신 받은 데이터 값 : %d\n", rdata.data);
		// 정상
		if (rdata.type == SUCCES) {
			//손실난 패킷이 있었을 경우
			if (flag == 1) {
				rdata.data = set;	//손실나기 전 패키지의 ACK를 계속 보냄
			}
		}

		// 손실
		else if (rdata.type == LOSTDATA) {
			//중복 손실을 가림
			if (flag != 1)	//손실이 발생했을 경우
				set = rdata.data - 1;	//손실의 위치를 임시로 저장
			//손실이 발생했다라는 상태를 나타냄
			flag = 1;
			rdata.data = set;		//손실되기 전의 데이터를 전송
		}
		// 타임 아웃
		else if (rdata.type == TIMEOUT) {
			if (flag != 1)
				set = rdata.data - 1;
		}

		if (rdata.type == WSOVER) {		//마지막 메세지
			flag = 0;			//플래그 초기화
			rdata.data = set;	//정상적으로 받은 ACK 다음 넘버 까지
			printf("\n");
		}
		sendto(sockfd, &rdata, sizeof(rdata), 0, (struct sockaddr*)&clntAddr, sizeof(clntAddr));
	}
}
