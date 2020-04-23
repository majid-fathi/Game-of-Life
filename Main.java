public class Main {
    public static void main(String[] args) throws InterruptedException {
        Universe universe = new Universe(50,50);
        for (int i = 0; i < 1000; i++) {
            universe.repaint();
            universe.evolution();
            Thread.sleep(200);
        }
    }
}
