package com.kingdom.gnome.presentation.strategy;

import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.dao.entity.Random.EmailDomains;
import com.kingdom.gnome.dao.entity.Random.EmailNames;
import com.kingdom.gnome.dao.entity.Random.GnomeNames;

import java.util.List;
import java.util.Scanner;

public class RandomGnomeStrategy implements GnomeCreationStrategy {

    private final String[] nameArray = GnomeNames.getNameArray();
    private final String[] emailArray = EmailNames.getEmailArray();
    private final String[] domainArray = EmailDomains.getDomainArray();

    @Override
    public List<Gnome> create(Scanner scanner) {
        return null;
    }
    //Рандомные гномы 2
}
