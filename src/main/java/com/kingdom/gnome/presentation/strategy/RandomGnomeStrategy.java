package com.kingdom.gnome.presentation.strategy;

import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.presentation.input.ConsoleInputReader;

import static com.kingdom.gnome.presentation.menu.providers.random.GnomeNamesProvider.NAMES;
import static com.kingdom.gnome.presentation.menu.providers.random.GnomeEmailProvider.EMAILS;
import static com.kingdom.gnome.presentation.menu.providers.random.GnomeRoleProvider.ROLES;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class RandomGnomeStrategy implements GnomeCreationStrategy {
    private static final Random rnd = new Random();

    @Override
    public void createGnomes(ConsoleInputReader inputReader, List<Gnome> gnomes) {
        int quantity = inputReader.readIntInRange("Введите количество гномов для генерации: ",1,1000);

        Stream.generate(
                () -> new Gnome.GnomeBuilder()
                        .name(NAMES.provideNameBySeed(rnd))
                        .role(ROLES.provideRoleBySeed(rnd))
                        .email(EMAILS.provideEmailBySeed(rnd))
                        .build()
                )
                .limit(quantity)
                .forEach(gnomes::add);
    }
}
