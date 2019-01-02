# RoomsWorld
Open source zelda clone

![roomsworld rooms 01](https://user-images.githubusercontent.com/8440520/50593901-f82ea800-0e5f-11e9-9160-0adcddadec9a.gif)

Fully free to use for any purpose

Here is the process for adding rooms:

- Download roomsworld tiles.zip from the repository

- Create the tileset in Tiled using those tiles

- Create a new tile map in Tiled (for now it must be square)

- Draw out your tile map

- Add a layer to the map and use the "exit01" through "exit40" tiles to mark the exits for the map

- Save the map as a lua file

- If you use any new tiles that are not listed in TileType, you will need to add them to that file and also to the function that creates the list of solid tiles that the player cannot walk through

-----------------------------------------------------------------------------------------------------

Using the contents of the lua file you need to create three files for each room

* map1

* map1special

* map1event

For map1 copy paste the layer1 CSV portion of the lua file into a separate file and save it to assets/rooms/

For map1special copy paste the layer2 CSV portion of the lua file into a separate file and save it to assets/rooms/

For map1event you will place the information on each line related to each exit tile on map1special and save it to assets/rooms. Separated by commas you put - eventType, tileNumber, mapToGoTo, playerX, playerY

Example:

exit, 523, cave3, 7, 21

exit, 524, cave2, 18, 20

-----------------------------------------------------------------------------------------------------

In theory other events can be created by adding different parameters to a map1special type file, and then adding code for them in the RoomWorld file
