package tamagoshis;

public class Lunatique extends Tamagoshi {
    public boolean consommeEnergie() {
        if (Math.random() > 0.5D) {
            --this.energy;
        }

        return super.consommeEnergie();
    }

    public boolean consommeFun() {
        if (Math.random() > 0.5D) {
            --this.fun;
        }

        return super.consommeFun();
    }

    public Lunatique(String name) {
        super(name);
    }
}
