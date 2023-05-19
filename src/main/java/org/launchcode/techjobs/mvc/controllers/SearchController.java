package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
//Use the correct annotation for the method. To configure the correct mapping type and mapping route,
// refer to the form tag in the search.html template. (Use @GetMapping or @PostMapping.)
    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType,@RequestParam String searchTerm)  {
//   The displaySearchResults method should take in a Model parameter.(Model model)
//The method should also take in two other parameters, specifying the type of search and the search term.(find the name in search.html)
        ArrayList<Job> jobs;
        if (searchTerm.equals("all") || searchTerm.equals("null")){
            jobs = JobData.findAll();
//model.addAttribute()
        } else {
            jobs = JobData.findByColumnAndValue(searchType,searchTerm);
            model.addAttribute("title", "Jobs Searched By " +
                    ListController.columnChoices.get(searchType) + ":" + searchTerm);
// REMEMBER SEARCHTYPE WOULD EQUAL ex.EMPLOYER AND SERCHTERM IS NOW THE VALUE ex. MASTERCARD
        }
//Pass jobs into the search.html view via the model parameter.
//Pass ListController.columnChoices into the view, as the existing search handler does
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }
}
