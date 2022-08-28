package com.cStates.member;

import com.cStates.dto.CoffeePatchDto;
import com.cStates.dto.CoffeePostDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/coffees")
public class CoffeeController {
    @PostMapping
    public ResponseEntity postCoffee(@RequestBody CoffeePostDto coffeePostDto) {

        return new ResponseEntity<>(coffeePostDto, HttpStatus.CREATED);
    }

    @GetMapping("/{coffeeId}")
    public ResponseEntity getCoffee(@PathVariable("coffeeId")long coffeeId) {
        System.out.println("# coffeeId: " + coffeeId);

        // not implementation

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getCoffees() {
        System.out.println("# get Coffees");

        // not implementation

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{coffeeId}")
    public ResponseEntity patchCoffee(@PathVariable("coffeeId") long coffeeId,
                                      @RequestBody CoffeePatchDto coffeePatchDto) {

        coffeePatchDto.getKorName();
        coffeePatchDto.getEngName();
        coffeePatchDto.setPrice(3000);

        return new ResponseEntity<>(coffeePatchDto, HttpStatus.OK);
    }

    @DeleteMapping("/{coffeeId}")
    public ResponseEntity deleteCoffee(@PathVariable("coffeeId") long coffeeId) {

        // No need business logic

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
