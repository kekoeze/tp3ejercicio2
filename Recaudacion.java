package tp3.ejercicio2;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import au.com.bytecode.opencsv.CSVReader;

public class Recaudacion {

  public static List<String[]> encontrarNombre(Map<String, String> options, String nombre, int numeroGuardarArray,
      List<String[]> csvData) {
    List<String[]> results = new ArrayList<String[]>();
    if (options.containsKey(nombre)) {

      for (int i = 0; i < csvData.size(); i++) {
        if (csvData.get(i)[numeroGuardarArray].equals(options.get(nombre))) {
          results.add(csvData.get(i));
        }
      }

    }
    return results;
  }

  public static List<Map<String, String>> where(Map<String, String> options) throws IOException {
    List<String[]> csvData = new ArrayList<String[]>();
    CSVReader reader = new CSVReader(new FileReader("data.csv"));
    String[] row = null;

    while ((row = reader.readNext()) != null) {
      csvData.add(row);
    }

    reader.close();
    csvData.remove(0);
    Predicate<String[]> lambda = (String[] s) -> s[1].equals(options.get("company_name"));
    csvData = Filtro.filtrar(lambda, csvData);
    csvData = encontrarNombre(options, "company_name", 1, csvData);
    csvData = encontrarNombre(options, "city", 4, csvData);
    csvData = encontrarNombre(options, "state", 5, csvData);
    csvData = encontrarNombre(options, "round", 9, csvData);

    List<Map<String, String>> output = new ArrayList<Map<String, String>>();

    for (int i = 0; i < csvData.size(); i++) {
      Map<String, String> mapped = new HashMap<String, String>();
      mapped.put("permalink", csvData.get(i)[0]);
      mapped.put("company_name", csvData.get(i)[1]);
      mapped.put("number_employees", csvData.get(i)[2]);
      mapped.put("category", csvData.get(i)[3]);
      mapped.put("city", csvData.get(i)[4]);
      mapped.put("state", csvData.get(i)[5]);
      mapped.put("funded_date", csvData.get(i)[6]);
      mapped.put("raised_amount", csvData.get(i)[7]);
      mapped.put("raised_currency", csvData.get(i)[8]);
      mapped.put("round", csvData.get(i)[9]);
      output.add(mapped);
    }

    return output;
  }

  // hasta aca
  public static Map<String, String> findBy(Map<String, String> options)
      throws IOException, NoSuchEntryException {
    List<String[]> csvData = new ArrayList<String[]>();
    CSVReader reader = new CSVReader(new FileReader("data.csv"));
    String[] row = null;

    while ((row = reader.readNext()) != null) {
      csvData.add(row);
    }

    reader.close();
    csvData.remove(0);
    Map<String, String> mapped = new HashMap<String, String>();

    for (int i = 0; i < csvData.size(); i++) {
      // agregar expresion lambda
      if (options.containsKey("company_name")) {
        if (csvData.get(i)[1].equals(options.get("company_name"))) {
          mapped.put("permalink", csvData.get(i)[0]);
          mapped.put("company_name", csvData.get(i)[1]);
          mapped.put("number_employees", csvData.get(i)[2]);
          mapped.put("category", csvData.get(i)[3]);
          mapped.put("city", csvData.get(i)[4]);
          mapped.put("state", csvData.get(i)[5]);
          mapped.put("funded_date", csvData.get(i)[6]);
          mapped.put("raised_amount", csvData.get(i)[7]);
          mapped.put("raised_currency", csvData.get(i)[8]);
          mapped.put("round", csvData.get(i)[9]);
        } else {
          continue;
        }
      }

      if (options.containsKey("city")) {
        if (csvData.get(i)[4].equals(options.get("city"))) {
          mapped.put("permalink", csvData.get(i)[0]);
          mapped.put("company_name", csvData.get(i)[1]);
          mapped.put("number_employees", csvData.get(i)[2]);
          mapped.put("category", csvData.get(i)[3]);
          mapped.put("city", csvData.get(i)[4]);
          mapped.put("state", csvData.get(i)[5]);
          mapped.put("funded_date", csvData.get(i)[6]);
          mapped.put("raised_amount", csvData.get(i)[7]);
          mapped.put("raised_currency", csvData.get(i)[8]);
          mapped.put("round", csvData.get(i)[9]);
        } else {
          continue;
        }
      }

      if (options.containsKey("state")) {
        if (csvData.get(i)[5].equals(options.get("state"))) {
          mapped.put("permalink", csvData.get(i)[0]);
          mapped.put("company_name", csvData.get(i)[1]);
          mapped.put("number_employees", csvData.get(i)[2]);
          mapped.put("category", csvData.get(i)[3]);
          mapped.put("city", csvData.get(i)[4]);
          mapped.put("state", csvData.get(i)[5]);
          mapped.put("funded_date", csvData.get(i)[6]);
          mapped.put("raised_amount", csvData.get(i)[7]);
          mapped.put("raised_currency", csvData.get(i)[8]);
          mapped.put("round", csvData.get(i)[9]);
        } else {
          continue;
        }
      }

      if (options.containsKey("round")) {
        if (csvData.get(i)[9].equals(options.get("round"))) {
          mapped.put("permalink", csvData.get(i)[0]);
          mapped.put("company_name", csvData.get(i)[1]);
          mapped.put("number_employees", csvData.get(i)[2]);
          mapped.put("category", csvData.get(i)[3]);
          mapped.put("city", csvData.get(i)[4]);
          mapped.put("state", csvData.get(i)[5]);
          mapped.put("funded_date", csvData.get(i)[6]);
          mapped.put("raised_amount", csvData.get(i)[7]);
          mapped.put("raised_currency", csvData.get(i)[8]);
          mapped.put("round", csvData.get(i)[9]);
        } else {
          continue;
        }
      }

      return mapped;
    }

    throw new NoSuchEntryException();
  }

  public static void main(String[] args) {
    try {
      Map<String, String> options = new HashMap<String, String>();
      options.put("company_name", "Facebook");
      options.put("round", "a");
      System.out.print(Recaudacion.where(options).size());
    } catch (IOException e) {
      System.out.print(e.getMessage());
      System.out.print("error");
    }
  }
}

class NoSuchEntryException extends Exception {
}