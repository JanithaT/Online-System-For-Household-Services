package sample;

public class HouseOwnersTable {
    int ho_id,ho_mobile, ho_homeno;
    String ho_fullname,ho_email,ho_address;

    public HouseOwnersTable(int ho_id,String ho_fullname, int ho_mobile, int ho_homeno, String ho_email, String ho_address) {
        this.ho_id = ho_id;
        this.ho_mobile = ho_mobile;
        this.ho_homeno = ho_homeno;
        this.ho_fullname = ho_fullname;
        this.ho_email = ho_email;
        this.ho_address = ho_address;
    }

    public int getHo_id() {
        return ho_id;
    }

    public void setHo_id(int ho_id) {
        this.ho_id = ho_id;
    }

    public int getHo_mobile() {
        return ho_mobile;
    }

    public void setHo_mobile(int ho_mobile) {
        this.ho_mobile = ho_mobile;
    }

    public int getHo_homeno() {
        return ho_homeno;
    }

    public void setHo_homeno(int ho_homeno) {
        this.ho_homeno = ho_homeno;
    }

    public String getHo_fullname() {
        return ho_fullname;
    }

    public void setHo_fullname(String ho_fullname) {
        this.ho_fullname = ho_fullname;
    }

    public String getHo_email() {
        return ho_email;
    }

    public void setHo_email(String ho_email) {
        this.ho_email = ho_email;
    }

    public String getHo_address() {
        return ho_address;
    }

    public void setHo_address(String ho_address) {
        this.ho_address = ho_address;
    }
}
