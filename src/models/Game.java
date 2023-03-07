package src.models;

import java.util.Arrays;
import java.util.Objects;

public class Game implements Comparable<Game>, CsvWriter {
    private String name;
    private Consoles[] supportedDevices;
    private double price;

    public Game(String name, Consoles[] supportedDevices, double price) {

        if (name.isEmpty() || name == null)
            throw new IllegalArgumentException("name can not be empty nor null");
        if (supportedDevices.length == 0)
            throw new IllegalArgumentException("there most be at least one supported game device");
        if (price < 0)
            throw new IllegalArgumentException("price must be positive");

        this.name = name;
        this.supportedDevices = Arrays.copyOf(supportedDevices, supportedDevices.length);
        this.price = price;
    }

    public Game(Game source) {
        this.name = source.getName();
        this.supportedDevices = source.getSupportedDevices();
        this.price = source.getPrice();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("game name can not be null nor empty");
        this.name = name;
    }

    public Consoles[] getSupportedDevices() {
        return Arrays.copyOf(this.supportedDevices, this.supportedDevices.length);
    }
    
    public int getNumberOfSupportedDevices() {
        return this.supportedDevices.length;
    }

    public void setSupportedDevices(Consoles[] supportedDevices) {
        if (supportedDevices.length == 0)
            throw new IllegalArgumentException("there most be at least one supported game device");
        this.supportedDevices = Arrays.copyOf(supportedDevices, supportedDevices.length);
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        if (price < 0)
            throw new IllegalArgumentException("price must be a positive number");
        this.price = price;
    }

    @Override
    public String toCsv(char separator) {
        return this.name + separator
             + getDevicesCsv(separator) + separator
             +  this.price;
    }

    public String getDevicesCsv(char separator) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(supportedDevices)
              .forEach(device -> {
                sb.append(device);
                sb.append(separator);
              });
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    @Override
    public String toString() {
        return this.name 
            + "\n- " + this.price
            + "\n- " + Arrays.toString(supportedDevices);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Game)) {
            return false;
        }
        Game game = (Game) o;
        return Objects.equals(name, game.name) 
            && Arrays.equals(supportedDevices, game.supportedDevices) 
            && price == game.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, supportedDevices, price);
    }

    @Override
    public int compareTo(Game o) {
        int nameComparison = this.name.compareTo(o.getName());
        if (nameComparison == 0 || this.equals(o))
            return (int) (this.price - o.getPrice());
        return nameComparison;
    }
}