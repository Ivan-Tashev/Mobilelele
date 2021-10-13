package com.example.mobilelele.web;

import com.example.mobilelele.model.entity.binding.OfferUpdateBindModel;
import com.example.mobilelele.model.entity.enums.Engine;
import com.example.mobilelele.model.entity.enums.Transmission;
import com.example.mobilelele.model.service.OfferServiceModel;
import com.example.mobilelele.model.view.AddOfferViewModel;
import com.example.mobilelele.service.BrandService;
import com.example.mobilelele.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/offers")
public class OffersController {
    private final OfferService offerService;
    private final BrandService brandService;
    private final ModelMapper modelMapper;

    public OffersController(OfferService offerService, BrandService brandService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.brandService = brandService;
        this.modelMapper = modelMapper;
    }

    // GET (ADD)
    @GetMapping("/add")
    public String getAddOfferPage(Model model) {
        model.addAttribute("brands", brandService.getAllBrands()); // List<BrandViewModel>
        model.addAttribute("engines", Engine.values());
        model.addAttribute("transmissions", Transmission.values());
        if (!model.containsAttribute("addOfferViewModel")) {
            model.addAttribute("addOfferViewModel", new AddOfferViewModel());
        }
        return "offer-add";
    }

    // POST (ADD)
    @PostMapping("/add")
    public String addOffer(@Valid AddOfferViewModel addOfferViewModel,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal Principal principal) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOfferViewModel", addOfferViewModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.addOfferViewModel", bindingResult);
            return "redirect:/offers/add";
        }

        Long offerId = offerService.saveOffer(addOfferViewModel, principal.getName());

        return "redirect:/offers/" + offerId;
    }

    // GET (by id)
    @GetMapping("/{id}")
    public String offerDetails(@PathVariable Long id, Model model) {
        OfferServiceModel offer = offerService.findOfferById(id);
        model.addAttribute("offer", offer);
        return "details";
    }

    // GET (ALL)
    @GetMapping("/all")
    public String getAllOffers(Model model) {
        model.addAttribute("offers", offerService.getAllOffers());
        return "offers";
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteOffer(@PathVariable Long id) {
        offerService.delete(id); // -> offerRepo.deleteById(id);
        return "redirect:/offers/all";
    }
    // <form th:action="@{/offers/{id}(id =${id})}" th:method="delete">
    // <input type="submit" value="Delete"></form>

    // UPDATE GET (PAGE)
    @GetMapping("/{id}/update")
    public String getUpdatePage(@PathVariable Long id, Model model) {
        OfferServiceModel offerServiceModel = offerService.findOfferById(id);
        if (!model.containsAttribute("offerUpdateBindModel")) {
            OfferUpdateBindModel offerUpdateBindModel = modelMapper.map(offerServiceModel, OfferUpdateBindModel.class);
            offerUpdateBindModel.setModel(offerServiceModel.getModel().getBrand().getName() + " "
                    + offerServiceModel.getModel().getName());
            model.addAttribute("offerUpdateBindModel", offerUpdateBindModel);
        }
        return "update";
    }

    // PATCH or PUT
    @PatchMapping("/{id}/update")
    public String updateOffer(@PathVariable Long id,
                              @Valid OfferUpdateBindModel offerUpdateBindModel,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerUpdateBindModel", offerUpdateBindModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.offerUpdateBindModel", bindingResult);
            return "redirect:/offers/" + id + "/update";
        }
        OfferServiceModel offerServiceModel = modelMapper.map(offerUpdateBindModel, OfferServiceModel.class);
        offerServiceModel.setId(id);
        Long updatedId = offerService.updateOffer(offerServiceModel);

        return "redirect:/offers/" + updatedId;
    }
}
