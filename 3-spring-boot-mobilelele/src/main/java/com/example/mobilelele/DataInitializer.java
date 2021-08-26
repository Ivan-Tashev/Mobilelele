package com.example.mobilelele;

import com.example.mobilelele.model.entity.Brand;
import com.example.mobilelele.model.entity.Model;
import com.example.mobilelele.model.entity.enums.Category;
import com.example.mobilelele.repo.BrandRepo;
import com.example.mobilelele.repo.ModelRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class DataInitializer implements CommandLineRunner {
    private final ModelRepo modelRepo;
    private final BrandRepo brandRepo;

    public DataInitializer(ModelRepo modelRepo, BrandRepo brandRepo) {
        this.modelRepo = modelRepo;
        this.brandRepo = brandRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        saveBrand("Ford");
        saveBrand("Honda");

        saveModel("Fiesta", Category.Car,
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fbg.wikipedia.org%2Fwiki%2F%25D0%25A4%25D0%25B0%25D0%25B9%25D0%25BB%3A2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg&psig=AOvVaw0fZgm1EX_tUsxGOo35xXeo&ust=1630073194184000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCODH7IDuzvICFQAAAAAdAAAAABAD",
                1976, null, 1L);

        saveModel("Escort", Category.Car,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/Ford_Escort_MkI_1100_1972.JPG/1920px-Ford_Escort_MkI_1100_1972.JPG",
                1968, 2002, 1L);


        saveModel("NC750S", Category.Motorcycle,
                "https://img1.motorradonline.de/image-fotoshowBig-521029d4-1379946.jpg",
                2014, null, 2L);

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
