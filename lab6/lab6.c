#include <stdio.h>
#include <stdlib.h>

int i = 0;
char str[10], tp;
void advance()
{
    i++;
    tp = str[i];
}

void F()
{
    if (tp == "i")
    {
        advance();
    }
    else
    {
        if (tp == "(")
        {
            advance();
            E();
            if (tp == ")")
            {
                advance();
            }
        }
        else
        {
            printf("\n String not accepted");
            // exit(1);
        }
    }
}

void TP()
{
    if (tp == "*")
    {
        advance();
        F();
        TP();
    }
}

void T()
{
    F();
    TP();
}

void EP()
{
    if (tp == "+")
    {
        advance();
        T();
        EP();
    }
}

void E()
{
    T();
    EP();
}

int main()
{
    // int op;
    while (1)
    {
        printf("\n Enter the string: ");
        scanf("%s", &str);
        tp = str[i];
        E();
        if (tp == "\0")
        {
            printf("String Accepted\n");
        }
        else
        {
            // printf("String Not Accepted\n");
        }
        // printf("\n Enter 1 to exit");
        // scanf("%d", &op);
        // if (op == 1)
        // {
        //     exit(0);
        // }
    }

    return 0;
}
