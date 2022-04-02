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
        System.out.printf("[id] %d  [type] %-10s [name] %-10s [size] %2d (Inches) %5.2f $\n",
                this.id, this.type, this.name, this.size, this.price);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
