package hiram.lavendimia.models;

public class Client {
    private int client_id;
    private String name, lastname, rfc;

    public Client(int client_id, String name, String lastname, String rfc) {
        this.client_id = client_id;
        this.name = name;
        this.lastname = lastname;
        this.rfc = rfc;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }
}
