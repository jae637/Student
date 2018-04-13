C=gcc

CFLAGS=

OBJS=calc.o addnum.o subnum.o multnum.o divnum.o

LIBS=

all:	calc mainV2

mainV2:	mainV2.c
	$(CC) $(CFLAGS) -o mainV2 mainV2.c $(LIBS)

calc:	$(OBJS)
	$(CC) $(CFLAGS) -o calc $(OBJS) $(LIBS)

calc.o:	calc.c
	$(CC) $(CFLAGS) -c calc.c

addnum.o:	addnum.c
	$(CC) $(CFLAGS) -c addnum.c

subnum.o:	subnum.c
	$(CC) $(CFLAGS) -c subnum.c

multnum.o:	multnum.c
	$(CC) $(CFLAGS) -c multnum.c

divnum.o:	divnum.c
	$(CC) $(CFLAGS) -c divnum.c

clean:
	rm -f $(OBJS) calc core mainV2

