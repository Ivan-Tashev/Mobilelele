package com.example.mobilelele;

import com.example.mobilelele.model.entity.*;
import com.example.mobilelele.model.entity.enums.Category;
import com.example.mobilelele.model.entity.enums.Engine;
import com.example.mobilelele.model.entity.enums.Role;
import com.example.mobilelele.model.entity.enums.Transmission;
import com.example.mobilelele.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final ModelRepo modelRepo;
    private final BrandRepo brandRepo;
    private final OfferRepo offerRepo;
    private final UserRepo userRepo;
    private final UserRoleRepo userRoleRepo;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(ModelRepo modelRepo, BrandRepo brandRepo, OfferRepo offerRepo, UserRepo userRepo, UserRoleRepo userRoleRepo, PasswordEncoder passwordEncoder) {
        this.modelRepo = modelRepo;
        this.brandRepo = brandRepo;
        this.offerRepo = offerRepo;
        this.userRepo = userRepo;
        this.userRoleRepo = userRoleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        saveBrand("Ford");
        saveBrand("Honda");

        saveModel("Fiesta", Category.Car,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg/275px-2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg",
                1976, null, 1L);

        saveModel("Escort", Category.Car,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/Ford_Escort_MkI_1100_1972.JPG/1920px-Ford_Escort_MkI_1100_1972.JPG",
                1968, 2002, 1L);


        saveModel("NC750S", Category.Motorcycle,
                "https://img1.motorradonline.de/image-fotoshowBig-521029d4-1379946.jpg",
                2014, null, 2L);

        saveOffer(modelRepo.findById(1L).orElse(null),Engine.GASOLINE,
                "https://www.motopfohe.bg/files/news/archive/2017/08/blob-server.jpg",
                80000, BigDecimal.valueOf(15200),2019,"Used, good condition...", Transmission.MANUAL);

        // Initialize Admin and User, with their corresponding roles
        initUsers();
    }

    private void initUsers() {
        // save Role.Admin and Role.USER to DB
        final UserRole adminRole = new UserRole();
        adminRole.setName(Role.ADMIN);
        final UserRole userRole = new UserRole();
        userRole.setName(Role.USER);
        userRoleRepo.saveAll(List.of(adminRole, userRole));
        // create admin with 2 roles
        User admin = new User();
        admin.setFirstName("John");
        admin.setLastName("Smith");
        admin.setUsername("admin");
            // save the password encoded to hash in DB
        admin.setPassword(passwordEncoder.encode("1"));
        admin.setRoles(List.of(adminRole, userRole));
        admin.setCreated(Instant.now());
        admin.setModified(Instant.now());
        userRepo.save(admin);
        // create user with 1 role
        User user = new User();
        user.setFirstName("Nicole");
        user.setLastName("Walt");
        user.setUsername("Nicole");
        // save the password encoded to hash in DB
        user.setPassword(passwordEncoder.encode("2"));
        user.setRoles(List.of(userRole));
        user.setCreated(Instant.now());
        user.setModified(Instant.now());
        userRepo.save(user);
    }

    private void saveOffer(Model model, Engine engine, String imageUrl, Integer km, BigDecimal price,
                           Integer year, String description, Transmission transmission) {
        Offer offer = new Offer();
        offer.setModel(model);
        offer.setEngine(engine);
        offer.setImageUrl(imageUrl);
        offer.setMileage(km);
        offer.setPrice(price);
        offer.setYear(year);
        offer.setDescription(description);
        offer.setTransmission(transmission);
        offer.setCreated(Instant.now());
        offer.setModified(Instant.now());
        offerRepo.save(offer);
    }

    private void saveModel(String modelName, Category category, String imageUrl, Integer startYear, Integer endYear, Long brandId) {
        Model model = new Model();
        model.setName(modelName);
        model.setCategory(category);
        model.setImageUrl(imageUrl);
        model.setStartYear(startYear);
        model.setEndYear(endYear);
        model.setCreated(Instant.now());
        model.setModified(Instant.now());
        model.setBrand(brandRepo.findById(brandId).orElse(null));
        modelRepo.save(model);
    }

    private void saveBrand(String name) {
        Brand brand = new Brand();
        brand.setName(name);
        brand.setCreated(Instant.now());
        brand.setModified(Instant.now());
        brandRepo.save(brand);
    }
}
