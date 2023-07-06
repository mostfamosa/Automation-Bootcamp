package week2.CommonMethods.ContactExe;

import java.util.Objects;

public class PhoneNumber implements Comparable<PhoneNumber>, Cloneable {
    private String areaCode;
    private String number;

    public PhoneNumber(String areaCode, String number) {
        this.areaCode = areaCode;
        this.number = number;
    }

    public PhoneNumber(PhoneNumber phoneNumber) {
        this.areaCode = phoneNumber.areaCode;
        this.number = phoneNumber.number;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return areaCode.equals(that.areaCode) && number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(areaCode, number);
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "areaCode='" + areaCode + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    @Override
    public int compareTo(PhoneNumber phoneNumber) {
        int result = this.areaCode.compareTo(phoneNumber.getAreaCode());
        if (result == 0) {
            return this.number.compareTo(phoneNumber.getNumber());
        }
        return result;
    }


    @Override
    public PhoneNumber clone() {
        try {
            PhoneNumber clone = (PhoneNumber) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
