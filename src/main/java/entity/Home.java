package entity;

/**
 * @author sheol on 9/26/17 at 4:47 PM
 * @project SpringRestStarter
 */
public class Home {
    private String test;
    private int id;

    public int getId() {
        return id;
    }

    public String getTest() {
        return test;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
