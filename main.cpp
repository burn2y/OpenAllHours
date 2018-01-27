#include <mbed.h>

int main() {

DigitalOut green(LED_GREEN);
DigitalOut blue(LED_BLUE);
DigitalOut red(LED_RED);

    while(1) {
      red.write(0);
      wait(0.5);
      red.write(1);
      wait(0.5);
      green.write(0);
      wait(0.5);
      green.write(1);
      wait(0.5);
      blue.write(0);
      wait(0.5);
      blue.write(1);
      wait(0.5);
    }
}
