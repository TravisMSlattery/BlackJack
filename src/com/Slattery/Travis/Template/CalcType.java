package com.Slattery.Travis.Template;

public enum CalcType {
    SUBTRACT_BAL {
        @Override
        public double updateBalance(double betAmount, double balance) {
            return balance - betAmount;
        }
    },
    ADD_BAL{
        @Override
        public double updateBalance(double betAmount, double balance) {
            return balance + betAmount;
        }
    },
    WIN_BAL{
        @Override
        public double updateBalance(double betAmount, double balance) {
            return  balance + (betAmount * 2);
        }
    },
    BJ_BAL {
        @Override
        public double updateBalance( double betAmount, double balance){
            return (balance + betAmount * 2.5f);
        }
    };

    public abstract double updateBalance(double betAmount, double balance);
}
