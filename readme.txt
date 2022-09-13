The requested project was implemented in java. This project consists of a multithreaded client server architecture using sockets with locking support and memory caching.

The server part is divided into 3 classes, the main being the class responsible for starting the threads and the cache memory, a cache class responsible for managing the
data in the cache and the server class being responsible for exchanging data between it and the client. The cache was implemented as the concept of a FIFO(firt in, first out) queue different from the algorithms of
cache as the LRU, and for this implementation a linked list was used. the cache supports up to 64mb adding the size of each file contained in it and when a file is requested
for the cache, it will remove the first element if there is no space available. the class referring to the server receives as parameters the cache reference and the port to be used during the connection.
the server class has multithread support, which can be implemented in different ways in java. the way chosen was to extend the Thread class.
for the treatment of problems of simultaneous access in the cache, the server uses locking to avoid that several threads modify the cache at the same time, being able to generate
data inconsistency problems. on the client side, it will build a message with the parameters referring to the server address and the port on which the server is listening. after mounted to
message the client can choose if he wants a list with the names of the files contained in the cache. the list is a string concatenated with all the names of the files in the cache.
during the development of the project I had problems sending large files via socket, due to this the project is limited to sending files with the size
socket buffer pattern.
 