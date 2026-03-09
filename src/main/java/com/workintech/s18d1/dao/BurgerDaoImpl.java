package com.workintech.s18d1.dao;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import com.workintech.s18d1.util.BurgerValidation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class BurgerDaoImpl implements BurgerDao {

    @PersistenceContext
    private EntityManager entityManager;

    private final BurgerValidation burgerValidation;

    public BurgerDaoImpl(BurgerValidation burgerValidation) {
        this.burgerValidation = burgerValidation;
    }

    @Override
    public Burger save(Burger burger) {
        burgerValidation.validateBurger(burger);
        entityManager.persist(burger);
        return burger;
    }

    @Override
    public Burger findById(Long id) {
        burgerValidation.validateId(id);

        Burger foundBurger = entityManager.find(Burger.class, id);

        if (foundBurger == null) {
            throw new BurgerException("Burger not found with id: " + id, HttpStatus.NOT_FOUND);
        }

        return foundBurger;
    }

    @Override
    public List<Burger> findAll() {
        return entityManager
                .createQuery("SELECT b FROM Burger b", Burger.class)
                .getResultList();
    }

    @Override
    public List<Burger> findByPrice(double price) {
        burgerValidation.validatePrice(price);

        return entityManager
                .createQuery("SELECT b FROM Burger b WHERE b.price > :price ORDER BY b.price DESC", Burger.class)
                .setParameter("price", price)
                .getResultList();
    }

    @Override
    public List<Burger> findByBreadType(BreadType breadType) {
        burgerValidation.validateBreadType(breadType);

        return entityManager
                .createQuery("SELECT b FROM Burger b WHERE b.breadType = :breadType ORDER BY b.name ASC", Burger.class)
                .setParameter("breadType", breadType)
                .getResultList();
    }

    @Override
    public List<Burger> findByContent(String content) {
        burgerValidation.validateContent(content);

        return entityManager
                .createQuery("SELECT b FROM Burger b WHERE LOWER(b.contents) LIKE LOWER(CONCAT('%', :content, '%'))", Burger.class)
                .setParameter("content", content)
                .getResultList();
    }

    @Override
    public Burger update(Burger burger) {
        burgerValidation.validateBurger(burger);
        burgerValidation.validateId(burger.getId());

        Burger foundBurger = entityManager.find(Burger.class, burger.getId());

        if (foundBurger == null) {
            throw new BurgerException("Burger not found with id: " + burger.getId(), HttpStatus.NOT_FOUND);
        }

        foundBurger.setName(burger.getName());
        foundBurger.setPrice(burger.getPrice());
        foundBurger.setIsVegan(burger.getIsVegan());
        foundBurger.setBreadType(burger.getBreadType());
        foundBurger.setContents(burger.getContents());

        return entityManager.merge(foundBurger);
    }

    @Override
    public Burger remove(Long id) {
        burgerValidation.validateId(id);

        Burger foundBurger = entityManager.find(Burger.class, id);

        if (foundBurger == null) {
            throw new BurgerException("Burger not found with id: " + id, HttpStatus.NOT_FOUND);
        }

        entityManager.remove(foundBurger);
        return foundBurger;
    }
}