public class Resource {
    private String resourceName;
    private int quantity;

    public Resource(String resourceName, int quantity) {
        this.resourceName = resourceName;
        this.quantity = quantity;
    }

  public String getResourceName() {
        return resourceName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int amount) {
        if (amount > 0) {
            quantity += amount;
        }
    }
