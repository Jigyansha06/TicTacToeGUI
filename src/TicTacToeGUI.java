import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI {

    private JFrame frame;
    private JButton[][] buttons;
    private char currentPlayer;
    private char[][] board;

    public TicTacToeGUI() {
        board = new char[3][3];
        currentPlayer = 'X';
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(3, 3));

        buttons = new JButton[3][3];
        initializeBoard();
        initializeButtons();

        frame.setVisible(true);
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private void initializeButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].setBackground(Color.BLACK);

                final int r = row;
                final int c = col;
                buttons[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (buttons[r][c].getText().equals("") && board[r][c] == ' ') {
                            buttons[r][c].setText(String.valueOf(currentPlayer));
                            board[r][c] = currentPlayer;
                            if (currentPlayer == 'X') {
                                buttons[r][c].setForeground(Color.RED);
                            } else {
                                buttons[r][c].setForeground(Color.GREEN);
                            }
                            if (checkForWin()) {
                                JOptionPane.showMessageDialog(frame, "Player " + currentPlayer + " wins!");
                                resetGame();
                            } else if (isBoardFull()) {
                                JOptionPane.showMessageDialog(frame, "It's a draw!");
                                resetGame();
                            } else {
                                switchPlayer();
                            }
                        }
                    }
                });
                frame.add(buttons[row][col]);
            }
        }
    }

    private boolean checkForWin() {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                    (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }
        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                board[i][j] = ' ';
                buttons[i][j].setBackground(Color.BLACK);
            }
        }
        currentPlayer = 'X';
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToeGUI());
    }
}
