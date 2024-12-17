import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ChristmasFunGUI extends JPanel {
    private int[] snowX, snowY; // Coordinates for snowflakes
    private final int SNOWFLAKE_COUNT = 100;
    private String message = "MERRY CHRISTMAS!";
    private Color[] messageColors = {Color.RED, Color.GREEN, Color.WHITE};
    private int messageColorIndex = 0;

    public ChristmasFunGUI() {
        // Initialize snowflakes' positions
        snowX = new int[SNOWFLAKE_COUNT];
        snowY = new int[SNOWFLAKE_COUNT];
        Random rand = new Random();
        for (int i = 0; i < SNOWFLAKE_COUNT; i++) {
            snowX[i] = rand.nextInt(400); // Random X positions
            snowY[i] = rand.nextInt(400); // Random Y positions
        }

        // Timer for animation
        Timer timer = new Timer(100, e -> {
            updateSnowflakes();
            repaint();
        });
        timer.start();
    }

    private void updateSnowflakes() {
        Random rand = new Random();
        for (int i = 0; i < SNOWFLAKE_COUNT; i++) {
            snowY[i] += 5; // Move snowflakes down
            if (snowY[i] > 400) { // Reset to the top if out of bounds
                snowY[i] = 0;
                snowX[i] = rand.nextInt(400);
            }
        }
        // Blink the message colors
        messageColorIndex = (messageColorIndex + 1) % messageColors.length;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);

        // Draw snowflakes
        g.setColor(Color.WHITE);
        for (int i = 0; i < SNOWFLAKE_COUNT; i++) {
            g.fillOval(snowX[i], snowY[i], 5, 5);
        }

        // Draw Christmas Tree
        drawChristmasTree(g);

        // Draw animated Merry Christmas message
        g.setColor(messageColors[messageColorIndex]);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString(message, 100, 350);
    }

    private void drawChristmasTree(Graphics g) {
        g.setColor(Color.GREEN);
        int[] xPoints, yPoints;
        int startX = 200, startY = 50;
        int width = 100, height = 50;

        // Draw 3 tree layers
        for (int i = 0; i < 3; i++) {
            xPoints = new int[]{startX, startX - width / 2, startX + width / 2};
            yPoints = new int[]{startY, startY + height, startY + height};
            g.fillPolygon(xPoints, yPoints, 3);
            startY += height - 10;
            width += 50;
        }

        // Draw tree trunk
        g.setColor(new Color(102, 51, 0));
        g.fillRect(190, 220, 20, 40);

        // Add decorations
        g.setColor(Color.RED);
        g.fillOval(180, 80, 10, 10);
        g.fillOval(220, 100, 10, 10);
        g.fillOval(200, 130, 10, 10);
        g.fillOval(190, 160, 10, 10);
        g.fillOval(210, 190, 10, 10);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Christmas Fun!");
            ChristmasFunGUI panel = new ChristmasFunGUI();

            frame.add(panel);
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setVisible(true);
        });
    }
}
