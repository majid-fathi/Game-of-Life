import javax.swing.*;
import java.awt.*;
import java.util.Random;

class Universe extends JFrame {
    private int width;
    private int height;
    private boolean[][] map;

    private int safeAreaUp = 40;
    private int safeAreaLeft = 10;

    private int scale = 10; // for create large pixels

    Universe(int width, int height) {
        this.width = width;
        this.height = height;
        map = new boolean[height][width];
        cellsInit();
        // ----- window
        setSize(width * scale, height * scale);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0,0, width * scale + safeAreaLeft + 10, height * scale + safeAreaUp + 10);
        setTitle("Game of Life");
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        // border
        g.setColor(Color.GREEN);
        g.drawRect(safeAreaLeft - 1, safeAreaUp - 1, width * scale + 1, height * scale + 1);

        // pixels
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j]) {
                    g.setColor(Color.RED);
                    g.fillRect(j * scale + safeAreaLeft, i * scale + safeAreaUp, scale, scale);
                } else {
                    g.setColor(Color.WHITE);
                    g.fillRect(j * scale + safeAreaLeft, i * scale + safeAreaUp, scale, scale);
                }
            }
        }
    }

    void evolution(){
        boolean[][] newMap = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int lives = 0;
                // ---- calculate 8 neighbours
                if (map[(i - 1 + height) % height][(j - 1 + width) % width]) lives++;
                if (map[(i - 1 + height) % height][(j + width) % width]) lives++;
                if (map[(i - 1 + height) % height][(j + 1 + width) % width]) lives++;
                if (map[(i + height) % height][(j - 1 + width) % width]) lives++;
                if (map[(i + height) % height][(j + 1 + width) % width]) lives++;
                if (map[(i + 1 + height) % height][(j - 1 + width) % width]) lives++;
                if (map[(i + 1 + height) % height][(j + width) % width]) lives++;
                if (map[(i + 1 + height) % height][(j + 1 + width) % width]) lives++;

                // Any live cell with two or three live neighbors survives.
                // Any dead cell with three live neighbors becomes a live cell.
                // All other live cells die in the next generation. Similarly, all other dead cells stay dead.
                newMap[i][j] = (lives == 3 || (lives == 2 && map[i][j]));
            }
        }

        map = newMap;
    }

    private void cellsInit(){
        // initiate first cells, 85 percent live
        Random random = new Random();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++)
                map[i][j] = random.nextInt(100) > 85;
        }
    }
}
