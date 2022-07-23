package cinema.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Seat {
    private final int row;
    private final int column;
    private final int price;

    public Seat(){
        this.price = 0;
        this.row = 0;
        this.column = 0;
    }
    public Seat (int row, int column){
        this.row = row;
        this.column = column;
        this.price = row > 4 ? 8 : 10;

    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }
}