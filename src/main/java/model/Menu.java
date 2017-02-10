package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Menu {
  private String url;
  private String name;
  private Menu parent;
  private Map<String, Menu> menuMap = new LinkedHashMap<>();
  private int index = -1;

  public Menu(String name, String url) {
    setName(name);
    setUrl(url);
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Menu> getMenuList() {
    return new ArrayList<Menu>(menuMap.values());
  }

  public Menu addMenu(Menu menu) {
    menu.setIndex(this.menuMap.size());
    menu.setParent(this);
    this.menuMap.put(menu.getUrl(), menu);
    return this;
  }

  private void setParent(Menu parent) {
    this.parent = parent;
  }

  public Menu getParent() {
    if (parent == null) {
      return new Menu("홈", "/");
    }
    return parent;
  }

  private void setIndex(int index) {
    this.index = index;
  }

  public Menu getMenu(String url) {
    return this.menuMap.get(url);
  }

  public int getIndex() {
    return this.index;
  }

  @Override
  public String toString() {
    return name + "::" + url;
  }
}
