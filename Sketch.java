import processing.core.PApplet;

public class Sketch extends PApplet {

    //Variables   
    int [][] intGrid;
    int CELL_WIDTH = 20;
    int CELL_HEIGHT = 20;
    int MARGIN = 5;
    int ROW_COUNT = 10;
    int COLUMN_COUNT = 10;
    int SCREEN_WIDTH = (CELL_WIDTH + MARGIN) * COLUMN_COUNT + MARGIN;
    int SCREEN_HEIGHT = (CELL_HEIGHT + MARGIN) * ROW_COUNT + MARGIN;
    int column;
    int rows;
    int intBlocksSelected = 0;
    int intRowsSelected = 0;
    int intTotalRow = 0;

  
  public void settings() {
    
    size(SCREEN_WIDTH, SCREEN_HEIGHT);
  }

  public void setup() {
    background(0);
    intGrid = new int [ROW_COUNT][COLUMN_COUNT];
  }

  public void draw() {

   fill (255);
    
    for (int column = 0; column < COLUMN_COUNT; column++){
      for (int rows = 0; rows < ROW_COUNT; rows++)
        
    if (intGrid[rows][column] == 1) {
          fill (0, 255, 0);
          rect (MARGIN * (column + 1) + (column * CELL_WIDTH), MARGIN * (rows + 1) + (rows * CELL_HEIGHT), CELL_HEIGHT, CELL_WIDTH);
        } else {
          fill (255);
          rect (MARGIN * (column + 1) + (column * CELL_WIDTH), MARGIN * (rows + 1) + (rows * CELL_HEIGHT), CELL_HEIGHT, CELL_WIDTH);
        }
        
      }

    
  }

  public void mousePressed(){

    for (int rows = 0; rows < ROW_COUNT; rows++ ) {
      for (int column = 0; column < COLUMN_COUNT; column++) {
        
        if (dist(MARGIN * (column + 1) + (column * CELL_WIDTH) + 10, MARGIN * (rows + 1) + (rows * CELL_HEIGHT) + 10, mouseX, mouseY) < 10 ) {
          System.out.println("Mouse Coordinates: " + mouseX + ", " + mouseY + " Grid Coordinates: row: " + (rows + 1) + " Column: " + (column + 1));
          
            intGrid[rows][column] ^= 1;
            if (intGrid[rows][column] == 1) {
              intBlocksSelected++;
            } else {
              intBlocksSelected--;
            }
          
            if(rows < 9) {
              intGrid[rows + 1][column] ^= 1;
              if (intGrid[rows + 1][column] == 1) {
                intBlocksSelected++;
              } else {
                intBlocksSelected--;
              }
            }
          
            if(column < 9) {
              intGrid[rows][column + 1] ^= 1;
              if (intGrid[rows][column + 1] == 1) {
                intBlocksSelected++;
              } else {
                intBlocksSelected--;
              }
            }

            if(rows > 0) {
              intGrid[rows - 1][column] ^= 1;
              if (intGrid[rows - 1][column] == 1) {
                intBlocksSelected++;
              } else {
                intBlocksSelected--;
              }
            }
            
            if(column > 0) {
              intGrid[rows][column - 1] = 1;
              if (intGrid[rows][column - 1] == 1) {
                intBlocksSelected++;
              } else {
                intBlocksSelected--;
              }
            }
        }
      }
    }
    
System.out.println("Total of " + intBlocksSelected + " selected");
   
    for (int rows = 0; rows < ROW_COUNT; rows++) {
      for (int column = 0; column < COLUMN_COUNT; column++) {
        if (intGrid[rows][column] == 1) {
          intRowsSelected++;
        }
        
        if (column < COLUMN_COUNT - 1 && column > 0) {
          if (intGrid[rows][column] == 1 && intGrid[rows][column - 1] == 1) {
            intTotalRow++;
          } else if (column < COLUMN_COUNT-1 && column > 0) {
            if (intGrid[rows][column] == 1 && intGrid[rows][column + 1] == 1) {
              intTotalRow++;
            }
          }
        }
      }
    }
  }

  }