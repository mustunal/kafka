package com.mustunal.usertrackingproducer;

import com.github.javafaker.Faker;
import com.mustunal.usertrackingproducer.enums.Color;
import com.mustunal.usertrackingproducer.enums.DesignType;
import com.mustunal.usertrackingproducer.enums.ProductType;
import com.mustunal.usertrackingproducer.enums.UserId;
import com.mustunal.usertrackingproducer.model.Event;
import com.mustunal.usertrackingproducer.model.Product;
import com.mustunal.usertrackingproducer.model.User;

public class EventGenerator {

    private Faker faker = new Faker();

    public Event generateEvent(){
        return Event.builder()
                .user(generateRandomUser())
                .product(generateRandomProduct())
                .build();
    }

    public User generateRandomUser(){
        return User.builder()
                .userId(faker.options().option(UserId.class))
                .userName(faker.name().lastName())
                .dateOfBirth(faker.date().birthday())
                .build();
    }

    public Product generateRandomProduct(){
        return Product.builder()
                .color(faker.options().option(Color.class))
                .designType(faker.options().option(DesignType.class))
                .productType(faker.options().option(ProductType.class))
                .build();

    }
}
