#include <mbed.h>
#include <string>
#include <iostream>
using namespace std;
Ticker pit;
DigitalOut   red(LED_RED);


void dash()
{
  red.write(0);
  wait(0.36);
  red.write(1);
  wait(0.12);
}

void dot(void)
{
  red.write(0);
  wait(0.12);
  red.write(1);
  wait(0.12);
}

void letterSeperator(void)
{
  wait(0.36);
}

void wordSeperator(void)
{
  wait(0.84);
}

void a(void)
{
  dot();
  dash();
  letterSeperator();
}
void b(void)
{
  dash();
  dot();
  dot();
  dot();
  letterSeperator();
}
void c(void)
{
  dash();
  dot();
  dash();
  dot();
  letterSeperator();
}
void d(void)
{
  dash();
  dot();
  dot();
  letterSeperator();
}
void e(void)
{
  dot();
  letterSeperator();
}
void f(void)
{
  dot();
  dot();
  dash();
  dot();
  letterSeperator();

  }
void g(void)
{
  dash();
  dash();
  dot();
  letterSeperator();
}
void h(void)
{
  dot();
  dot();
  dot();
  dot();
  letterSeperator();
}
void i(void)
{
  dot();
  dot();
  letterSeperator();
}
void j(void)
{
  dot();
  dash();
  dash();
  dash();
  letterSeperator();
}
void k(void)
{
  dash();
  dot();
  dash();
  letterSeperator();
}
void l(void)
{
  dot();
  dash();
  dot();
  dot();
  letterSeperator();
}
void m(void)
{
  dash();
  dash();
  letterSeperator();
}
void n(void)
{
  dash();
  dot();
  letterSeperator();
}
void o(void)
{
  dash();
  dash();
  dash();
  letterSeperator();
}
void p(void)
{
  dot();
  dash();
  dash();
  dot();
  letterSeperator();
}
void q(void)
{
  dash();
  dash();
  dot();
  dash();
  letterSeperator();
}
void r(void)
{
  dot();
  dash();
  dot();
  letterSeperator();
}
void s(void)
{
  dot();
  dot();
  dot();
  letterSeperator();
}
void t(void)
{
  dash();
  letterSeperator();
}
void u(void)
{
  dot();
  dot();
  dash();
  letterSeperator();
}
void v(void)
{
  dot();
  dot();
  dot();
  dash();
  letterSeperator();
}
void w(void)
{
  dot();
  dash();
  dash();
  letterSeperator();
}
void x(void)
{
  dash();
  dot();
  dot();
  dash();
  letterSeperator();
}
void y(void)
{
  dash();
  dot();
  dash();
  dash();
  letterSeperator();
}
void z(void)
{
  dash();
  dash();
  dot();
  dot();
  letterSeperator();
}

void  transmit(string character)
{
  if(character == "a" || character == "A"){ a();}
  else if(character == "b" || character == "B") { b(); }
  else if(character == "c" || character == "C") { c(); }
  else if(character == "d" || character == "D") { d(); }
  else if(character == "e" || character == "E") { e(); }
  else if(character == "f" || character == "F") { f(); }
  else if(character == "g" || character == "G") { g(); }
  else if(character == "h" || character == "H") { h(); }
  else if(character == "i" || character == "I") { i(); }
  else if(character == "j" || character == "J") { j(); }
  else if(character == "k" || character == "K") { k(); }
  else if(character == "l" || character == "L") { l(); }
  else if(character == "m" || character == "M") { m(); }
  else if(character == "n" || character == "N") { n(); }
  else if(character == "o" || character == "O") { o(); }
  else if(character == "p" || character == "P") { p(); }
  else if(character == "q" || character == "Q") { q(); }
  else if(character == "r" || character == "R") { r(); }
  else if(character == "s" || character == "S") { s(); }
  else if(character == "t" || character == "T") { t(); }
  else if(character == "u" || character == "U") { u(); }
  else if(character == "v" || character == "V") { v(); }
  else if(character == "w" || character == "W") { w(); }
  else if(character == "x" || character == "X") { x(); }
  else if(character == "y" || character == "Y") { y(); }
  else if(character == "z" || character == "Z") { z(); }
  else {wordSeperator(); }

}
int main()
{
        string message;
        puts("Enter Message to convert to morse code: ");
        cin >> message;

        for (int i = 0; i < message.length(); i++)
        {
          string character = message.substr(i,1);
          transmit(character);
        }
}
