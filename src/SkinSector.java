import java.util.Random;

public class SkinSector {
    private Cell[][] sector;
    private int size;

    public SkinSector(int size) {
        this.size = size;
        sector = new Cell[size][size];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                sector[i][j] = new HealthyCell();
            }
        }
        sector[this.size / 2][this.size / 2] = new InfectedCell();
    }

    public void infect(Cell[][] sectorStep, int x, int y) {
        Random rand = new Random();
        final int NEIGHBOURS = 8;
        int counter = 0;
        int mark = rand.nextInt(NEIGHBOURS);
        int xInfected = 0, yInfected = 0;

        while (counter < NEIGHBOURS) {
            switch (mark) {
                case 0:
                    xInfected = x - 1;
                    yInfected = y - 1;
                    break;

                case 1:
                    xInfected = x;
                    yInfected = y - 1;
                    break;

                case 2:
                    xInfected = x + 1;
                    yInfected = y - 1;
                    break;

                case 3:
                    xInfected = x + 1;
                    yInfected = y;
                    break;

                case 4:
                    xInfected = x + 1;
                    yInfected = y + 1;
                    break;

                case 5:
                    xInfected = x;
                    yInfected = y + 1;
                    break;

                case 6:
                    xInfected = x - 1;
                    yInfected = y + 1;
                    break;

                case 7:
                    xInfected = x - 1;
                    yInfected = y;
                    break;
            }

            if ((xInfected != -1) && (xInfected != size) && (yInfected != -1) && (yInfected != size)) {
                if (tryToInfect((InfectedCell) sector[x][y], sector[xInfected][yInfected])) {
                    sectorStep[xInfected][yInfected] = new InfectedCell();
                    break;
                }
            }
            counter++;
            mark++;
            mark %= NEIGHBOURS;
        }
    }

    private boolean tryToInfect(InfectedCell infected, Cell cell) {
        double test = Math.random();
        if ((cell instanceof HealthyCell) && (test <= infected.getChanceToInfect())) {
            return true;
        }
        return false;
    }

    public void simulateStep() {
        Cell[][] nextSectorStep = new Cell[size][size];

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (nextSectorStep[i][j] == null) {
                    nextSectorStep[i][j] = sector[i][j];
                }

                if (sector[i][j] instanceof InfectedCell) {
                    infect(nextSectorStep, i, j);
                    if (((InfectedCell) sector[i][j]).getInfectionTime() != 0) {
                        ((InfectedCell) nextSectorStep[i][j]).infectionStep();
                    } else {
                        nextSectorStep[i][j] = new ImmuneCell();
                    }
                } else if (sector[i][j] instanceof ImmuneCell) {
                    if (((ImmuneCell) sector[i][j]).getImmunityTime() != 0) {
                        ((ImmuneCell) nextSectorStep[i][j]).immunityStep();
                    } else {
                        nextSectorStep[i][j] = new HealthyCell();
                    }
                }
            }
        }

        sector = nextSectorStep;
    }

    public boolean showStep() {
        int healthy = size * size, infected = 0, immune = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(sector[i][j] + " ");
                if (sector[i][j] instanceof InfectedCell) {
                    infected++;
                    healthy--;
                } else if (sector[i][j] instanceof ImmuneCell) {
                    immune++;
                    healthy--;
                }
            }
            System.out.println();
        }
        System.out.println(String.format("Amount of cells: health = %d, infected = %d, immune = %d",
                healthy, infected, immune));
        System.out.println("\n");
        if (infected > 0) {
            return true;
        }
        return false;
    }
}
