import com.dei.isassignment.Car;
import com.dei.isassignment.Owner;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateJsonDb {
    public static void generateOwnersDb(int entries){
        Faker faker = new Faker();
        List<Owner> owners = new ArrayList<>();
        for(int i = 0; i < entries; i++){
            Owner owner = Owner.newBuilder()
                    .setAddress(faker.address().streetAddress())
                    .setId(i+1)
                    .setName(faker.name().fullName())
                    .setTelephone(faker.phoneNumber().cellPhone())
                    .build();
            owners.add(owner);
        }
        writeOwnersInFile(owners);
    }

    private static void writeOwnersInFile(List<Owner> owners) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(Writer writer = new FileWriter("src/main/resources/owners.json")){
            gson.toJson(owners, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateCarsDb(int entries, int maxOwners){
        Random random = new Random();

        Faker faker = new Faker();
        List<Car> cars = new ArrayList<>();

        for(int i = 0; i < entries; i++){
            int brandId = random.nextInt(10);
            Car car = Car.newBuilder()
                .setId(i +1)
                .setBrand(getBrand(brandId))
                .setModel(getModel(brandId))
                .setEngine(String.valueOf(random.nextInt(3000 - 1000) + 1000))
                .setSize(random.nextInt(5 - 2) + 2)
                .setPower(random.nextInt(600 - 80) + 80)
                .setConsumption((float) ((random.nextInt((int)((20-10)*4+1))+4*10) / 10.0))
                .setPlate(generatePlate())
                .setOwnerId(random.nextInt(maxOwners - 1) + 1)
                .build();
            cars.add(car);
        }
        writeCarsInFile(cars);
    }

    private static void writeCarsInFile(List<Car> cars) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(Writer writer = new FileWriter("src/main/resources/cars.json")){
            gson.toJson(cars, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getModel(int brandId) {
        switch (brandId){
            case 0:
                return getBMWModel();
            case 1:
                return getVolkswagenModel();
            case 2:
                return getAudiModel();
            case 3:
                return getHondaModel();
            case 4:
                return getVolvoModel();
            case 5:
                return getToyotaModel();
            case 6:
                return getFordModel();
            case 7:
                return getRenaultModel();
            case 8:
                return getLamborghiniModel();
            case 9:
                return getMercedesModel();
            case 10:
                return getAlfaRomeo();
            default:
                return null;
        }
    }

    private static String getAlfaRomeo() {
        Random random = new Random();
        switch (random.nextInt(5)){
            case 0:
                return "Spider";
            case 1:
                return "Brera";
            case 2:
                return "159";
            case 3:
                return "MiTo";
            case 4:
                return "Giulietta";
            default:
                return "";
        }
    }

    private static String getMercedesModel() {
        Random random = new Random();
        switch (random.nextInt(5)){
            case 0:
                return "SLK";
            case 1:
                return "AMG";
            case 2:
                return "A180";
            case 3:
                return "F1015";
            case 4:
                return "F125";
            default:
                return "";
        }
    }

    private static String getLamborghiniModel() {
        Random random = new Random();
        switch (random.nextInt(5)){
            case 0:
                return "Aventador";
            case 1:
                return "Gallardo";
            case 2:
                return "Veneno";
            case 3:
                return "Centenario";
            case 4:
                return "Murcielago";
            default:
                return "";
        }
    }

    private static String getRenaultModel() {
        Random random = new Random();
        switch (random.nextInt(5)){
            case 0:
                return "Captur";
            case 1:
                return "Clio";
            case 2:
                return "Espace";
            case 3:
                return "Express";
            case 4:
                return "Laguna";
            default:
                return "";
        }
    }

    private static String getFordModel() {
        Random random = new Random();
        switch (random.nextInt(5)){
            case 0:
                return "Focus";
            case 1:
                return "Fista";
            case 2:
                return "Mustang";
            case 3:
                return "GT";
            case 4:
                return "Escort";
            default:
                return "";
        }
    }

    private static String getToyotaModel() {
        Random random = new Random();
        switch (random.nextInt(5)){
            case 0:
                return "Corolla";
            case 1:
                return "Supra";
            case 2:
                return "GT86";
            case 3:
                return "Prius";
            case 4:
                return "Mirai";
            default:
                return "";
        }
    }

    private static String getVolvoModel() {
        Random random = new Random();
        switch (random.nextInt(5)){
            case 0:
                return "V30";
            case 1:
                return "V40";
            case 2:
                return "XC 90";
            case 3:
                return "XC 70";
            case 4:
                return "S60";
            default:
                return "";
        }
    }

    private static String getHondaModel() {
        Random random = new Random();
        switch (random.nextInt(5)){
            case 0:
                return "Civic";
            case 1:
                return "Element";
            case 2:
                return "Integra";
            case 3:
                return "NSX-R";
            case 4:
                return "Passport";
            default:
                return "";
        }
    }

    private static String getAudiModel() {
        Random random = new Random();
        switch (random.nextInt(5)){
            case 0:
                return "TT";
            case 1:
                return "A3";
            case 2:
                return "A4";
            case 3:
                return "A5";
            case 4:
                return "A6";
            default:
                return "";
        }
    }

    private static String getVolkswagenModel() {
        Random random = new Random();
        switch (random.nextInt(5)){
            case 0:
                return "Golf";
            case 1:
                return "Polo";
            case 2:
                return "Jetta";
            case 3:
                return "Scirocco";
            case 4:
                return "Passat";
            default:
                return "";
        }
    }

    private static String getBMWModel() {
        Random random = new Random();
        switch (random.nextInt(5)){
            case 0:
                return "320";
            case 1:
                return "M4";
            case 2:
                return "420";
            case 3:
                return "120";
            case 4:
                return "M3";
            default:
                return "";
        }
    }

    private static String getBrand(int brandId) {
        switch (brandId){
            case 0:
                return "BMW";
            case 1:
                return "Volkswagen";
            case 2:
                return "Audi";
            case 3:
                return "Honda";
            case 4:
                return "Volvo";
            case 5:
                return "Toyota";
            case 6:
                return "Ford";
            case 7:
                return "Renault";
            case 8:
                return "Lamborghini";
            case 9:
                return "Mercedes";
            case 10:
                return "AlfaRomeo";
            default:
                return null;
        }
    }

    private static String generatePlate() {
        Random random = new Random();

        char letter1 = (char)(random.nextInt(26) + 'A');
        char letter2 = (char)(random.nextInt(26) + 'A');
        char letter3 = (char)(random.nextInt(26) + 'A');

        int number1 = random.nextInt(10);
        int number2 = random.nextInt(10);
        int number3 = random.nextInt(10);

        return "" + letter1 + letter2 + letter3 + "-" + String.valueOf(number1) + String.valueOf(number2) + String.valueOf(number3);
    }
}
