C=gcc

CFLAGS=

OBJS=main.o addnum.o subnum.o multnum.o divnum.o

LIBS=

all:	cul

cul:	$(OBJS)
	$(CC) $(CFLAGS) -o cul $(OBJS) $(LIBS)

main.o:	main.c
	$(CC) $(CFLAGS) -c main.c

addnum.o:	addnum.c
	$(CC) $(CFLAGS) -c addnum.c

subnum.o:	subnum.c
	$(CC) $(CFLAGS) -c subnum.c

multnum.o:	multnum.c
	$(CC) $(CFLAGS) -c multnum.c

divnum.o:	divnum.c
	$(CC) $(CFLAGS) -c divnum.c

clean:
	rm -f $(OBJS) cul core

