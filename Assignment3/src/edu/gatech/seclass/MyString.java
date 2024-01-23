package edu.gatech.seclass;

public class MyString implements MyStringInterface {

    private String currentString;

    @Override
    public String getString() {
        return currentString;
    }

    @Override
    public void setString(String string) {
        if (string == null || string.equals(easterEgg) || string.isEmpty() || !string.matches(".*[a-zA-Z0-9]+.*")) {
            throw new IllegalArgumentException();
        }
        currentString = string;
    }

    @Override
    public int countAlphabeticWords() {
        if (currentString == null) {
            throw new NullPointerException();
        }
        return currentString.split("[^a-zA-Z]+").length;
    }

    @Override
    public String encrypt(int arg1, int arg2) {
        if (currentString == null || arg1 < 1 || arg1 >= 62 || arg2 < 1 || arg2 >= 62 || !isCoprime(arg1, 62)) {
            throw new IllegalArgumentException();
        }

        StringBuilder encrypted = new StringBuilder();
        for (char c : currentString.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                int x = getCharValue(c);
                int E = (arg1 * x + arg2) % 62;
                encrypted.append(getValueChar(E));
            } else {
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }

    @Override
    public void convertDigitsToNamesInSubstring(int firstPosition, int finalPosition) {
        if (currentString == null) {
            throw new NullPointerException();
        }
        if (firstPosition < 1 || firstPosition > finalPosition || finalPosition > currentString.length()) {
            throw new IllegalArgumentException();
        }

        StringBuilder result = new StringBuilder();
        String[] digitNames = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        for (int i = 0; i < currentString.length(); i++) {
            if (i >= firstPosition - 1 && i <= finalPosition - 1 && Character.isDigit(currentString.charAt(i))) {
                result.append(digitNames[currentString.charAt(i) - '0']);
            } else {
                result.append(currentString.charAt(i));
            }
        }
        currentString = result.toString();
    }

    private boolean isCoprime(int a, int b) {
        return gcd(a, b) == 1;
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    private int getCharValue(char c) {
        if (Character.isDigit(c)) return c - '0';
        if (Character.isUpperCase(c)) return c - 'A' + 10;
        return c - 'a' + 36;
    }

    private char getValueChar(int val) {
        if (val < 10) return (char) ('0' + val);
        if (val < 36) return (char) ('A' + val - 10);
        return (char) ('a' + val - 36);
    }
}