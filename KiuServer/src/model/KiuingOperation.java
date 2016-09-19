package model;

/**
 * Created by spronghi on 15/09/16.
 */
public class KiuingOperation {
    private int id;
    private boolean done;
    private Operation operation;
    private int kiuingId;

    public KiuingOperation(){}
    public KiuingOperation(boolean done, Operation operation, int kiuingId) {
        this.done = done;
        this.operation = operation;
        this.kiuingId = kiuingId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public int getKiuing() {
        return kiuingId;
    }

    public void setKiuing(int kiuingId) {
        this.kiuingId = kiuingId;
    }
}
