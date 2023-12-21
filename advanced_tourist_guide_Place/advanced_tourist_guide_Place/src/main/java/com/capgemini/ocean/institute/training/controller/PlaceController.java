package com.capgemini.ocean.institute.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.capgemini.ocean.institute.training.entity.Place;
import com.capgemini.ocean.institute.training.service.PlaceService;

import java.util.List;

@RestController
@RequestMapping("/place")
@CrossOrigin("*")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @GetMapping("viewplace")
    public List<Place> getAllPlaces() {
        return placeService.getAllPlaces();
    }

    @GetMapping("/{placeID}")
    public Place getPlaceById(@PathVariable(name="placeID") Long placeID) {
        return placeService.getPlaceById(placeID);
    }

    @PostMapping("addplace")
    public Place addPlace(@RequestBody Place place) {
        return placeService.addPlace(place);
    }

    @PutMapping("/{placeID}")
    public Place updatePlace(@PathVariable Long placeID, @RequestBody Place place) {
        return placeService.updatePlace(placeID, place);
    }

    @DeleteMapping("/{placeID}")
    public void deletePlace(@PathVariable Long placeID) {
        placeService.deletePlace(placeID);
    }
    
    @GetMapping("/all")
    public List<String> getAllTags() {
        return placeService.getAllTags();
    }
    
    @GetMapping("/tag/{tag}")
    public ResponseEntity<List<Place>> getPlacesByTag(@PathVariable(name = "tag") String tag) {
        List<Place> places = placeService.getPlacesByTag(tag);
        return new ResponseEntity<>(places, HttpStatus.OK);
    }
}
