#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>


#define FIREBASE_HOST "test-afa1c.firebaseio.com"
#define FIREBASE_AUTH "GiaS0cjwXt4eC2Q7gMh7fynBMz3vKqhTqWNr7foB"
#define WIFI_SSID "Batman"
#define WIFI_PASSWORD "iambatman"

void setup() {
  Serial.begin(9600);
  

  // connect to wifi.
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("connecting");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println();
  Serial.print("connected: ");
  Serial.println(WiFi.localIP());
  
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
}

int n = 0;

int temp,humi,in1,in2,in3,in4,ldr,aq;
String str,di;
int  dist;
char distance[5]; 
char bu[20];

void loop() {


  //Serial Communication
  
    str=Serial.readString();
    str.toCharArray(bu,40);
    sscanf(bu,"%d,%d,%d,%d,%d,%d,%d,%d,%d",&temp,&humi,&dist,&in1,&in2,&in3,&in4,&ldr,&aq);
    Serial.println(temp);
    Serial.println(humi);
    Serial.println(dist);
    
  
  
  Firebase.setFloat("Temperature:", temp);
  Firebase.setFloat("Humidity:", humi);
  Firebase.setFloat("Distance", dist);
  Firebase.setFloat("Infrared 1:", in1);
  Firebase.setFloat("Infrared 2::", in2);
  Firebase.setFloat("Infrared 3:", in3);
  Firebase.setFloat("Infrared 4:",in4);
  Firebase.setFloat("Light:", ldr);
  Firebase.setFloat("Air Quality:", aq);
  
  
   if (Firebase.failed()) 
  {
      Serial.print("setting /number failed:");
      Serial.println(Firebase.error());  
      return;
  }


  delay(1000);
}
