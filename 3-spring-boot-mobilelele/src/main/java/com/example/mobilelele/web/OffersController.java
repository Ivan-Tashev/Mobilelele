package com.example.mobilelele.web;

import com.example.mobilelele.model.entity.Offer;
import com.example.mobilelele.model.entity.enums.Engine;
import com.example.mobilelele.model.entity.enums.Transmission;
import com.example.mobilelele.model.view.AddOfferViewModel;
import com.example.mobilelele.service.BrandService;
import com.example.mobilelele.service.OfferService;
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

    public OffersController(OfferService offerService, BrandService brandService) {
        this.offerService = offerService;
        this.brandService = brandService;
    }

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

        return "redirect:/offers/offer/" + offerId;
    }

    @GetMapping("/offer/{id}")
    public String offerDetails(@PathVariable String id, Model model) {
        model.addAttribute("id", id);
        Offer offer = offerService.findOfferById(id);
        model.addAttribute("offer" ,offer);
        return "details";
    }

    @DeleteMapping("/offer/{id}")
    public String deleteOffer(@PathVariable Long id){
        offerService.delete(id);
        return "redirect:/offers/all";
    }

    @GetMapping("/all")
    public String getAllOffers(Model model) {
        model.addAttribute("offers", offerService.getAllOffers());
        return "offers";
    }
}
