#include <SPI.h>
#include <MFRC522.h>
#define blackLED 5
#define SS_PIN 10
#define RST_PIN 9
MFRC522 mfrc522(SS_PIN, RST_PIN);

int i=0;
void setup() {
Serial.begin(9600);
SPI.begin();
mfrc522.PCD_Init();

  // put your setup code here, to run once:
pinMode(blackLED, OUTPUT);  //set the LED as an output
digitalWrite(blackLED, HIGH);
Serial.println("Puneti cardul pentru al citii...");
Serial.println();
}

void loop() {
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
  digitalWrite(blackLED, HIGH);
  delay(100);
  digitalWrite(blackLED, LOW);
}
else{}

if(content.substring(1) == "93 61 41 1C"){
  Serial.println("Autorizat");
  delay(1000);
  do{
  Serial.println("a trecut");
  Serial.println(i);
  delay(1000);
  i=i+1;
  }while(i<3);
  digitalWrite(blackLED, HIGH);
  delay(100);
  digitalWrite(blackLED, LOW);
  delay(100);
  digitalWrite(blackLED, HIGH);
  delay(100);
  digitalWrite(blackLED, LOW);
  // put your main code here, to run repeatedly:
}
else{}
delay(2000);
}
