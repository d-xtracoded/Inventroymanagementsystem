package com.prime.InventroryMgtSystem.specification;

import com.prime.InventroryMgtSystem.models.Transaction;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

public class TransactionFilter {

    public static Specification<Transaction> byFilter(String searchValue){
        return (root, query, criteriaBuilder) -> {
            if (searchValue == null || searchValue.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            String searchPattern="%"+searchValue.toLowerCase()+"%";
            List<Predicate> predicates= new ArrayList<>();
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")),searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("note")),searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("status")),searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("transactionType")),searchPattern));

            //safely join to check userfields
            if (root.getJoins().stream().noneMatch(j-> j.getAttribute().getName().equals("users"))){
                root.join("user", JoinType.LEFT);
            }
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("user",JoinType.LEFT).get("name")),searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("user",JoinType.LEFT).get("email")),searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("user",JoinType.LEFT).get("phonenumber")),searchPattern));

            if (root.getJoins().stream().noneMatch(j-> j.getAttribute().getName().equals("suppliers"))){
                root.join("suppliers", JoinType.LEFT);
            }
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("suppliers",JoinType.LEFT).get("name")),searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("suppliers",JoinType.LEFT).get("contactInfo")),searchPattern));


            if (root.getJoins().stream().noneMatch(j-> j.getAttribute().getName().equals("products"))){
                root.join("products", JoinType.LEFT);
            }
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("products",JoinType.LEFT).get("name")),searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("products",JoinType.LEFT).get("sku")),searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("products",JoinType.LEFT).get("description")),searchPattern));

            //join to check category field
            if (root.getJoins().stream().noneMatch(j-> j.getAttribute().getName().equals("product")) &&
                    root.join("products").getJoins().stream().noneMatch(j -> j.getAttribute().getName().equals("category"))){
                root.join("product", JoinType.LEFT).join("category",JoinType.LEFT);
            }
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("products",JoinType.LEFT).join("",JoinType.LEFT).get("name")),searchPattern));

            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));


        };
    }

    public static Specification<Transaction>byMonthAndYear(int month, int year){
        return (root, query, criteriaBuilder) -> {
            Expression<Integer> monthExpression = criteriaBuilder.function("month", Integer.class,root.get("createdAt"));
            Expression<Integer> yearExpression = criteriaBuilder.function("year", Integer.class,root.get("createdAt"));

            Predicate monthPredicate = criteriaBuilder.equal(monthExpression,month);
            Predicate yearPredicate = criteriaBuilder.equal(yearExpression,year);

            return criteriaBuilder.and(monthPredicate,yearPredicate);
        };

    }


}
