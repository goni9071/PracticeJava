package model;

public class Audio {
  private int modelNo;

  public int getModelNo() {
    return modelNo;
  }

  public void setModelNo(int modelNo) {
    this.modelNo = modelNo;
  }

  @Override
  public String toString() {
    return "modelNo:" + modelNo;
  }

}
