package model;

public class Car {
  private int carNo;
  private String color;
  private String carName;
  private int speed;
  private Audio audio;

  public void setCarName(String carName) {
    this.carName = carName;
  }

  public String getCarName() {
    return carName;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public int getCarNo() {
    return carNo;
  }

  public void setCarNo(int carNo) {
    this.carNo = carNo;
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  @Override
  public String toString() {
    return "carNo:" + carNo + ",carName:" + carName + ",color:" + color + ",audio:" + audio
        + ",speed:" + speed;
  }

  public Audio getAudio() {
    return audio;
  }

  public void setAudio(Audio audio) {
    this.audio = audio;
  }
}
