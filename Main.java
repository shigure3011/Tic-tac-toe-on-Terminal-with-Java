import java.util.Scanner;
public class TicTacToe {
    private String cells = "         ";
    private int countX = 0;
    private int countO = 0;
    private int size = 3;
    private int player = 1;
    private boolean isDraw = true;
    public boolean checkFinished() {
        return (size * size - countX - countO == 0);
    }
    public boolean checkMainDiag(char player) {
        for (int i = 0; i < size; i++)
            if (cells.charAt(size * i + i) != player) return false;
            return true;
    }
    public boolean checkSubDiag(char player) {
        for (int i = 0; i < size; i++)
            if (cells.charAt((size - 1) * (i + 1)) != player) return false;
        return true;
    }
    public boolean checkRow(char player, int row) {
        for (int i = 0; i < size; i++)
            if (cells.charAt(size*row+i) != player) return false;
        return true;
    }
    public boolean checkCol(char player, int col) {
        for (int i = 0; i < size; i++)
            if (cells.charAt(size*i+col) != player) return false;
        return true;
    }
    public boolean checkWin(char player) {
        for (int i = 0; i < size; i++) {
            if (checkRow(player,i)) return true;
        }
        for (int i = 0; i < size; i++) {
            if (checkCol(player, i)) return true;
        }
        if (checkMainDiag(player)) return true;
        if (checkSubDiag(player)) return true;
        return false;

    }
    public void printCells(){
        System.out.println("---------");
        for (int i = 0; i < size; i++) {
            System.out.print("| ");
            for (int j = 0; j < size; j++) {
                System.out.print(cells.charAt(size*i+j)+" ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
    public boolean isNum(String s){
        try {
            int num = Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public boolean isValid(String firstNum, String secondNum) {
        if (isNum(firstNum) && isNum(secondNum)) {
            int X = Integer.parseInt(firstNum);
            int Y = Integer.parseInt(secondNum);
            if (X <= 0 || X > 3 || Y <= 0 || Y > 3) {
                System.out.println("Coordinates should be from 1 to " + size);
                return false;
            }
            int pos = size * (size - Y) + (X - 1);
            if (cells.charAt(pos) == ' ') return true;
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
        System.out.println("You should enter numbers");
        return false;
    }
    public void getNumbers() {
        Scanner scanner = new Scanner(System.in);
        String firstNum;
        String secondNum;
        do {
            System.out.println("Enter the coordinates: ");
            firstNum = scanner.next();
            secondNum = scanner.next();
        }
        while (!isValid(firstNum,secondNum));
        int X = Integer.parseInt(firstNum);
        int Y = Integer.parseInt(secondNum);
        int pos = size * (size - Y) + (X - 1);
        if (player == 1) {
            cells = cells.substring(0, pos) + 'X' + cells.substring(pos + 1);
            countX++;
        }
        else {
            cells = cells.substring(0, pos) + 'O' + cells.substring(pos + 1);
            countO++;
        }
        player *= -1;
        printCells();
    }
    public void play() {
        printCells();
        do {
            getNumbers();
            char[] players = {'X','O'};
            for (char player: players) {
                if (checkWin(player)) {
                    System.out.println(player + " wins");
                    isDraw = false;
                }
            }
            if (!isDraw) break;
        } while (!checkFinished());
        if (isDraw) System.out.println("Draw");
    }

    public static void main(String[] args) {
        Main game = new Main();
        game.play();
    }
}
