pacman
======

Pacman Clone

#RUNNING INSTRUCTIONS

There is executable file called "run" in the project's root directory.
This will automatically compile and run the program in a unix like environment.
This project requires:
1. The Java 7 JDK including the virtual machine
2. Bash
3. Unix environment

#Notice

Please read before contributing.

#Design Decisions

##Game Loop

The `PacmanGame` object extends a `JFrame` and is responsible for the game loop.
We follow a common convention of the loop being devided between updating game logic and drawing.
This way we can control the frame rate independently of the logic.
Hence, they are on different threads.
All drawing is done with the graphics context which normally has the variable name `g`.
The game is updated by taking in the number of milliseconds (dt) that have elapsed since the last update.
The `PacmanGame` object updates and draws the `Room` object.
The `Room` object updates and draws everything else.

##Mutable over Immutable

Although a immutable API is considered more safe, we have decided to go with mutable objects.
This means all of an object's properties are in the public space.
To ensure high cohesion and low coupling the Room object will handel (almost) all the game logic.
The only exception is that `Pacman` and the `Ghost` asks the room if various board positions are free.
All other collisions should be handled by the `Room`.
The benifit of using mutable design in this project is that no additional structure will be needed to added for the `Room` to do what it needs to do.
Also, since the `Room` will handle everything, this makes conceptualizing the game easy.

##GameObject Inheritance

**Everything** in the `Room` extends `GameObject`.
The `GameObject` has all the properties needed for an object to be:
1. updated
2. drawn
3. collision detected
If you only need an object to be drawn but not updated, don't overload the `update` method.
The same principle applies any functionality you don't need for an object in the `Room`.

##Media

All in game artwork is currently contained in a spritemap located at `./media/spritemap-alpha.png`.
There is another version without the alpha for previewing at `./media/spritemap.png`.
Before making any artwork changes look at `./doc/tiled-playfield.png` and follow the style of the old arcade version of the game.
Before making any animation changes look at [Pacman Arcade](http://www.youtube.com/watch?v=uswzriFIf_k) (if the link goes down search for pacman arcade on youtube).
Use `drawSprite(Graphics2D g,int size, int i, int j, int offsetX, int offsetY)`.
`i` and `j` are for which tile.
Standard tile sizes are either 12 px or 24 px.
The offset is used to move the position of the graphic in relation to the object's bounding box.

#Notes

- Pacman should never directly interact with ghost or dots.

#TODO

