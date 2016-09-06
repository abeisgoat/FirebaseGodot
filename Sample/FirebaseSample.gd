extends Node

var firebase = null

func _init():
	firebase = Globals.get_singleton("Firebase")
	firebase.initializeApp("dark-diagram", \
		"AIzaSyB4_2e0t_BSG0QJtG8aYY2vB-_QD1JvSlI", \
		"https://dark-diagram.firebaseio.com", \
		"dark-diagram.appspot.com")

func _ready():
	var status = ""
	if (firebase != null):
		firebase.addOnValueEventListener(get_instance_ID(), "hello")
	else:
		get_node("Label").set_text("Singleton is null")
	
func _firebase_on_value(data):
	get_node("Label").set_text(data)