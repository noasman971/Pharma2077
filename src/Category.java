public class Category implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}