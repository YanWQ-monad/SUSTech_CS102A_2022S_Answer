import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Customer {
    private static int cnt;
    private int id;
    private String name;
    private ArrayList<Product> shoppingCart;
    private float wallet;

    private HashMap<Product, Store> storeOfProduct = new HashMap<>();

    public Customer(String name, float wallet) {
        this.name = name;
        this.wallet = wallet;
        this.shoppingCart = new ArrayList<>();

        Customer.cnt++;
        this.id = Customer.cnt;
    }

    public boolean rateProduct(Product product, int rating) {
        return product.setRating(rating);
    }

    public void updateWallet(float amount) {
        this.wallet += amount;
    }

    public boolean purchaseProduct(Store store, Product product) {
        if (this.wallet < product.getPrice() || !store.hasProduct(product))
            return false;
        this.updateWallet(- product.getPrice());
        this.shoppingCart.add(product);
        this.storeOfProduct.put(product, store);
        store.transact(product, 0);
        return true;
    }

    public void viewShoppingCart(SortBy sortMethod) {
        ArrayList<Product> display = (ArrayList<Product>) this.shoppingCart.clone();
        if (sortMethod == SortBy.Rating)
            display.sort(Comparator.comparing(Product::getAvgRating));
        else if (sortMethod == SortBy.Price)
            display.sort(Comparator.comparing(Product::getPrice));

        for (Product product : display)
            System.out.println(product);
    }

    public boolean refundProduct(Product product) {
        if (!this.shoppingCart.contains(product))
            return false;
        this.updateWallet(product.getPrice());
        this.shoppingCart.remove(product);
        Store store = this.storeOfProduct.get(product);
        store.transact(product, 1);
        return true;
    }
}
