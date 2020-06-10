#include <dht11.h>
#include<ultrasontemp.h>
//Temperature and Humidity sensor 
#define DHT11PIN 50
dht11 DHT11;


int ldr,temp,humi,id1,id2,id3,id4,aqua;
float distance;
int duration;
String str;

//Ultrasonic 
const int trigpin=48;
const int echopin=51;
int dur,distanceo;
ultrasontemp ulra;

//Infrared Sensor
int ir1=32;
int ir2=34;
int ir3=36;
int ir4=38;

//Lightdetecting sensor
int l=40;

//AirQuality Sensor 
int aq= A0;



void setup()
{
    pinMode(trigpin,OUTPUT);
    pinMode(echopin,INPUT);
    pinMode(id1,INPUT);
    pinMode(id2,INPUT);
    pinMode(id3,INPUT);
    pinMode(id4,INPUT);
    Serial.begin(9600);
    Serial1.begin(9600);

}



void loop() 
  {
 
      //Humidity and Temperature
      DHT11.read(DHT11PIN);
      temp=DHT11.temperature;
      humi=DHT11.humidity;
      
      //Ultrasonic 
      digitalWrite(trigpin,LOW);
      delayMicroseconds(2);
      digitalWrite(trigpin,HIGH);
      delayMicroseconds(10);
      duration=pulseIn(echopin,HIGH);
      distance=ulra.calculate(temp,humi,duration); //function for calculating distance in terms of temperature and humidity
      distanceo=(int)distance;
      
      //Infrared 
      id1=digitalRead(ir1);
      id2=digitalRead(ir2);
      id3=digitalRead(ir3);
      id4=digitalRead(ir4);

      //Light detecting sensor
      ldr=digitalRead(l);
      
      //AirQuality
      aqua=analogRead(aq);

      //Printing all the inputs

      Serial.println(temp);
      delay(50);
      Serial.println(humi);
      delay(2);
      Serial.print("Distance:");
      Serial.println(distance);
      delay(1000);
      Serial.print("Infra1:");
      Serial.println(id1);
      delay(1000);
      Serial.print("Infra2:");
      Serial.println(id2);
      delay(1000);
      Serial.print("Infra3:");
      Serial.println(id3);
      delay(1000);
      Serial.print("Infra4:");
      Serial.println(id4);
      delay(1000);
      Serial.print("Light:");
      Serial.println(ldr);
      delay(1000);
      Serial.print("Air Quality:");
      Serial.println(aqua);
      delay(1000);
      str=String(temp)+String(',')+String(humi)+String(',')+String(distanceo)+String(',')+String(id1)+String(',')+String(id2)+String(',')+String(id3)+String(',')+String(id4)+String(',')+String(ldr)+String(',')+String(aqua);
      Serial1.println(str);

}
