package nz.ac.auckland.se281;

public class AverageStrategy implements Strategy{

    @Override
    public String getFingers() {
        int fingerInt = Utils.getRandomNumber(1, 5);
        return Integer.toString(fingerInt);
    }

    @Override
    public String getSum(int fingerInt) {
        int sumInt = fingerInt + getAverage();
        Math.round()

    }

}
