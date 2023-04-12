import java.util.Random;

public class Pierscien{
    private int ktory;
    private boolean czyZalatwion = false;
    private int deadline;

    public Pierscien(int ktory) {
        Random random =new Random();
        this.ktory = ktory;
        deadline = random.nextInt(730,880);
    }
    public Pierscien(int ktory,int ilosc_czasu_do_deadline) {
        this.ktory = ktory;
        this.deadline = ilosc_czasu_do_deadline;
    }

    public int getKtory() {
        return ktory;
    }

    public void setKtory(int ktory) {
        this.ktory = ktory;
    }

    public boolean isCzyZalatwion() {
        return czyZalatwion;
    }

    public void setCzyZalatwion(boolean czyZalatwion) {
        this.czyZalatwion = czyZalatwion;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "Pierscien{" +
                "ktory=" + ktory +", deadline="+deadline+
                '}';
    }
}