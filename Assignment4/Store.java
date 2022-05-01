import java.util.ArrayList;

public class Store {
    private static int cnt; // initialized to 0, and will increase by 1 when the constructor is called.
    private int id;  // unique for each store and the value is set to cnt.
    private String name;
    private ArrayList<Product> productList;
    private float income;

    public Store(String name) {
        this(name, null, 0);
    }

    public Store(String name, ArrayList<Product> productList, float income) {
        if (productList == null)
            productList = new ArrayList<>();

        this.name = name;
        this.productList = productList;
        this.income = income;

        Store.cnt++;
        this.id = Store.cnt;
    }

    public boolean hasProduct(Product product) {
        return this.productList.contains(product);
    }

    public boolean addProduct(Product product) {
        if (hasProduct(product))
            return false;
        this.productList.add(product);
        return true;
    }

    public boolean removeProduct(Product product) {
        return this.productList.remove(product);
    }

    public ArrayList<Product> getProductList() {
        return this.productList;
    }

    public void transact(Product product, int method) {
        if (method == 0) {
            this.productList.remove(product);
            this.income += product.getPrice();
        }
        else {
            this.productList.add(product);
            this.income -= product.getPrice();
        }
    }
}
