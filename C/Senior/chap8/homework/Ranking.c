#include <stdio.h>
#include <string.h>

int main(void)
{
	FILE * fp;

	int i;


	// Write included New Score
	fp = fopen("Rank", "w");
	printf("\nNow Ranking \nRank\t\tName\t\tScore\n");
	for(i=0;i<=rank_len;i++) {
		if( i == 10 ) break;
		fwrite( &Rank_List[i], sizeof(Rank_List[i]), 1, fp );
		printf("%d\t\t%s\t\t%d\n", i+1, Rank_List[i].name, Rank_List[i].score);
	}

	fclose(fp);
	return 1;
}
