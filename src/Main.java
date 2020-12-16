import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Address {
    private String country;
    private String region;
    private String city;
    private String street;
    private String building;
    private String korpus;
    private String flat;

    public Address(String address){
        this.setAddress(address);
    }

    public void setAddress(String address) {
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(address);
        ArrayList<String> words = new ArrayList<>();

        while (matcher.find()) {
            words.add(matcher.group().toLowerCase());
        }

        if (words.size() < 6){
            System.out.println("Неверный адрес");
            return;
        }
        country = words.get(0);
        region = words.get(1);
        city = words.get(2);
        street = words.get(3);
        building = words.get(4);
        korpus = words.get(5);
        flat = words.get(6);
    }

    public String toString(){
        return "Страна: " + country + ", Регион: " + region + ", Город: " + city + ", Улица: " + street + ", Дом: " + building + ", Корпус: " + korpus + ", Квартира: " + flat;
    }
}

class Last {
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<String> newList;
    public Last(String string) {
        list.addAll(Arrays.asList(string.split("\\s")));
        newList = function();
    }

    public ArrayList<String> function() {
        ArrayList<String> current;
        for (int i = 0; i < list.size(); i++) {
            current = new ArrayList<>();
            ArrayList<String> res = getStrings(list, current, i);
            if (res != null) return res;
        }
        return null;
    }

    public boolean check (ArrayList<String> arrayList) {
        for (int i = 0; i < arrayList.size() - 1; i++) {
            if (Character.toLowerCase(arrayList.get(i).charAt(arrayList.get(i).length() - 1)) != Character.toLowerCase(arrayList.get(i + 1).charAt(0))) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<String> functionp2(ArrayList<String> left,ArrayList<String> current) {
        if (left.size() == 0 && check(current)) {
            return current;
        }
        ArrayList<String> current1;
        for (int i = 0; i < left.size(); i++) {
            current1 = new ArrayList<>(current);
            ArrayList<String> res = getStrings(left, current1, i);
            if (res != null) return res;
        }
        return null;
    }

    private ArrayList<String> getStrings(ArrayList<String> left, ArrayList<String> current1, int i) {
        ArrayList<String> left1;
        ArrayList<String> res;
        current1.add(left.get(i));
        left1 = new ArrayList<>(left);
        left1.remove(i);
        res = functionp2(left1, current1);
        if (res != null && check(res)) {
            return res;
        }
        return null;
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : newList) {
            stringBuilder.append(s).append(" ");
        }
        return String.valueOf(stringBuilder);
    }
}

class Person {
    private String name;
    private String secondName;
    private String surName;

    public Person(String name, String secondName, String surName) {
        this.name = name;
        this.secondName = secondName;
        this.surName = surName;
    }

    public String fio() {
        return String.format("%s %.1s. %.1s.", secondName, name, surName);
    }
}

class PhoneNumber {
    private int code;
    private long number;

    public PhoneNumber(String numbers) {
        if (numbers.charAt(0) != '+') {
            code = 7;
            this.number = Long.parseLong(numbers.substring(1));
        } else {
            code = Integer.parseInt(String.valueOf(numbers.charAt(1)));
            this.number = Long.parseLong(numbers.substring(2));
        }
    }

    public String toString(){
        return String.format("+%d (%d) %d-%d-%d", code, number / 10000000, number % 10000000 / 10000, number % 10000 / 100, number % 100);
    }
}

class Shirt {
    private String art;
    private String type;
    private String color;
    private String size;

    public Shirt(String s) {
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(s.split(",")));
        art = arrayList.get(0);
        type = arrayList.get(1);
        color = arrayList.get(2);
        size = arrayList.get(3);
    }

    public String toString(){
        return "Артикул: " + art + ", Наименование:" + type + ", Цвет:" + color + ", Размер:" + size;
    }
}

public class Main {
    public static void main(String[] args){
        Person person = new Person("Петр","Петров","Петрович");
        System.out.println(person.fio());

        Address address = new Address("Russia, Volgogradskay, Volgograd, Angelsa 23, 4, 37");
        System.out.println(address);

        String[] shirts = new String[11];
        shirts[0] = "S001, Black Polo Shirt, Black, XL";
        shirts[1] = "S002, Black Polo Shirt, Black, L";
        shirts[2] = "S003, Blue Polo Shirt, Blue, XL";
        shirts[3] = "S004, Blue Polo Shirt, Blue, M";
        shirts[4] = "S005, Tan Polo Shirt, Tan, XL";
        shirts[5] = "S006, Black T-Shirt, Black, XL";
        shirts[6] = "S007, White T-Shirt, White, XL";
        shirts[7] = "S008, White T-Shirt, White, L";
        shirts[8] = "S009, Green T-Shirt, Green, S";
        shirts[9] = "S010, Orange T-Shirt, Orange, S";
        shirts[10] = "S011, Maroon Polo Shirt, Maroon, S";
        Shirt[] shirts1 = new Shirt[11];
        for (int i = 0; i < shirts1.length; i++) {
            shirts1[i] = new Shirt(shirts[i]);
            System.out.println(shirts1[i]);
        }

        System.out.println(new PhoneNumber("+79992472945"));

        Last last = new Last("ФейверК Цезарь Серебро сосна ла");
        System.out.println(last);
    }
}
