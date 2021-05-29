package mattingRitual;

public class Couple {

    private int leftMember;
    private int rightMember;

    public Couple(int leftMember, int rightMember) {
        this.leftMember = leftMember;
        this.rightMember = rightMember;
    }

    public Couple(String values) {
        String[] splitValues = values.split(",");
        leftMember = Integer.parseInt(splitValues[0]);
        rightMember = Integer.parseInt(splitValues[1]);
    }

    public void flip() {
        int stock = leftMember;
        leftMember = rightMember;
        rightMember = stock;
    }

    public int getLeftMember() {
        return leftMember;
    }

    public int getRightMember() {
        return rightMember;
    }

    @Override
    public String toString() {
        return "[" + leftMember +"," +rightMember + "]";
    }
}
