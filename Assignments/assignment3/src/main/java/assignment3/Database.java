package assignment3;

import java.util.ArrayList;
import java.util.HashMap;

public interface Database {
  void deleteAll();
  void insert(String row);
  void update(String row, String column, String value);
  void connect();
  void close();
  HashMap<String, ArrayList<String>> getAllRows();
}
