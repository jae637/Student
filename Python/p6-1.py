class MyPrime:
    primenum=[]

def is_prime(n):
    for i in range(2,n):
        if(n%i==0):
             return -1
    return n

def sieve(n):
    if n < 2:
        return []
    s = [0, 0] + [1] * (n - 1)
    for i in range(2, int(n**.5)+1):
        if s[i]:
            s[i*2::i] = [0] * ((n - i)//i)
    return [i for i, v in enumerate(s) if v]

prime=MyPrime()

sum=0
n=int(input("enter upper limit:"))


if n<=1:
    print("소수의 범위를 벗어납니다.")
    exit(1)

prime.primenum=sieve(n)
print(prime.primenum)

a=0
for a in prime.primenum:
    sum= sum+ a

print(sum)
