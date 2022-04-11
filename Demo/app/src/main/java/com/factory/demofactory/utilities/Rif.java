package com.factory.demofactory.utilities;

public enum Rif {
    V(" V "), J(" J "), E(" E "), P(" P "), G(" G ");

    private String value;

    private Rif(String value){
        this.value = value;
    }

    @Override public String toString(){
        return value;
    }
}
