package com.evaitcs.eventplanner.controller;

import com.evaitcs.eventplanner.model.Event;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * ============================================================================
 * CLASS: EventController
 * TOPIC: @Controller — Returns VIEW NAMES (not JSON data)
 * ============================================================================
 *
 * KEY DIFFERENCE:
 *   @Controller    → returns a String that resolves to a Thymeleaf template
 *   @RestController → returns data (JSON) directly
 *
 * MVC REQUEST FLOW:
 *   1. Browser sends GET /events
 *   2. DispatcherServlet routes to EventController.listEvents()
 *   3. Controller adds data to Model and returns view name "events/list"
 *   4. ViewResolver finds template: src/main/resources/templates/events/list.html
 *   5. Thymeleaf renders HTML with the Model data
 *   6. HTML is sent back to the browser
 *
 * INTERVIEW TIP:
 * "In Spring MVC, the DispatcherServlet is the front controller. It routes
 *  requests to handlers, which populate a Model and return a view name.
 *  The ViewResolver maps view names to actual template files."
 * ============================================================================
 */
@Controller
@RequestMapping("/events")
public class EventController {

    private final Map<Long, Event> events = new HashMap<>();
    private long nextId = 1;

    /**
     * TODO 1: GET /events — Show list of all events
     * Add the events list to the Model so Thymeleaf can render it.
     *
     * Model is a key-value container that passes data to the view.
     *   model.addAttribute("events", eventList);
     * In Thymeleaf: <div th:each="event : ${events}">
     */
    @GetMapping
    public String listEvents(Model model) {
        // TODO: Add events to the model, return the view name
        // model.addAttribute("events", new ArrayList<>(events.values()));
        // return "events/list";
        return "events/list";
    }

    /**
     * TODO 2: GET /events/new — Show the "create event" form
     * Pass an empty Event object to the form for binding.
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        // TODO: Add a new empty Event to the model
        // model.addAttribute("event", new Event());
        // return "events/form";
        return "events/form";
    }

    /**
     * TODO 3: POST /events — Handle form submission
     * @Valid triggers Bean Validation on the Event.
     * BindingResult contains any validation errors.
     * If errors exist, re-show the form (don't save).
     *
     * @ModelAttribute binds form fields to the Event object automatically.
     */
    @PostMapping
    public String createEvent(@Valid @ModelAttribute("event") Event event,
                              BindingResult bindingResult, Model model) {
        // TODO: Check for validation errors
        // if (bindingResult.hasErrors()) {
        //     return "events/form";  // Re-show form with error messages
        // }
        // event.setId(nextId++);
        // events.put(event.getId(), event);
        // return "redirect:/events";  // PRG pattern: redirect after POST
        return "redirect:/events";
    }

    /**
     * TODO 4: GET /events/{id} — Show event details
     */
    @GetMapping("/{id}")
    public String showEvent(@PathVariable Long id, Model model) {
        // TODO: Find event, add to model, return detail view
        // Event event = events.get(id);
        // if (event == null) return "redirect:/events";
        // model.addAttribute("event", event);
        // return "events/detail";
        return "events/detail";
    }

    /**
     * TODO 5: GET /events/{id}/edit — Show edit form pre-filled with event data
     */
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        return "events/form";
    }

    /**
     * TODO 6: POST /events/{id}/delete — Delete an event
     */
    @PostMapping("/{id}/delete")
    public String deleteEvent(@PathVariable Long id) {
        // events.remove(id);
        return "redirect:/events";
    }
}

