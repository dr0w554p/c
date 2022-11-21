/*
E -> TE'
E' -> +TE | EPSILON
T -> FT'
T' -> *FT | EPSILON
F -> (E) | ID
*/

/**
 * i+1
 * (i++)
 * i+i*i
 * i*i*i+i
 * 
 * i-i
 * 1
 * i+1
 * i-1*i*i
*/
#include<iostream>

using namespace std;
string s;
char token;
int ptr = 0;
void E();
void EP();
void T();
void F();
void TP();
void advance()
{
    ptr++;
    token = s[ptr];
}
void E()
{
    T();
    EP();
}
void EP()
{
    if (token == '+')
    {
        advance();
        T();
        E();
    }
}
void T()
{
    F();
    TP();
}
void TP()
{
    if (token == '*')
    {
        advance();
        F();
        T();
    }
}
void F()
{
    if (token == '(')
    {
        advance();
        E();
        if (token == ')')
        {
            advance();
        }
    }

    else if (token == 'i')
    {
        advance();
    }
}

int main()
{
    while (1)
    {
        /* code */
        cout << "Enter a string: ";
        cin >> s;
        token = s[ptr];
        E();
        if (s[ptr] == '\0')
            cout << "ACCEPTED!!\n";
        else
            cout << "NOT ACCEPTED!!!\n";
        // int choice;
        // cout << "Enter 0 to exit: ";
        // cin >> choice;
        // if (choice == 0)
        //     return 0;
        ptr = 0;
    }

    return 0;
}