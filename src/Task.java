import java.util.Date;

public class Task {
    private int number;
    private String descrption;
    private int statu;
    private Date limite;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    public int getStatu() {
        return statu;
    }

    public void setStatu(int statu) {
        this.statu = statu;
    }

    public Date getLimite() {
        return limite;
    }

    public void setLimite(Date limite) {
        this.limite = limite;
    }

    public Task(int number, String descrption, int statu, Date limite) {
        this.number = number;
        this.descrption = descrption;
        this.statu = statu;
        this.limite = limite;
    }

    public void display() {
        System.out.println(getNumber() +" - "+ getDescrption());
    }
}
