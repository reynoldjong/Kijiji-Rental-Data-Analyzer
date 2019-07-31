package assignment3;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/chartview")
public class ChartViewServlet extends HttpServlet {
  private ArrayList<String> columns;
  private Database db;

  public void init() {
    this.db = new ListingDatabase();
    buildColumnArray();
  }
  // [0: Url, 1: Addr, 2: Price, 3: Unit Type, 4: Bedrooms, 5: Bathrooms,
  // 6: Parking Included, 7: Move-In Date, 8: Pet Friendly, 9: Size (sqft),
  // 10: Furnished, 11: Smoking Permitted, 12: Hydro Included, 13: Heat
  // Included,
  // 14: Water Included, 15: Cable/TV Included, 16: Internet Included,
  // 17: Landline Included, 18: Yard, 19: Balcony, 20: Elevator in Building]

  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    db.connect();
    HashMap<String, ArrayList<String>> dict = db.getAllRows();
    String reply = "{";
    for (String key : dict.keySet()) {
      ArrayList<String> row = dict.get(key);
      String rowResult = listToJsonString(row);
      reply += "\""+key+"\":"+rowResult+",";
    }
    if (reply.endsWith(","))
      reply = reply.substring(0, reply.length() - 1);
    reply += "}";
    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");
    PrintWriter writer = resp.getWriter();
    writer.write(reply);
    db.close();
  }

  private String listToJsonString(ArrayList<String> array) {
    String reply = "{";
    int i = 0;
    while (i < columns.size()) {
      reply += "\""+ columns.get(i) + "\":\"" + array.get(i) + "\",";
      i++;
    }
    if (reply.endsWith(","))
      reply = reply.substring(0, reply.length() - 1);
    reply += "}";
    return reply;
  }

  private void buildColumnArray() {
    this.columns = new ArrayList<>();
    String[] headers =
        {"Url", "Address", "Price", "Unit Type", "Bedrooms", "Bathrooms",
            "Parking Included", "Move-In Date", "Pet Friendly", "Size (sqft)",
            "Furnished", "Smoking Permitted", "Hydro Included", "Heat Included",
            "Water Included", "Cable/TV Included", "Internet Included",
            "Landline Included", "Yard", "Balcony", "Elevator in Building"};
    for (String s : headers) {
      columns.add(s);
    }
  }
  
  public static void main(String[] args) {
    /*
    HashMap<String, ArrayList<String>> testHash = new HashMap<>();
    ArrayList<String> value1 = new ArrayList<>();
    value1.add("test url");
    value1.add("123 fake street");
    value1.add("$1000");
    value1.add("House");
    value1.add("3");
    value1.add("2");
    value1.add("1"); // parking
    value1.add("aug 12, 2019");
    value1.add("Yes");
    value1.add("1200");
    value1.add("Yes");
    value1.add("Outdoor Only"); // smoking
    value1.add("Yes");
    value1.add("Yes");
    value1.add("Yes");
    value1.add("Yes");
    value1.add("Yes");
    value1.add("No"); // landline
    value1.add("No");
    value1.add("No");
    value1.add("No");
    testHash.put("title1", value1);
    
    ArrayList<String> value2 = new ArrayList<>();
    value2.add("www.fakehouse.com");
    value2.add("321 real road ave");
    value2.add("$9001");
    value2.add("Condo");
    value2.add("3");
    value2.add("2");
    value2.add("1"); // parking
    value2.add("Sept 12, 2019");
    value2.add("Yes");
    value2.add("1200");
    value2.add("Yes");
    value2.add("Outdoor Only"); // smoking
    value2.add("Yes");
    value2.add("Yes");
    value2.add("Yes");
    value2.add("Yes");
    value2.add("Yes");
    value2.add("No"); // landline
    value2.add("No");
    value2.add("No");
    value2.add("No");
    testHash.put("title2", value2);
    
    ChartViewServlet test = new ChartViewServlet();
    String reply = "{";
    for (String key : testHash.keySet()) {
      ArrayList<String> row = testHash.get(key);
      String rowResult = test.listToJsonString(row);
      reply += "\""+key+"\":"+rowResult+",";
    }
    if (reply.endsWith(","))
      reply = reply.substring(0, reply.length() - 1);
    reply += "}";
    System.out.println(reply);
    */
  }
}
