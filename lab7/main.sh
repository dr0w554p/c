flex lab7.l 
yacc -d lab7.y 
gcc lex.yy.c y.tab.c -lm

# while(a<b) a=a+1;

# if(a<21) a=a*21;

# for(i=0;i<5;i++) a=a/10

# while(a<b){a=a+10;b=b+10;}