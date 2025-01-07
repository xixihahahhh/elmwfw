package elm.pojo;

public class Deliveryaddress {
    private Integer daid;

    private String contactname;

    private Integer contactsex;

    private String contacttel;

    private String address;

    private String userid;

    public Integer getDaid() {
        return daid;
    }

    public void setDaid(Integer daid) {
        this.daid = daid;
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname == null ? null : contactname.trim();
    }

    public Integer getContactsex() {
        return contactsex;
    }

    public void setContactsex(Integer contactsex) {
        this.contactsex = contactsex;
    }

    public String getContacttel() {
        return contacttel;
    }

    public void setContacttel(String contacttel) {
        this.contacttel = contacttel == null ? null : contacttel.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}