import java.util.*;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.IOException;

class Main {

  public static char[][] fillBoard(char[][] board, char myChar){
  for (int row = 0; row < board.length; row++){
  Arrays.fill(board[row], 0, board[row].length, myChar);
    }
  return board;
}

  public static void printBoard(char[][] board){
    System.out.println();
    for (int row = 0; row < board.length; row++){
    System.out.print("|");
  for (int col = 0; col < board[row].length; col++){
    System.out.print(" " + board[row][col] + " |");
    }
   System.out.println();
  }
}

  public static boolean tryDropPiece(char[][] board, int col, char player){
boolean result = false;

if (board[0][col] != '□'){
System.out.println("Whoops! Full, pick another one.");
return false;
}

for (int row = board.length-1; row >= 0; row--){
if (board[row][col] == '□'){
board[row][col] = player;
    return true;
   }
 }
  return result;
}

  public static boolean winCondition(char[][]board) {
    //horiz
    for (int row = 0; row < board.length; row++){
    for (int col = 0; col < board[row].length - 3; col++){
      if (board[row][col] != '□' && board[row][col] == board[row][col+1] && board[row][col] == board[row][col+2] && board[row][col] == board[row][col + 3]){
      return true;
        }
      }
    }
    //vert
    for (int col = 0; col < board[0].length; col++){
      for (int row = 0; row < board.length - 3; row++){
        if (board[row][col] != '□' && board[row][col] == board[row + 1][col] && board[row][col] == board[row + 2][col] && board[row][col] == board[row + 3][col]){
        return true;
        }
      }
    }
    //diag back
    for (int row = 0; row < board.length - 3; row++){
    for (int col = 0; col < board[row].length - 3; col++){
      if (board[row][col] != '□' && board[row][col] == board[row + 1][col + 1] && board[row][col] == board[row + 2][col + 2] && board[row][col] == board[row + 3][col + 3]) {
        return true;
        }
      } 
    }
    //diag forwards
    for (int row = 0; row < board.length - 3; row++){
    for (int col = 3; col < board[row].length; col++){
      if (board[row][col] != '□' && board[row][col] == board[row+1][col-1] && board[row][col] == board[row+2][col-2] && board[row][col] == board[row+3][col-3]){
    return true;
        }
      }
    }
    return false;
  }

  public static char switchPiece(char currentPlayer){
    if (currentPlayer == 'X'){
      return 'O';
    } else {
      return 'X';
    }
}

  public static void main(String[] args) throws IOException {
    FileInputStream byteStream = new FileInputStream("savedata.txt");
    FileOutputStream fileStream = new FileOutputStream("savedata.txt");
    PrintWriter outFS = new PrintWriter(fileStream);
    Scanner inFS = new Scanner(byteStream);


    char[][] board = new char[6][7];
    Scanner input = new Scanner(System.in);
    char player = 'X';

    board = fillBoard(board, '□');
    System.out.println("Welcome to Connect Four!");
    System.out.println("Type in 'n' to strt a new game or press 'l' to load a previous game.");
    char answer = input.next().charAt(0);
   
    if(answer == 'l') {
      for (int i = 0; i < 6; ++i) {
        for (int j = 0; j < 7; ++j) {
        if(inFS.hasNext()) {
        board[i][j] = inFS.next().charAt(0);
        }
      }
     }
    }

    while (true){

printBoard(board);
System.out.println("  1   2   3   4   5   6   7 ");

System.out.print("Player " + player + ", please enter the column where you'd like to drop your piece: ");

int col = (input.nextInt() - 1);
if (tryDropPiece(board,col, player)){
if(winCondition(board)){
System.out.println("Player " + player + " wins!");
  printBoard(board);
  System.out.println("  1   2   3   4   5   6   7 ");
return; 
    }
  player = switchPiece(player);
  }
  System.out.println("If you'd like to save your progress and quit press 'q.'");
  System.out.println("Enter anything else to continue.");
  answer = input.next().charAt(0);
  if (answer == 'q') {
    for (int i = 0; i < 6; ++i) {
      for (int j = 0; j < 7; ++j) {
        outFS.println(board[i][j]);
      }
    }
    break;
  }
}
inFS.close();
outFS.close();
  }
}
