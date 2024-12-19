#include "pointValues.h"

//The following pins are connected to two R-2R ladder digital to analog converters (DACs)
const int pinsX[4] = {3,4,5,6};//3 is LSB (index 0)
const int pinsY[4] = {8,9,10,11};
//Max == 4.60 V


void setup() {
  for(int pin:pinsX){
    pinMode(pin, OUTPUT);
    }
   for(int pin:pinsY){
    pinMode(pin, OUTPUT);
    }
  Serial.begin(9600);
}

void loop() {
  for(int p = 0; p < IMAGE_SIZE;p++){
      delay(1);
      writeToDAC(pinsX,xValues[p]);
      writeToDAC(pinsY,yValues[p]);
      //Serial.println();
    }
}
/*
* Writes voltage levels of a point to the digital to analog converte (DAC).
*/
void writeToDAC(const int pins[], const fourBits voltage){
  for(int i=0; i<4;i++){
    if( 1==((voltage >> i) & 1)){
      digitalWrite(pins[i], HIGH);
      //Serial.print("Wrote 1 to pin");
    }else{
      digitalWrite(pins[i], LOW);
      //Serial.print("Wrote 0 to pin");
    }
    //Serial.println(i);
    }
  }
