#include <stdio.h>
#include <string.h>

struct rank_list {
	char name[20];
	int  score;
};

int main(void)
{
	FILE * fp;
	char   input_name[20], tmp_name[20];
	int    input_score, tmp_score;
	int    rank_len;
	struct rank_list Rank_List[10];

	int i;

	// Read Rank File
	fp = fopen("Rank", "a+b");
	i = 0;
	while(1) {
		fread( &Rank_List[i], sizeof(Rank_List[i]), 1, fp );
		if( feof(fp) ) break;
		i++;
		rank_len = i;
	}
	fclose(fp);

	// Input User Name and Score
	printf("name  : ");
	scanf ("%s", input_name  );
	printf("score : ");
	scanf ("%d", &input_score);

	// Sort New Score
	for(i=0;i<rank_len;i++) {
		if(Rank_List[i].score < input_score) {
			tmp_score = Rank_List[i].score;
			strcpy(tmp_name, Rank_List[i].name);
			Rank_List[i].score = input_score;
			strcpy(Rank_List[i].name, input_name);
			input_score = tmp_score;
			strcpy(input_name, tmp_name);
		}
	}
	if(i < 10) {
		Rank_List[i].score = input_score;
		strcpy(Rank_List[i].name, input_name);
	}

	// Write included New Score
	fp = fopen("Rank", "wb");
	printf("\nNow Ranking \nRank\t\tName\t\tScore\n");
	for(i=0;i<=rank_len;i++) {
		if( i == 10 ) break;
		fwrite( &Rank_List[i], sizeof(Rank_List[i]), 1, fp );
		printf("%d\t\t%s\t\t%d\n", i+1, Rank_List[i].name, Rank_List[i].score);
	}

	fclose(fp);
	return 1;
}
