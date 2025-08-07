package com.iamohmx.ohmx.services;

import com.iamohmx.ohmx.entities.Cow;
import com.iamohmx.ohmx.entities.Farm;
import com.iamohmx.ohmx.entities.Owner;
import com.iamohmx.ohmx.repositories.CowRepository;
import com.iamohmx.ohmx.repositories.FarmRepository;
import com.iamohmx.ohmx.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Arrays;

@Service
public class DataInitializationService implements CommandLineRunner {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private FarmRepository farmRepository;

    @Autowired
    private CowRepository cowRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if data already exists
        if (ownerRepository.count() > 0) {
            return; // Data already initialized
        }

        // Create Owners
        Owner owner1 = new Owner();
        owner1.setName("Somchai");
        owner1.setSurName("Jaidee");
        owner1.setAddress("123 Moo 1, Tambon Nong Pla Lai, Amphoe Ban Bueng, Chonburi 20170");
        ownerRepository.save(owner1);

        Owner owner2 = new Owner();
        owner2.setName("Sumalee");
        owner2.setSurName("Kaewta");
        owner2.setAddress("456 Moo 3, Tambon Wang Thonglang, Amphoe Wang Thonglang, Bangkok 10310");
        ownerRepository.save(owner2);

        Owner owner3 = new Owner();
        owner3.setName("Prasert");
        owner3.setSurName("Thongchai");
        owner3.setAddress("789 Moo 5, Tambon San Sai Noi, Amphoe San Sai, Chiang Mai 50210");
        ownerRepository.save(owner3);

        // Create Farms
        Farm farm1 = new Farm();
        farm1.setName("Happy Cow Farm");
        farm1.setOwner(owner1);
        farmRepository.save(farm1);

        Farm farm2 = new Farm();
        farm2.setName("Green Valley Farm");
        farm2.setOwner(owner2);
        farmRepository.save(farm2);

        Farm farm3 = new Farm();
        farm3.setName("Mountain View Farm");
        farm3.setOwner(owner3);
        farmRepository.save(farm3);

        Farm farm4 = new Farm();
        farm4.setName("Sunshine Dairy Farm");
        farm4.setOwner(owner1);
        farmRepository.save(farm4);

        // Create Cows
        Cow[] cows = {
            createCow("Daisy", LocalDate.of(2020, 3, 15), "Holstein", farm1),
            createCow("Bella", LocalDate.of(2019, 7, 22), "Jersey", farm1),
            createCow("Luna", LocalDate.of(2021, 1, 10), "Angus", farm1),
            createCow("Rosie", LocalDate.of(2020, 9, 5), "Holstein", farm2),
            createCow("Molly", LocalDate.of(2019, 11, 18), "Guernsey", farm2),
            createCow("Annie", LocalDate.of(2021, 4, 2), "Jersey", farm2),
            createCow("Sophie", LocalDate.of(2020, 6, 30), "Brown Swiss", farm2),
            createCow("Charlie", LocalDate.of(2019, 12, 8), "Holstein", farm3),
            createCow("Maggie", LocalDate.of(2021, 2, 14), "Angus", farm3),
            createCow("Ruby", LocalDate.of(2020, 8, 25), "Jersey", farm4),
            createCow("Grace", LocalDate.of(2019, 5, 12), "Holstein", farm4),
            createCow("Lily", LocalDate.of(2021, 3, 7), "Guernsey", farm4)
        };

        cowRepository.saveAll(Arrays.asList(cows));

        System.out.println("Mock data initialized successfully!");
        System.out.println("Created " + ownerRepository.count() + " owners");
        System.out.println("Created " + farmRepository.count() + " farms");
        System.out.println("Created " + cowRepository.count() + " cows");
    }

    private Cow createCow(String name, LocalDate birth, String type, Farm farm) {
        Cow cow = new Cow();
        cow.setCowName(name);
        cow.setBirth(birth);
        cow.setType(type);
        cow.setFarm(farm);
        return cow;
    }
}
