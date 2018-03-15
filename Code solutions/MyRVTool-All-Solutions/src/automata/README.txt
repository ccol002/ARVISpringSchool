There is a bug due to the lack of separation between events and transitions: 

since each transition has its own pointcut, subsequent transitions can fire upon a single event.