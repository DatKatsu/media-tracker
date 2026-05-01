package com.github.datkatsu.mediatracker.specification;

import java.util.ArrayList;
import java.util.List;

import com.github.datkatsu.mediatracker.model.MediaEntry;
import com.github.datkatsu.mediatracker.model.MediaFormat;
import org.springframework.data.jpa.domain.Specification;


import com.github.datkatsu.mediatracker.model.MediaCategory;
import com.github.datkatsu.mediatracker.model.Status;

import jakarta.persistence.criteria.Predicate;
//Class to specify filter rules
public class MediaEntrySpecification
{
    public static Specification<MediaEntry> filterBy(MediaFormat format, Status status)
    {
        return (root, query, cb) -> 
        {
            List<Predicate> predicates = new ArrayList<>();

            if(format != null)
            {
                predicates.add(cb.equal(root.get("format"), format));
            }
            if(status != null)
            {
                predicates.add(cb.equal(root.get("status"), status));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}