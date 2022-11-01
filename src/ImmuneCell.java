public class ImmuneCell extends Cell {
    private int immunityTime = Main.IMMUNITY_TIME;

    public ImmuneCell() {
        type = "I";
    }

    public ImmuneCell(int immunity) {
        immunityTime = immunity;
        type = "I";
    }

    public int getImmunityTime() {
        return immunityTime;
    }

    public void immunityStep() {
        immunityTime--;
    }
}
