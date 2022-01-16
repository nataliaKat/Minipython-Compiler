#Q1
def add(x,y):
   return x + y
print k
k =0

#Q2, Q3 We expect Errors in Line 7 and 11 and 13
ad2(1)
add2(1)
def add2(x, y=2):
    print x + y
ad2(1, 1)
add2(1, 1)
add2(1, 1, 1, 1 ,1)

#Q4
x="hello world"
print x+2

def add4(x,y):
   return x + y
k="hello world"
print add4(2,k)

#Q5
x5 =None + None
y5= 5 + None
z5= None + "hi"


#Q6
def add(x,y):
   return "hello world"
print add(2,1)+2

#Q7 We expect Errors in Line 39, 41, 45
def add7(x, y, z=0):
    print 4
def add7(x,y, z=1):
    print 4
def add7(x,y, z):
     print 4
def add7(x,y, z, k=0):
     print 4
def add7(x,y):
    print 4
