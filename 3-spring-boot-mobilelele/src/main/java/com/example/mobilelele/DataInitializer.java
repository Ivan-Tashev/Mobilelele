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
        // Initialize Admin and User, with their corresponding roles
        if (userRepo.count() == 0) {
            initUsers();
        }
        // Initialize Brands
        if (brandRepo.count() == 0) {
            saveBrand("Mercedes");
            saveBrand("BMW");
            saveBrand("Reno");
            saveBrand("Ducati");
        }
        // Initialize Models
        if (modelRepo.count() == 0) {
            saveModel("C43 AMG", Category.Car,
                    "https://www.mbusa.com/content/dam/mb-nafta/us/myco/my21/c/cab/byo-options/2021-AMG-C-CABRIOLET-MP-030.jpg",
                    2016, null, 1L);

            saveModel("CLS 350d", Category.Car,
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSeRYwzKmEx_Exn8v2_ybs-aBmiviPlA6qZaw&usqp=CAU",
                    2009, null, 1L);


            saveModel("Supersport S", Category.Motorcycle,
                    "https://www.motostop.eu/productimages/20024/59406.jpg",
                    2018, null, 4L);

            saveModel("Master", Category.Motorcycle,
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQbrPEwx7VDgSB_DM-_e1q_Rp9Sxr948zhdCNRGqUB7zZSU64y_ASfBwzDiSH0yA1iTjp4&usqp=CAU",
                    2010, null, 3L);
        }
        // Initialize Offers
        if (offerRepo.count() == 0) {
            saveOffer(modelRepo.findById(1L).orElse(null), Engine.DIESEL,
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTNpy0JRZFx0C2r6GD5Es53gzuXEJFiqFp-Hw&usqp=CAU",
                    22000, BigDecimal.valueOf(88000), 2017, "Used, garaged, good condition...", Transmission.AUTOMATIC);

            saveOffer(modelRepo.findById(3L).orElse(null), Engine.GASOLINE,
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQDdGIx2lNIceux7zi9qu5aQtYXE7EYRBluGA&usqp=CAU",
                    20500, BigDecimal.valueOf(29500), 2015, "Used, but well services and in good condition.", Transmission.MANUAL);
        }



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
        admin.setPassword(passwordEncoder.encode("123"));
        admin.setRoles(List.of(adminRole, userRole));
        userRepo.save(admin);
        // create user with 1 role
        User user = new User();
        user.setFirstName("Nicole");
        user.setLastName("Walt");
        user.setUsername("Nicole");
        // save the password encoded to hash in DB
        user.setPassword(passwordEncoder.encode("456"));
        user.setRoles(List.of(userRole));
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
        offer.setSeller(userRepo.getById(1L));
        offerRepo.save(offer);
    }

    private void saveModel(String modelName, Category category, String imageUrl, Integer startYear, Integer endYear, Long brandId) {
        Model model = new Model();
        model.setName(modelName);
        model.setCategory(category);
        model.setImageUrl(imageUrl);
        model.setStartYear(startYear);
        model.setEndYear(endYear);
        model.setBrand(brandRepo.findById(brandId).orElse(null));
        modelRepo.save(model);
    }

    private void saveBrand(String name) {
        Brand brand = new Brand();
        brand.setName(name);
        brandRepo.save(brand);
    }
}
