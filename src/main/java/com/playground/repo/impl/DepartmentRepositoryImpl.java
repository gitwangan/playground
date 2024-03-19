package com.playground.repo.impl;

import com.playground.pojo.Department;
import com.playground.repo.DepartmentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {
    private final EntityManager entityManager;

    @Autowired
    public DepartmentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Department department) {
        entityManager.persist(department);
    }

    @Override
    public List<Department> findByNameAndMinId(String name, int minId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Department> cq = cb.createQuery(Department.class);
        Root<Department> root = cq.from(Department.class);
        cq.select(root).where(cb.and(new Predicate[] {
                cb.equal(root.get("name"), name), cb.greaterThanOrEqualTo(root.get("id"), minId)}));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public void update(Department department) {
        entityManager.merge(department);
    }

    @Override
    public void delete(int id) {
        // use em.remove(em.find()) results in two queries
        entityManager.createQuery("delete from Department d where d.id = :id").setParameter("id", id).executeUpdate();
    }

}
