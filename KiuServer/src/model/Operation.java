package model;

/**
 * Created by spronghi on 15/09/16.
 */
public class Operation {
    private int id;
    private String operation;

    public Operation(int id, String operation) {
        this.id = id;
        this.operation = operation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", operation='" + operation + '\'' +
                '}';
    }
}
