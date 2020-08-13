# JrCodingChallengeMS3
A Java app that consumes a CSV file, parses the data, and inserts valid records into a SQLite database

~~~~~~~~~Summary~~~~~~~~~
This repo's purpose is to document, store and record data read from a csv file into three(3) locations. It is also a coding challenge given to me by the MS3 team.

~~~~~~~~~How to use~~~~~~~~~
Download version 3.30.1 of xerial's sqlite-jdbc found here "https://bitbucket.org/xerial/sqlite-jdbc/downloads/" 
Put the downloaded item in your Java fodler with this path. C:\Program Files (x86)\Java\jre1.8.0_261\lib\ext
In this ext folder create a folder titled sqlite-jdbc. In addition make sure the latest version of Java is installed along with any Java IDE.
Download all files from master branch to any folder. The easy way to run this and see the results is to just let the executable file "MS3exe.jar" run. 
This will produce a log file, a csv file, and a sqlite database after a minute or two. 
The "MS3exe.jar" file runs off a slightly modified version of the original csv file given.
This modified version of the csv removed an apostrophe from four(4) data cells (all cities). The modified version of the csv works completely.

The unmodified version of the csv file runs up until it hits the first apostrophe in the city column. 
To see what this data looks like or how it runs open up the "src/Main.java" file and alter line 16 from "ms3InterviewMod.csv" to "ms3Interview.csv"

~~~~~~~~~Approach~~~~~~~~~
The way I approached this project was to segment each part into its own mini-project. The first step was to be able to completely read the csv file with correct output everytime. 
After this I made a CSVWriter to put bad data into its own csv file. The log writer was after this and then lastly the SQLite DB was created. 
I wanted to make each of these functions as simple as possible instead of trying to overcomplicate it.


An assumption I made while working on this challenge was definitely how simple I thought it would have been. I assumed the CSVReader
would be easy, but there were a few curveballs in the data that I didn't take into account at first. Also setting up and 
creating an SQLite database was a little more difficult then what I thought it would have been.



