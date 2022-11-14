%{
    #include<stdio.h>
    extern int yylex(); 
    extern int yywrap(); 
    extern int yyparse(); 
%}

%token WH IF DO FOR OP CP OCB CCB CMP SC ASG ID NUM COMMA OPR

%%
start: swh | mhw | dowh | sif | mif; 

swh: WH OP cmpn CP stmt {printf("VALID SINGLE STATEMENT WHILE LOOP\n");};

mhw: WH OP cmpn CP OCB stmllist CCB {printf("VALID MULTI STATEMENT WHILE LOOP\n");};

dowh: DO OCB stmt CCB WH OP cmpn CP SC {printf("VALID DO WHILE LOOP\n");};

sif: IF OP cmpn CP stmt {printf("VALID SINGLE STATEMENT IF BLOCK\n");};

mif: IF OP cmpn CP OCB stmllist CCB {printf("VALID MULTI STATEMENT IF\n");};

cmpn: ID CMP ID | ID CMP NUM; 

stmt: ID ASG ID OPR ID SC | ID ASG ID OPR NUM SC | ID ASG NUM OPR ID SC | ID ASG NUM OPR NUM SC | ID ASG ID SC | ID ASG NUM SC | start{printf("NESTED INSIE A ");}

stmllist: stmt stmt|stmllist;

%% 

int yyerror(char *str){ 
    printf("%s", str); 
}

void main(){ 
    yyparse(); 
}



