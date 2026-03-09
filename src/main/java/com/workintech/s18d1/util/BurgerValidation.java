package com.workintech.s18d1.util;

import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class BurgerValidation {

    public void validateBurger(Burger burger) {
        if (burger == null) {
            throw new BurgerException("Burger cannot be null", HttpStatus.BAD_REQUEST);
        }

        if (burger.getName() == null || burger.getName().trim().isEmpty()) {
            throw new BurgerException("Burger name cannot be empty", HttpStatus.BAD_REQUEST);
        }

        if (burger.getPrice() == null || burger.getPrice() <= 0) {
            throw new BurgerException("Burger price must be greater than 0", HttpStatus.BAD_REQUEST);
        }

        if (burger.getIsVegan() == null) {
            throw new BurgerException("Burger vegan information cannot be null", HttpStatus.BAD_REQUEST);
        }

        if (burger.getBreadType() == null) {
            throw new BurgerException("Burger bread type cannot be null", HttpStatus.BAD_REQUEST);
        }

        if (burger.getContents() == null || burger.getContents().trim().isEmpty()) {
            throw new BurgerException("Burger contents cannot be empty", HttpStatus.BAD_REQUEST);
        }
    }

    public void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new BurgerException("Id must be greater than 0", HttpStatus.BAD_REQUEST);
        }
    }

    public void validatePrice(Double price) {
        if (price == null) {
            throw new BurgerException("Price cannot be null", HttpStatus.BAD_REQUEST);
        }
    }

    public void validateBreadType(Object breadType) {
        if (breadType == null) {
            throw new BurgerException("BreadType cannot be null", HttpStatus.BAD_REQUEST);
        }
    }

    public void validateContent(String content) {
        if (content == null || content.trim().isEmpty()) {
            throw new BurgerException("Content cannot be null or empty", HttpStatus.BAD_REQUEST);
        }
    }
}