package com.spronghi.kiu.kiuing;

/**
 * Created by spronghi on 16/09/16.
 */
public class KiuingOperation {
    private int id;
    private boolean done;
    private String operation;
    private int kiuingId;

    public KiuingOperation(){}
    public KiuingOperation(boolean done, String operation, int kiuingId) {
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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperationMessage(){
        if(operation.equals(Kiuing.FINISHED)){
            return "finished the Kiu";
        } else if(operation.equals(Kiuing.GOING)){
            return "is going";
        } else if(operation.equals(Kiuing.ARRIVED)){
            return "arrived";
        } else if(operation.equals(Kiuing.KIUING)){
            return "is Kiuing";
        } else {
            return "is lost";
        }

    }
    public int getKiuing() {
        return kiuingId;
    }

    public void setKiuing(int kiuingId) {
        this.kiuingId = kiuingId;
    }

    @Override
    public String toString() {
        return "KiuingOperation{" +
                "id=" + id +
                ", done=" + done +
                ", operation='" + operation + '\'' +
                ", kiuingId=" + kiuingId +
                '}';
    }
}
