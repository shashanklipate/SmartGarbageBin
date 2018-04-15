from firebase import firebase
firebase = firebase.FirebaseApplication('https://newproject-ae379.firebaseio.com/')

data='b\'433453\''
result = firebase.post('/user',(data))
print(result)