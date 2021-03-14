# alea-iacta-core
Alea Iacta Est is a project that aims to help running RPG sessions over the network.

This module is the core library of the alea-iacta-est project, and aside the common classes it implements the Expression Engine.

## Expression Engine
The expression engine is built to be used as a simple dice roller.
It can solve compounded expression in algebrical sum form, and can interpret - beside simple integer constants - the following notations:

- **Simple Dice notation** *NdX*, for example 2d8 - which means roll two dice each with eight faces.
- **Roll'n'Keep (upper) notation** *NdXkY*, for example 10d6k5 - which means roll ten dice each with six faces, then sum together the 5 highest values
- **Roll'n'Keep (lower) notation** *NdXlY*, for example 10d6l5 - which means roll ten dice each with six faces, then sum together the 5 lowest values
- **Roll Above notation** *NdX/Y*, for example 5d10/7 - which means roll five dice each with ten faces, then count those with a value higher than, or equal to seven
- **Roll Below notation** *NdX\Y*, for example 3d20\13 - which means roll three dice each with twenty faces, then count those with a value lesser than, or equal to thirteen

For example, a perfecly valid expression could be: `4d4/3  + 10d6k5 - 1d8 - 1d12\5 +7` which could solve to anything between 3 and 40.
