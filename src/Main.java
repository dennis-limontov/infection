public class Main {
    public static final double CHANCE_TO_INFECT = 0.7;
    public static final int INFECTION_TIME = 5;
    public static final int IMMUNITY_TIME = 3;
    public static final int SKIN_SECTOR_SIZE = 21;

    public static void main(String[] args) {
        SkinSector ss = new SkinSector(SKIN_SECTOR_SIZE);
        int steps = 50;

        for (int i = 1; i <= steps; i++) {
            ss.simulateStep();
            System.out.println("Step " + i);
            if (!ss.showStep()) {
                break;
            }
        }
    }
}
