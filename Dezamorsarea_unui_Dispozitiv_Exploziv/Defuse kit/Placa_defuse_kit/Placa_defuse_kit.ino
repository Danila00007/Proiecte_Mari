
void setup(){ // Configuration

Ax12.begin(1000000,2); // Configure the usart at 1mbps
// The digital pin 2 will make the switch from tx to rx
}

void loop(){

Ax12.move(1,random(200,800)); // Move the servo with ID 1
// to a random position between 200 and 800
// Ax12.move(1,Position); Position from 0 to 1023 Resolution

delay(1000);

Ax12.moveSpeed(1,random(160,860),random(256,1023));// Move the servo with ID 1
// at a random position and speed.
// Ax12.move(1,Position,Speed); Position and Speed from 0 to 1023 Resolution
delay(1000);
}
