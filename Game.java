import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {
    private Board board;
    private int row1 = -1, col1 = -1;
    private int row2 = -1, col2 = -1;
    private boolean isGameOver = false;
    private JFrame frame;
    private JPanel boardPanel;
    private JButton[][] buttons;

    public Game(Board board) {
        this.board = board;
        this.frame = new JFrame("Concentration Game");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(600, 600);
        this.boardPanel = new JPanel();
        this.buttons = new JButton[3][4];
        boardPanel.setLayout(new GridLayout(3, 4));

        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j] = new JButton("_____");
                final int row = i;
                final int col = j;

                
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleTileClick(row, col);
                    }
                });
                boardPanel.add(buttons[i][j]);
            }
        }

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    
    private void handleTileClick(int row, int col) {
        if (isGameOver) return; 
        if (board.getGameboard()[row][col].isShowingValue() || board.getGameboard()[row][col].matched()) {
            return; 
        }

        if (row1 == -1 && col1 == -1) {
            row1 = row;
            col1 = col;
            board.showValue(row, col);
            updateBoard();
        } else if (row2 == -1 && col2 == -1) {
            row2 = row;
            col2 = col;
            board.showValue(row, col);
            updateBoard();

           
            String result = board.checkForMatch(row1, col1, row2, col2);
            JOptionPane.showMessageDialog(frame, result);

            
            new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (board.allTilesMatch()) {
                        JOptionPane.showMessageDialog(frame, "Game Over! All tiles matched.");
                        isGameOver = true;
                    }
                    row1 = col1 = row2 = col2 = -1;  
                    updateBoard();  
                }
            }).start();
        }
    }

 
    private void updateBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                Tile tile = board.getGameboard()[i][j];
                if (tile.isShowingValue()) {
                    buttons[i][j].setText(tile.getValue());
                } else if (tile.matched()) {
                    buttons[i][j].setText("  *  ");
                } else {
                    buttons[i][j].setText("_____");
                }
            }
        }
    }
}
