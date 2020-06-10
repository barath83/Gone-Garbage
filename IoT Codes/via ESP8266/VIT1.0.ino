int const trigPin = 22;
int const echoPin = 24;
int const buzzPin = 26;

void setup()
  
   {
      
      pinMode(trigPin, OUTPUT); 
      pinMode(echoPin, INPUT); 
      pinMode(buzzPin, OUTPUT); 
  }
String str;
int aq,ldr;

void loop()

  {
  
      int duration, distance;
      digitalWrite(trigPin, HIGH);
      delay(1);
      digitalWrite(trigPin, LOW);
      duration = pulseIn(echoPin, HIGH);
      distance = (duration/2) / 29.1;
      if (distance <= 50 && distance >= 0)
      {
            digitalWrite(buzzPin, HIGH);
       } 
       
       else 
       
       {

digitalWrite(buzzPin, LOW);

        }
        aq=analogRead(A0);
        ldr=analogRead(A2);
        str=String(distance)+String(',')+String(aq)+(',')+String(ldr);
        Serial.println(distance);
        Serial.println(aq);
        Serial.println(ldr);

delay(5000);
}
