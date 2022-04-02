public class Food {
    private int id;
    private String name;
    private String type;
    private int size;
    private double price;

    public Food(int id, String name, String type, int size, double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.size = size;
        this.price = price;
    }

    public void getMenu() {
        System.out.printf("[id] %d  [type] %-10s [name] %-20s [size] %2d (Inches) %.2f $\n",
                id, type, name, size, price);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
