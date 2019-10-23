
import com.dei.isassignment.Car;
import com.dei.isassignment.Owner;
import com.google.gson.Gson;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Common utilities for the RouteGuide demo.
 */
public class DataConversionUtil {

  /**
   * Parses the JSON input file containing the list of features.
   */
  public static List<Car> parseCars() throws IOException {

    Gson gson = new Gson();

    try (Reader reader = new FileReader("src/main/resources/cars.json")) {

      // Convert JSON File to Java Object
      Car [] cars = gson.fromJson(reader, Car[].class);
      return Arrays.asList(cars);

    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Parses the JSON input file containing the list of features.
   */
  public static List<Owner> parseOwners() throws IOException {
    Gson gson = new Gson();

    try (Reader reader = new FileReader("src/main/resources/owners.json")) {

      // Convert JSON File to Java Object
      Owner [] owners = gson.fromJson(reader, Owner[].class);
      return Arrays.asList(owners);

    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
