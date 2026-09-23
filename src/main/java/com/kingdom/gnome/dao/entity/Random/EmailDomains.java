package com.kingdom.gnome.dao.entity.Random;

public class EmailDomains {
    private static final String[] domainArray = {"@mail.ru", "@gmail.com", "yandex.ru", "icloud.com", "outlook.com"};

    public static String[] getDomainArray() {
        return domainArray;
    }
}
