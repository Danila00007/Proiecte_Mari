#include <Wire.h> 
#include <LiquidCrystal_I2C.h>
#include <Keypad.h> //include keypad library - first you must install library (library link in the video description)
#include "AX12A.h"

#define DirectionPin     (10u)
#define BaudRate          (1000000ul)
#define ID                (10u)
#define ID2       (14u)

const int trigPin = 10;
const int echoPin = 11;
char* password ="12"; //create a password
int pozisyon = 0,k=1,c=0,b=10,a=20,piezoPin = 12; //keypad position
long dezamorsare;
int distanta;
int poz1=350;
int poz2=150;
const byte rows = 4; //number of the keypad's rows and columns
const byte cols = 4;
const byte pinButon = 13;
char keyMap [rows] [cols] = { //define the cymbols on the buttons of the keypad

  {'1', '2', '3', 'A'},
  {'4', '5', '6', 'B'},
  {'7', '8', '9', 'C'},
  {'*', '0', '#', 'D'}
};

byte rowPins [rows] = {2,3,4,5}; //pins of the keypad
byte colPins [cols] = {6,7,8,9};


Keypad myKeypad = Keypad( makeKeymap(keyMap), rowPins, colPins, rows, cols);

LiquidCrystal_I2C lcd(0x27, 16, 2);

void setup(){
 
  lcd.begin();
  lcd.backlight();
   pinMode(trigPin, OUTPUT); // Sets the trigPin as an Output
   
  pinMode(echoPin, INPUT); // Sets the echoPin as an Input
  
  Serial.begin(9600); // Starts the serial communication
  ax12a.begin(BaudRate, DirectionPin, &Serial);
  pinMode(pinButon, INPUT);
  
  digitalWrite(pinButon, HIGH);
     
}

void loop(){

  digitalWrite(trigPin, LOW);
  delayMicroseconds(2);
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);
   dezamorsare = pulseIn(echoPin, HIGH);
   distanta = dezamorsare * 0.034 / 2;
  char whichKey = myKeypad.getKey(); //define which key is pressed with getKey
   
  do{
    
  lcd.setCursor(0, 0);
  lcd.print("   Introduceti");
  lcd.setCursor(0, 1);
  lcd.print("     Parola");
  
  if(whichKey == 'C' || whichKey == 'D' || whichKey == '#' || whichKey == '*'){

    pozisyon=0;
    lcd.clear();
    lcd.setCursor(0, 0);
  lcd.print("     Bomba");
  lcd.setCursor(0, 1);
  lcd.print("   Restartata");
    delay(1000);
    k=k-1;
    lcd.clear();
  }
  
  
  if(whichKey == password [pozisyon]){
   pozisyon ++;
   
  }
  if(whichKey == 'A')
{
  ax12a.move(ID,290);    
    delay(1000);
  for(int i=0;i<3;i++){
    ax12a.move(ID2,poz1);
    delay(850);
    ax12a.move(ID2,poz2);
    delay(1000);
    }
    ax12a.move(ID2,250);
    delay(1000);
    ax12a.move(ID,80);
    pozisyon=0;
  }

if(whichKey == 'B')
{
  ax12a.move(ID,120);
  delay(1000);
  ax12a.move(ID2,270);
  delay(1000);
  ax12a.move(ID,70);
  delay(1000);
  ax12a.move(ID,120);
  delay(1000);
  ax12a.move(ID2,250);
  delay(1000);
  ax12a.move(ID,70);
  delay(1000);
  ax12a.move(ID,120);
  delay(1000);
  ax12a.move(ID2,230);
  delay(1000);
  ax12a.move(ID,70);
  delay(1000);
  ax12a.move(ID,150);
  delay(1000);
  ax12a.move(ID2,170);
  
  delay(1000);
  ax12a.move(ID,50);
  delay(400);

  if(digitalRead(pinButon)== LOW){
    delay(1000);
    lcd.clear();
    lcd.setCursor(0, 0);
  lcd.print("     Bomba");
  
  lcd.setCursor(0, 1);
  lcd.print("    Activata");
    delay(2000);
    
  do
  {
  lcd.clear();
  lcd.setCursor(0, 0);
  lcd.print("Explodeaza in:");
  lcd.print(a);
  a=a-1;
  tone(piezoPin, 6500, 500);
  delay(1000);
  }while(a!=-1);

  ax12a.move(ID,290);    
    delay(1000);
  for(int i=0;i<3;i++){
    ax12a.move(ID2,poz1);
    delay(850);
    ax12a.move(ID2,poz2);
    delay(1000);
    }
    ax12a.move(ID2,250);
    delay(1000);
    ax12a.move(ID,80);
    
    pozisyon=0;
    }
}

  
  if(digitalRead(pinButon)== LOW){
    delay(1000);
    lcd.clear();
    lcd.setCursor(0, 0);
  lcd.print("     Bomba");
  
  lcd.setCursor(0, 1);
  lcd.print("    Activata");
    delay(2000);
    
  do
  {
  lcd.clear();
  lcd.setCursor(0, 0);
  lcd.print("Explodeaza in:");
  lcd.print(a);
  a=a-1;
  tone(piezoPin, 6500, 500);
  delay(1000);
  digitalWrite(trigPin, LOW);
  delayMicroseconds(2);
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);
   dezamorsare = pulseIn(echoPin, HIGH);
   distanta = dezamorsare * 0.034 / 2;
    if(distanta<40)
        {do{
  lcd.clear();        
  lcd.setCursor(0, 0);
  lcd.print("Explodeaza in:");
  lcd.print(a);
  a=a-1;
           lcd.setCursor(0, 1);
  lcd.print("Dezamorsare:");
  lcd.print(b);
  b=b-1;
  tone(piezoPin, 6500, 500);
  delay(1000);
          digitalWrite(trigPin, LOW);
  delayMicroseconds(2);
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);
   dezamorsare = pulseIn(echoPin, HIGH);
   distanta = dezamorsare * 0.034 / 2;
  if(a==-1)
  b=0;
 
  if(distanta>40)
  {b=0;
   c=1;}
        }while(b!=0);
        
        if(distanta>40 && b==0 && c==1)
        {
          lcd.clear();
  lcd.setCursor(0, 0);
  lcd.print("Explodeaza in:");
  lcd.print(a);
  a=a-1;
  tone(piezoPin, 6500, 500);
  delay(1000);
  b=10;
  }
  else
  a=-1;
        
        }
  }while(a!=-1);

  
  if(a==-1 && b==0)
  {
    a=20;
  lcd.clear();
    lcd.setCursor(0, 0);
  lcd.print("     Bomba");
  lcd.setCursor(0, 1);
  lcd.print("   Dezamorsata");
    delay(3000);
    lcd.clear();
    pozisyon=0;
    }
 
  if(a==-1){
  a=20;
  lcd.clear();
    lcd.setCursor(0, 0);
  lcd.print("     Bomba");
  lcd.setCursor(0, 1);
  lcd.print("   A explodat");
  tone(piezoPin, 500, 2000);
  delay(2500);
    delay(3000);
    lcd.clear();
    pozisyon=0;
  }
    
  }
}while(k==0);
}
