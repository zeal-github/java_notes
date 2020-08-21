public class Salary {
    public static void main(String[] args) {

        
    }
}

abstract class Income{
    protected float income;
    private float tax_rate;

    public Income(float income){
        this.income = income;
    }

    public abstract void set_tax(float rate);
    public float get_tax(){
        return this.tax_rate * this.income
    }
}

public class RoyaltyIncome extends Income{
    public RoyaltyIncome(float income){
        super(income);
    }
}