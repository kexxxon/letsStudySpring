package com.cStates.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/coffees")
public class CoffeeController {
    @PostMapping
    public ResponseEntity postCoffee(@RequestParam("korName") String korName,
                                     @RequestParam("engName") String engName,
                                     @RequestParam("price") int price) {

        Map<String, Object> map = new HashMap<>();
        map.put("korName", korName);
        map.put("engName", engName);
        map.put("price", price);

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @GetMapping("/{coffeeId}")
    public ResponseEntity getCoffee(@PathVariable("coffeeId")long coffeeId) {
        System.out.println("# coffeeId: " + coffeeId);

        // not implementation

        return new ResponseEntity<Map>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getCoffees() {
        System.out.println("# get Coffees");

        // not implementation

        return new ResponseEntity<Map>(HttpStatus.OK);
    }

    @PatchMapping("/{coffeeId}")
    public ResponseEntity patchCoffee(@PathVariable("coffeeId") long coffeeId,
                                      @RequestParam("korName") String korName,
                                      @RequestParam("price") int price) {

        Map<String, Object> body = new HashMap<>();
        body.put("coffeeId", coffeeId);
        body.put("korName", korName);
        body.put("engName", "Espresso");
        body.put("price", price);

        return new ResponseEntity(body, HttpStatus.OK);
    }

    @DeleteMapping("/{coffeeId}")
    public ResponseEntity deleteCoffee(@PathVariable("coffeeId") long coffeeId) {

        // No need business logic

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
