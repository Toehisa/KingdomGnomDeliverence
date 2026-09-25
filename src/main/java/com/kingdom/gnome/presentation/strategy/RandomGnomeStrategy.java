package com.kingdom.gnome.presentation.strategy;

import com.kingdom.gnome.dao.entity.Email;
import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.dao.entity.GnomeRole;
import com.kingdom.gnome.dao.entity.Random.EmailDomains;
import com.kingdom.gnome.dao.entity.Random.EmailNames;
import com.kingdom.gnome.dao.entity.Random.GnomeNames;
import com.kingdom.gnome.presentation.input.ConsoleInputReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.MAX_VALUE;

public class RandomGnomeStrategy implements GnomeCreationStrategy {

    private final String[] nameArray = GnomeNames.getNameArray();
    private final String[] emailArray = EmailNames.getEmailArray();
    private final String[] domainArray = EmailDomains.getDomainArray();

    @Override
    public List<Gnome> create(ConsoleInputReader inputReader) {

        System.out.println("Введите количество гномов для генерации: ");

        int quantity = inputReader.readInteger("Твой ответ, хозяин: ",1,MAX_VALUE);

        List<Gnome> gnomeList = new ArrayList<>(quantity);
        Random random = new Random();

        for (int i = 0; i < quantity; i++) {
            Gnome gnome = new Gnome.GnomeBuilder()
                .name(nameArray[random.nextInt(nameArray.length)])
                .role(GnomeRole.values()[random.nextInt(GnomeRole.values().length)])
                .email(new Email(emailArray[random.nextInt(emailArray.length)]
                    + domainArray[random.nextInt(domainArray.length)]))
                .build();

            gnomeList.add(gnome);
        }


        return gnomeList;
    }
    //Рандомные гномы 2
}
