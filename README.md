# conways-game-of-life

A Scala functional implementation of Conway's Game of Life

- Library with common preset patterns in ASCII text
- Random soup generator  
- Swing UI interface

## packaging & running
1. install sbt-assembly in `plugins.sbt`:
   
       addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.6")
   
2. run `> sbt assembly` (`assembly` in sbt shell)
3. find the generated jar somewhere in dir 'target'  
   and run `java -jar gameoflife-assembly-01.jar`
   
See https://alvinalexander.com/scala/sbt-how-build-single-executable-jar-file-assembly/

## todo

- Expand UI with controls to
  - Start/Pause/Reset evolution
  - Step evolution
  - Choose preset pattern 
  - Toggle Cell   

