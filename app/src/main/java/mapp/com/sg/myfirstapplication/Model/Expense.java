package mapp.com.sg.myfirstapplication.Model;

public class Expense {
    int id;
    String memo;
    Double money;


    public Expense(int id, String memo, double money) {
        this.id = id;
        this.memo = memo;
        this.money = money;
    }

    public int getId() {
        return id;
    }


    public String getMemo() {
        return memo;
    }

    public Double getMoney() {
        return money;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
    public void setMoney(Double money) {
        this.money = money;
    }
}

