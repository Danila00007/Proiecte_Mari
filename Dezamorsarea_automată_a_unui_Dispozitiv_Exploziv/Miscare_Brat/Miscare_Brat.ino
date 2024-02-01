#include "AX12A.h"

#define DirectionPin     (10u)
#define BaudRate          (1000000ul)
#define ID                (10u)
#define ID2       (14u)
void setup()
{
    ax12a.begin(BaudRate, DirectionPin, &Serial);
}
void loop()
{
  ax12a.move(ID,0);
  delay(1000);
  ax12a.move(ID2,0);
}
}
