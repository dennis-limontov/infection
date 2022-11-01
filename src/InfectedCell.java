public class InfectedCell extends Cell {
    private double chanceToInfect = Main.CHANCE_TO_INFECT;
    private int infectionTime = Main.INFECTION_TIME;

    public InfectedCell() {
        type = "o";
    }

    public InfectedCell(double chance, int infection) {
        chanceToInfect = chance;
        infectionTime = infection;
        type = "o";
    }

    public double getChanceToInfect() {
        return chanceToInfect;
    }

    public int getInfectionTime() {
        return infectionTime;
    }

    public void infectionStep() {
        infectionTime--;
    }
}
