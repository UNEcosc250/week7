# FizzBuzz with Actors

To give you some sample code for Actors, we're going to play a game of FizzBuzz.

This is a game often played in primary schools to test division by three and five.
The players sit in a circle. Play goes around the circle, with each player calling out the next number...
...but if the number is divisible by 3 and 5 they should say "fizzbuzz" instead of the number. If it's divisible
by 3 but not 5, they should say "fizz", and if it's divisible by 5 but not 3 they should say "buzz".

*1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz, etc.*

In our game, the players are Actors, as is the referee.
The referee will send a player a `YourTurn` message, and they have to reply (with the number, `Fizz`, `Buzz`, or `FizzBuzz`).
The referee will broadcast the player's message out to all the other players (so they can keep track of what number the group is up to).
The referee will also send a `Wrong` message and eliminate any player that gets the reply wrong.

Play keeps counting even after `Wrong`s.

There are two versions of the game. One uses untyped actors (classic actors) and the other uses typed actors.
For each case:

1. In `MyApp`, set up a ring of `Terrible` players and start them playing. 
   They will get it all wrong, but won't even know. 
   But they should print it out to the console, so you'll see you have some Actors talking to each other.

2. Write a player who will play the game correctly (implement `FizzBuzzActor`), and set up a ring of them

3. Try changing the `Referee`'s code to use the ask pattern `?` instead of `!` for sending out the challenges and 
   testing the responses

(If you're having trouble working out whether to reply with `Fizz` or `Buzz` for a number, crib from the Referee's
code for checking the answer.)