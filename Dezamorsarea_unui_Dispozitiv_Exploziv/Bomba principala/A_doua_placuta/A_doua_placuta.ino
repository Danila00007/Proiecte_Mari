#include <Keypad.h> //include keypad library - first you must install library (library link in the video description)
#define redLED 11 //define the LED pins
#define greenLED 10
const int trigPin = 13;
const int echoPin = 12;
int state = 0;
char* password ="1510";
int pozisyon = 0,k=1,c=0,b=10,a=20; //keypad position
long dezamorsare;
int distanta;
const byte rows = 4; //number of the keypad's rows and columns
const byte cols = 4;
const byte pinButon = A5;
char keyMap [rows] [cols] = { //define the cymbols on the buttons of the keypad

  {'1', '2', '3', 'A'},
  {'4', '5', '6', 'B'},
  {'7', '8', '9', 'C'},
  {'*', '0', '#', 'D'}
};

byte rowPins [rows] = {2,3,4,5}; //pins of the keypad
byte colPins [cols] = {6,7,8,9};


Keypad myKeypad = Keypad( makeKeymap(keyMap), rowPins, colPins, rows, cols);


void setup(){

   pinMode(trigPin, OUTPUT); // Sets the trigPin as an Output
  pinMode(echoPin, INPUT); // Sets the echoPin as an Input
  Serial.begin(9600); // Starts the serial communication
  pinMode(pinButon, INPUT);
  digitalWrite(pinButon, HIGH);
    pinMode(redLED, OUTPUT);  //set the LED as an output
  pinMode(greenLED, OUTPUT);

  
}

void loop(){

  /*digitalWrite(trigPin, LOW);
  delayMicroseconds(2);
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);
   dezamorsare = pulseIn(echoPin, HIGH);
   distanta = dezamorsare * 0.034 / 2;
  char whichKey = myKeypad.getKey(); //define which key is pressed with getKey
   if(Serial.available() > 0){ // Checks whether data is comming from the serial port
    state = Serial.read(); }*/
   
    digitalWrite(redLED, HIGH);
  delay(1000);
  digitalWrite(redLED, LOW);
  digitalWrite(greenLED, HIGH);
  delay(1000);
  digitalWrite(greenLED, LOW);
  
  /*do{
   
  #define redLED 11 //definire LED rosu pe port 11
#define greenLED 10 //definire LED verde pe port 10

void setup(){

  Serial.begin(9600);
    pinMode(redLED, OUTPUT);  //Setare LED rosu ca si OUTPUT
  pinMode(greenLED, OUTPUT);  //Setare LED verde ca si OUTPUT

  
}

void loop(){

  digitalWrite(redLED, HIGH);  //Aprindere LED rosu
  delay(1000);                 //Asteptare 1 secunda
  digitalWrite(redLED, LOW);   //Stingere LED rosu
  digitalWrite(greenLED, HIGH);//Aprindere LED verde
  delay(1000);                 //Asteptare 1 secunda
  digitalWrite(greenLED, LOW); //Stingere LED verde
  
  
}
  
  if(whichKey == password [pozisyon]){
   pozisyon ++;
  }
  
 Serial.print("   ");
    Serial.print(pozisyon);
  if(digitalRead(pinButon)== LOW && pozisyon == 5){
  
  do
  {
    digitalWrite(redLED, HIGH);
  delay(1000);
  digitalWrite(redLED, LOW);
  delay(1000);
  a=a-1;

  delayMicroseconds(2);
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);
   dezamorsare = pulseIn(echoPin, HIGH);
   distanta = dezamorsare * 0.034 / 2;
    if(distanta<40)
        {
          
          do{
     digitalWrite(redLED, HIGH);
  delay(1000);
  digitalWrite(redLED, LOW);
  a=a-1;
           
  b=b-1;
  
  if(a==-1)
  b=0;
 
  if(distanta>40)
  {b=0;
   c=1;}
        }while(b!=0);
        
        if(distanta>40 && b==0 && c==1)
        {
          digitalWrite(redLED, HIGH);
  delay(1000);
  b=5;
  }
  else
  a=-1;
        
        }
  }while(a!=-1);

  
  if(a==-1 && b==0)
  {
    a=40;
 digitalWrite(greenLED, HIGH);
 delay(3000);
 digitalWrite(greenLED, LOW);
    pozisyon=0;
    }
 
  if(a==-1){
  a=40;
digitalWrite(redLED, HIGH);
    delay(3000);
    digitalWrite(redLED, LOW);
    pozisyon=0;
  }}

if(digitalRead(pinButon)== LOW && pozisyon == 3){
  
  
  do
  {
    b=5;
  
  a=a-1;
digitalWrite(redLED, HIGH);
  delay(1000);
  digitalWrite(redLED, LOW);
  
  delayMicroseconds(2);
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);
   dezamorsare = pulseIn(echoPin, HIGH);
   distanta = dezamorsare * 0.034 / 2;
    if(distanta<40)
        {
          
          do{
     digitalWrite(redLED, HIGH);
  delay(1000);
  digitalWrite(redLED, LOW);
  a=a-1;
           
  b=b-1;
  
  if(a==-1)
  b=0;
 
  if(distanta>40)
  {b=0;
   c=1;}
        }while(b!=0);
        
        if(distanta>40 && b==0 && c==1)
        {
          digitalWrite(redLED, HIGH);
  delay(1000);
  b=5;
  }
  else
  a=-1;
        
        }
  }while(a!=-1);

  
  if(a==-1 && b==0)
  {
    a=40;
 digitalWrite(greenLED, HIGH);
 delay(3000);
 digitalWrite(greenLED, LOW);
    pozisyon=0;
    }
 
  if(a==-1){
  a=40;
digitalWrite(redLED, HIGH);
    delay(3000);
    digitalWrite(redLED, LOW);
    pozisyon=0;
  }
  
}}while(k==0);*/
}
