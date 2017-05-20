package com.granatasoft.remotelist.persistence;

import java.util.List;

import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Named;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;

import de.invesdwin.context.persistence.jpa.api.dao.ADao;

@ThreadSafe
@Named
public class CategoryDao extends ADao<Category> {
    public List<Category> findCategoriesWithApplicationInstances() {
        final QCategory category = QCategory.category;
        final QApplicationInstance applicationInstance = QApplicationInstance.applicationInstance;

        final JPQLQuery<Category> list = JPAExpressions.selectFrom(applicationInstance)
                .select(applicationInstance.category);
        return new JPAQuery<Category>(getEntityManager()).from(category)
                .where(category.in(list))
                .orderBy(category.name.asc())
                .fetch();

    }
}
