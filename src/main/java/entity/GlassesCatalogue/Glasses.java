package entity.GlassesCatalogue;

import entity.Product;


public class Glasses extends Product{
    private double leftDiopter;
    private double rightDiopter;

    public double getLeftDiopter() {
        return leftDiopter;
    }

    public void setLeftDiopter(double leftDiopter) {
        this.leftDiopter = leftDiopter;
    }

    public double getRightDiopter() {
        return rightDiopter;
    }

    public void setRightDiopter(double rightDiopter) {
        this.rightDiopter = rightDiopter;
    }
}
