# Gone-Garbage

A project that primarily focuses to eliminate the hazards of garbage overflow.The system is primarily based on IoT that can alert the responsible officials to collect the garbage from the locations where overflowing dumpsters need immediate concern.

## General Public Application

This application holds the feature of reporting complaints about the overflow of garbage dumpsters in the user's surroundings.The user can choose a specific type of complaint and can even send in pictures regarding it and the application also captures the user's location and sends these dadta to a central base.


## TruckDriver Application

This application uses Google Maps API to locate all the points where the truck driver has to collect the garbage and it priortizes the location based on the dumpster's overflow ratio.The data for this application comes in from the central base which has records for both public's complaint also the data from IoT kits attached with the dumpsters. 

## IoT Kit

The IOT kit is installed on the lid of the trash can. This kit includes sensors to measure the level of garbage in each dumpster along with it’s location. It raises an alarm to the corresponding truck driver through the system such as corporation of the city. So an interface at the central level is made to receive complaints from the general public and also the locations alerted by our automation IoT  kit. Both,together will enhance the complete automation of the garbage monitoring system. Also disposal is being captured by a raspberry pi, an object detection model is trained to detect the presence and location of the trash, if any , around the trash can. This way the cleanliness of the surrondings of the trash can also be maintained.


## Screenshots

<p float="left">
<img src="https://github.com/barath83/Smart-Garbage-Disposal-and-Monitoring-System/blob/master/images/iot-kit.jpeg" width="450" height="400" >
</p>

## Co-Creators

<p>Raahul Kalyaan J - <span><a href="https://github.com/Raahul46"/>Raahul46</span></a></p>
<p>Janani VI - <span><a href="https://github.com/Janani216"/>Janani216</span></a></p>
<p>Hareedaran Bharathi - <span><a href="https://github.com/hareedharanb"/>hareedharanb</span></a></p>


