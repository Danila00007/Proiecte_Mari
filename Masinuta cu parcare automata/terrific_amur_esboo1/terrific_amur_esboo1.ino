// C++ code
//

//PINS
const int ENA = 3;
const int ENB = 6;

const int A_1 = 2;
const int B_1 = 5;
const int A_2 = 4;
const int B_2 = 7;

const int trig_back = 13;
const int echo_back = 12;

const int trig_left = 11;
const int echo_left = 10;

const int trig_front = 9;
const int echo_front = 8;

//Speed
const float per = 58; // [%]
const float speed_A = 127 * (per/100.0);
const float speed_B = 127 * ((per+15)/100.0);
const int speed = 100;  
//Module
int module;

//Distances for Sensors
float width;
float length;
float back_distance;
float front_distance;

//time counter
float time = 0;



float Sensor(int trig, int echo)
{
  digitalWrite(trig, LOW);
  delayMicroseconds(2);
  
  digitalWrite(trig, HIGH);
  
  delayMicroseconds(10);
  digitalWrite(trig, LOW);
  
  return pulseIn(echo, HIGH) * 0.034/2;
  
  
}

void Motor_Left(char Forw, char Back, int speed)
{
  analogWrite(ENA, speed_A);
  
  digitalWrite(A_1, Forw);
  digitalWrite(A_2, Back);
}

void Motor_Right(char Forw, char Back, int speed)
{
  analogWrite(ENB, speed_B);
  
  digitalWrite(B_1, Forw);
  digitalWrite(B_2, Back);
}

void setup()
{
  module = 0; // Module = 0 == > Search for parking space
  			  // Module = 1 == > If parking space is big enough, enter parking space
  			  // Module = 2 == > Move the robot forward, so that the robot has enough space  in the back and front
  pinMode(ENA, OUTPUT);
  pinMode(ENB, OUTPUT);
  
  pinMode(A_1, OUTPUT);
  pinMode(B_1, OUTPUT);
  pinMode(A_2, OUTPUT);
  pinMode(B_2, OUTPUT);
  
  pinMode(trig_back, OUTPUT);
  pinMode(echo_back, INPUT);
  
  pinMode(trig_left, OUTPUT);
  pinMode(echo_left, INPUT);
  
  pinMode(trig_front, OUTPUT);
  pinMode(echo_front, INPUT);
  
  digitalWrite(ENA, LOW);
  digitalWrite(ENB, LOW);
  
  digitalWrite(A_1, LOW);
  digitalWrite(B_1, LOW);
  digitalWrite(A_2, LOW);
  digitalWrite(B_2, LOW);
  
  Serial.begin(9600);

  delay(3000);
  
}

void loop()
{
  delay(100);
  if(module == 0) {
    
    Motor_Left(HIGH, LOW, speed);// Move Forward
    Motor_Right(HIGH, LOW, speed);
    
  	width = Sensor(trig_left, echo_left);
    
  	Serial.print("Parking Width is :");
  	Serial.print(width);
  	Serial.println(" [cm] ");
    
    if(width >= 25)
    {
      time = time + 0.1;
    }
    
  	length = time * 25;
    
    Serial.print("Parking Length is :");
    Serial.print(length);
    Serial.println(" [cm] ");
    Serial.println();
    
    if(length >= 25)
    {
      Serial.println(" Parking space is sufficiently big");
      
      Motor_Left(LOW, LOW, speed);// Stop Movement
      Motor_Right(LOW, LOW, speed);
      delay(1000);
      
      Motor_Left(HIGH, LOW, speed);// Turn Left;
      Motor_Right(LOW, HIGH,speed);
      delay(500);
      
      Motor_Left(LOW, LOW, speed);// Stop Turning
      Motor_Right(LOW, LOW, speed);
      delay(1000);
      
      module = 1;
    }
  
  }
  
  if(module == 1) {
    
  	Motor_Left(LOW, HIGH, speed);// Move Backward
    Motor_Right(LOW, HIGH, speed);
    
    back_distance = Sensor(trig_back, echo_back);
    
    Serial.print("Distance in the back is :");
  	Serial.print(back_distance);
  	Serial.println(" [cm] ");
    Serial.println();
    
    if(back_distance <= 10 && back_distance > 0)
    {
      Motor_Left(LOW, LOW, speed);// Stop Movement
      Motor_Right(LOW, LOW, speed);
      delay(1000);
      
      Motor_Left(LOW, HIGH, speed);// Turn Right;
      Motor_Right(HIGH, LOW,speed);
      delay(500);
      
      Motor_Left(LOW, LOW, speed);// Stop Turning
      Motor_Right(LOW, LOW, speed);
      delay(1000);
      
      Serial.println(" Robot is inside the parking space ");
      module = 2;
    }
    
  }
  
  if(module == 2) {
    
    Motor_Left(HIGH, LOW, speed);// Move Forward
    Motor_Right(HIGH, LOW, speed);
    
    front_distance = Sensor(trig_front, echo_front);
    
    Serial.print("Distance in the front is :");
  	Serial.print(front_distance);
  	Serial.println(" [cm] ");
    Serial.println();
    
    if(front_distance <= 15 && front_distance > 0 )
    {
      Motor_Left(LOW, LOW, speed);// Stop Movement
      Motor_Right(LOW, LOW, speed);
      delay(1000);
      
      Serial.println(" Robot is parked ");
      module = -1;
    }
    
  }
}
