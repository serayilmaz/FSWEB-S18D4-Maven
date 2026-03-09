package com.workintech.s18d1.controller;

import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/burger")
@Slf4j
public class BurgerController {

    @Autowired
    private BurgerDao burgerDao;

    @GetMapping
    public List<Burger> findAll() {
        try {
            return burgerDao.findAll();
        } catch (Exception e) {
            log.error("Error while getting all burgers", e);
            throw e;
        }
    }

    @GetMapping("/{id}")
    public Burger findById(@PathVariable Long id) {
        try {
            Burger burger = burgerDao.findById(id);

            if (burger == null) {
                throw new BurgerException("Burger not found", HttpStatus.NOT_FOUND);
            }

            return burger;
        } catch (Exception e) {
            log.error("Error while getting burger by id: {}", id, e);
            throw e;
        }
    }

    @PostMapping
    public Burger save(@RequestBody Burger burger) {
        try {
            return burgerDao.save(burger);
        } catch (Exception e) {
            log.error("Error while saving burger", e);
            throw e;
        }
    }

    @PutMapping
    public Burger update(@RequestBody Burger burger) {
        try {
            return burgerDao.update(burger);
        } catch (Exception e) {
            log.error("Error while updating burger", e);
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public Burger remove(@PathVariable Long id) {
        try {
            return burgerDao.remove(id);
        } catch (Exception e) {
            log.error("Error while deleting burger with id: {}", id, e);
            throw e;
        }
    }

    @GetMapping("/price/{price}")
    public List<Burger> findByPrice(@PathVariable double price) {
        try {
            return burgerDao.findByPrice(price);
        } catch (Exception e) {
            log.error("Error while finding burgers by price: {}", price, e);
            throw e;
        }
    }

    @GetMapping("/breadType/{breadType}")
    public List<Burger> findByBreadType(@PathVariable BreadType breadType) {
        try {
            return burgerDao.findByBreadType(breadType);
        } catch (Exception e) {
            log.error("Error while finding burgers by bread type: {}", breadType, e);
            throw e;
        }
    }

    @GetMapping("/content/{content}")
    public List<Burger> findByContent(@PathVariable String content) {
        try {
            return burgerDao.findByContent(content);
        } catch (Exception e) {
            log.error("Error while finding burgers by content: {}", content, e);
            throw e;
        }
    }
}