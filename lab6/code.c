#include <stdio.h>
#include <string.h>
#include <ctype.h>

char input[10];
int i, error;
void E();
void T();
void EP();
void TP();
void F();

void E()
{
    T();
    EP();
}

void EP()
{
    if (input[i] == '+')
    {
        i++;
        T();
        EP();
    }
}

void T()
{
    F();
    TP();
}

void TP()
{
    if (input[i] == '*')
    {
        i++;
        F();
        TP();
    }
}

void F()
{
    if (isalnum(input[i]))
        i++;
    else if (input[i] == '(')
    {
        i++;
        E();
        if (input[i] == ')')
            i++;
        else
            error = 1;
    }
    else
        error = 1;
}

int main()
{
    while (1)
    {
        i = 0;
        error = 0;
        printf("Enter an arithmetic expression   :  "); // Eg: a+a*a
        scanf("%s", input);
        E();
        if (strlen(input) == i && error == 0)
            printf("\nAccepted..!!!\n");
        else
            printf("\nRejected..!!!\n");
    }
}