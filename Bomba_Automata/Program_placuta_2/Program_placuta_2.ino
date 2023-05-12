#include <SPI.h>
#include <MFRC522.h>

#define SS_PIN 10
#define RST_PIN 9
#define greenLED 3
#define redLED 2

MFRC522 mfrc522(SS_PIN, RST_PIN);
int i=0;

void setup() {
  Serial.begin(9600);
SPI.begin();
mfrc522.PCD_Init();
pinMode(redLED, OUTPUT);  //set the LED as an output
  pinMode(greenLED, OUTPUT);

}

void loop() {
  digitalWrite(redLED, HIGH);
  delay(1000);
  digitalWrite(redLED, LOW);
  digitalWrite(greenLED, HIGH);
  delay(1000);
  digitalWrite(greenLED, LOW);
  if( ! mfrc522.PICC_IsNewCardPresent())
{
  return;
}

if( ! mfrc522.PICC_ReadCardSerial())
{
  return;
}
Serial.print("UID tag:");
String content= "";
byte letter;
for (byte i=0; i < mfrc522.uid.size; i++)
{
  Serial.print(mfrc522.uid.uidByte[i] < 0x10 ? " 0" : " ");
  Serial.print(mfrc522.uid.uidByte[i], HEX);
  content.concat(String(mfrc522.uid.uidByte[i] < 0x10 ? " 0" : " "));
  content.concat(String(mfrc522.uid.uidByte[i], HEX));
}

Serial.println();
Serial.print("Mesaj: ");
content.toUpperCase();

if(content.substring(1) == "86 F6 B6 57"){
    digitalWrite(greenLED, LOW);
  digitalWrite(redLED, HIGH);
  delay(20000);
  
  
}
else{}

if(content.substring(1) == "93 61 41 1C"){
 digitalWrite(redLED, LOW);
 digitalWrite(greenLED, HIGH);
delay(20000);
}
else{}
}
