
import com.dei.isassignment.Car;
import com.dei.isassignment.CarsDatabase;
import com.dei.isassignment.Owner;
import com.dei.isassignment.OwnersDatabase;
import com.google.protobuf.util.JsonFormat;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Common utilities for the RouteGuide demo.
 */
public class DataConversionUtil {

  /**
   * Gets the default features file from classpath.
   */
  public static URL getCarsDBFile() {
    return DataConversionServer.class.getResource("cars_db.json");
  }

  /**
   * Gets the default features file from classpath.
   */
  public static URL getOwnersDBFile() {
    return DataConversionServer.class.getResource("owners_db.json");
  }

  /**
   * Parses the JSON input file containing the list of features.
   */
  public static List<Car> parseCars(URL file) throws IOException {
    InputStream input = file.openStream();
    try {
      Reader reader = new InputStreamReader(input, Charset.forName("UTF-8"));
      try {
        CarsDatabase.Builder carsDatabase = CarsDatabase.newBuilder();
        JsonFormat.parser().merge(reader, carsDatabase);
        return carsDatabase.getCarsList();
      } finally {
        reader.close();
      }
    } finally {
      input.close();
    }
  }

  /**
   * Parses the JSON input file containing the list of features.
   */
  public static List<Owner> parseOwners(URL file) throws IOException {
    InputStream input = file.openStream();
    try {
      Reader reader = new InputStreamReader(input, Charset.forName("UTF-8"));
      try {
        OwnersDatabase.Builder ownersDatabase = OwnersDatabase.newBuilder();
        JsonFormat.parser().merge(reader, ownersDatabase);
        return ownersDatabase.getOwnersList();
      } finally {
        reader.close();
      }
    } finally {
      input.close();
    }
  }
}
