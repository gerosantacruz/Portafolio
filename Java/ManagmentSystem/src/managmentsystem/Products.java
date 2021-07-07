package managmentsystem;

/**
 *
 * @author gerosantacruz
 */
public class Products {
    
    private int id;
    private String name;
    private float price;
    private String addDate;
    private byte[] picture;
    
    public Products(int pid, String pname, float pprice, String pAddDate, byte[] pimg){
        this.id = pid;
        this.name = pname;
        this.price = pprice;
        this.addDate = pAddDate;
        this.picture = pimg;
    }
    
    public int getId(){
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @return the addDate
     */
    public String getAddDate() {
        return addDate;
    }

    /**
     * @return the picture
     */
    public byte[] getPicture() {
        return picture;
    }
    
}
