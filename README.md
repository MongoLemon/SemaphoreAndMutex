# SemaphoreAndMutex
This repo is working on the test of Semaphore and mutex

One Thread is like you get the key to a door. But there is only one key. Everyone has to stay
in line to use the key one after one.

Mutexes are typically used to serialise access to a section of re-entrant code that
cannot be executed by more than one thread concurrently. A mutex object only allows one thread
into a controlled section and forces other threads which attempt to gain access to that section
to wait until the first thread has exited from that section.

mutex = manually exclusive semaphore

N specified threads is like there are several keys, for instance 3. At the beginning, all 3 keys
are available until they are all borrowed out. Other people have to wait until any one of the keys
is returned and free to use again.

A semaphore restricts the number of simultaneous users of the a shared resources up to a maximum
number. Threads can request access to the resource(decrementing the semaphore) and can signal that
they have finished using the resource(incrementing the semaphore).