package model;

public class PlayStation {
    private String id;
    private String type;   // PS2/PS3/PS4/PS5
    private String status; // available / rented

    public PlayStation(String id, String type, String status) {
        this.id = id;
        this.type = type;
        this.status = status;
    }

    public String getId() { return id; }
    public String getType() { return type; }
    public String getStatus() { return status; }

    public void setType(String type) { this.type = type; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "PlayStation{id='" + id + "', type='" + type + "', status='" + status + "'}";
    }
}
