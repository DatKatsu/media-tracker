package com.github.datkatsu.mediatracker.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;


import com.github.datkatsu.mediatracker.model.Item;
import com.github.datkatsu.mediatracker.model.MediaType;
import com.github.datkatsu.mediatracker.model.Status;

import jakarta.persistence.criteria.Predicate;

public class ItemSpecification
{
    public static Specification<Item> filterBy(MediaType type, Status status)
    {
        return (root, query, cb) -> 
        {
            List<Predicate> predicates = new ArrayList<>();

            if(type != null)
            {
                predicates.add(cb.equal(root.get("type"), type));
            }
            if(status != null)
            {
                predicates.add(cb.equal(root.get("status"), status));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}