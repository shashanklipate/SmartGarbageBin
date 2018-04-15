
import serial
import firebase_admin
import time
import datetime
import ast
from firebase_admin import db
from firebase_admin import credentials

cred = credentials.Certificate("D:\anaconda\Lib\site-packages\firebase_admin\my-firebase-db-3b8c4-export.json")

default_app = firebase_admin.initialize_app(cred,
        {'databaseURL': 'https://console.firebase.google.com/u/0/project/my-firebase-db-3b8c4/database/my-firebase-db-3b8c4/data'
        })

ref = db.reference('/heads')

arduino = serial.Serial('COM4', 9600, timeout=.1)
count = False;
count2 = False;

t = ref.get('/counts')[0]
sum = t[0]['counts']['count']
latest = ref.child('0')
print(latest)
print(sum)
previous = int(round(time.time() * 1000))
duration = 5000

def toFirebase(sum):
    print(sum)
    print(datetime.datetime.now().time())
    timeNow = datetime.datetime.now().time()
    ref.child('0').push({ "count":sum,"time":str(timeNow)})
    ref.child('0').update({'counts':{"count":sum,"time":str(timeNow)}})
    
while True:

    data = arduino.readline()[:-2].decode("utf-8")
    millis = int(round(time.time() * 1000))
    
    if data!="":
        
        enter = ast.literal_eval(data)['enter']
        exit1 = ast.literal_eval(data)['exit']
        
        if enter < 15:
            count = True;
        elif count == True:
            print("enter")
            print(enter)
            sum = sum +1
            count = False;

        if exit1 < 15:
            count2 = True;
        elif count2 == True:
            print("exit")
            print(exit1)
            sum = sum -1
            count2 = False;

    if millis - previous >= duration:
        toFirebase(sum)
        previous = millis