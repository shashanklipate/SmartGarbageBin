import serial
import time
import re
#from firebase import firebase
#firebase = firebase.FirebaseApplication('https://newproject-ae379.firebaseio.com/')

arduino = serial.Serial('COM4', 9600, timeout=.1)

while True:
		data = arduino.readline()[:-2]      

		if data != '':
			#new = ''.join(c for c in data if c in '0123456789')
			
			new = data.decode("utf-8")
			#result = firebase.post('/user',(new))
			#print(result) 
			print(data)
			print(new)
			time.sleep(1)



