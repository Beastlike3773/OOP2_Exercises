package item;

public class Item {
  private String date;
  private Integer unit;
  private Integer current;
  private Integer interactive;
  
  /**
   * Creates an empty Item instance.
   */
  public Item() {
    this.date = "";
    this.unit = 0;
    this.current = 0;
    this.interactive = 0;
  }

  /**
   * Creates an  Item instance.
   */
  public Item(String date, Integer unit, Integer current, Integer interactive) {
    this.date = date;
    this.unit =  unit;
    this.current = current;
    this.interactive = interactive;
  }
  
  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public Integer getUnit() {
    return unit;
  }

  public void setUnit(int unit) {
    this.unit = unit;
  }

  public Integer getCurrent() {
    return current;
  }

  public void setCurrent(int current) {
    this.current = current;
  }

  public Integer getInteractive() {
    return interactive;
  }

  public void setInteractive(int interactive) {
    this.interactive = interactive;
  }

  @Override
  public String toString() {
    return "Item [current=" + current + ", date=" + date + ", interactive=" + interactive
        + ", unit=" + unit + "]";
  }
}

