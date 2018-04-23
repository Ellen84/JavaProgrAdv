package be.pxl.student;

import java.util.Objects;

public class Temperature {
    private float temp;

    public Temperature (float t){
        this.temp = t;
    }

    public void setValue(float t){
        this.temp = t;
    }

    public float getValue(){
        return temp;
    }

    public boolean isBoiling(){
        if (temp >= 100){
            return true;
        }else{
            return false;
        }
    }

    public boolean isfreezing(){
        if (temp <= 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean equals(Object o){
       return o.equals(temp);
    }

    @Override
    public int hashCode() {

        return Objects.hash(temp);
    }
}
