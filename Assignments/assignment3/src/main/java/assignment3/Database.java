package assignment3;

public interface Database {
  void deleteAll();
  void insert(String column, String value);
  void connect();
}
