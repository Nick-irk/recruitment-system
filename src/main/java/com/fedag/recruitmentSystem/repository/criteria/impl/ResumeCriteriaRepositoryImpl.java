package com.fedag.recruitmentSystem.repository.criteria.impl;

import com.fedag.recruitmentSystem.model.Resume;
import com.fedag.recruitmentSystem.repository.criteria.ResumeCriteriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@RequiredArgsConstructor
public class ResumeCriteriaRepositoryImpl implements ResumeCriteriaRepository {

    private final EntityManager entityManager;

    @Override
    public Page<Resume> findByTextFilter(String text, Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        countQuery
                .select(criteriaBuilder.count(countQuery.from(Resume.class)));
        Long count = entityManager.createQuery(countQuery).getSingleResult();

        CriteriaQuery<Resume> criteriaQuery = criteriaBuilder.createQuery(Resume.class);
        Root<Resume> resume = criteriaQuery.from(Resume.class);
        criteriaQuery
                .select(resume)
                .distinct(false)
                .where(criteriaBuilder.like(resume.get("resumeName"), "%" + text + "%"));
        TypedQuery<Resume> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(pageNumber*pageSize);
        query.setMaxResults(pageSize);
        List<Resume> res = query.getResultList();

        return new PageImpl<>(res, pageable, count);
    }
}
