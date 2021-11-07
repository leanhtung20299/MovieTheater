package com.vn.repositories.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.vn.entities.ScheduleSeat;
import com.vn.entities.ShowDates;
import com.vn.repositories.custom.ScheduleSeatRepositoryCustom;


public class ScheduleSeatRepositoryImpl implements ScheduleSeatRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public List<ScheduleSeat> searchMovieAndScheduleAndShowDate(String movieId, String scheduleId , String showDateId) {
    Session session = sessionFactory.openSession();
    try {
      session.beginTransaction();
      Criteria criteria = session.createCriteria(ScheduleSeat.class)
        .createAlias("movieId", "movieId")
        .createAlias("scheduleId", "scheduleId")
        .createAlias("showDateId", "showDateId")
        .add(Restrictions.eq("movieId.movieId", Integer.parseInt(movieId)))
        .add(Restrictions.eq("scheduleId.scheduleId", Integer.parseInt(scheduleId)))
        .add(Restrictions.eq("showDateId.showDateId", Integer.parseInt(showDateId)));
      criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
      List<ScheduleSeat> list = criteria.list();
      session.getTransaction().commit();
      session.close();
      return list;
    } catch (Exception e) {
      e.printStackTrace();
      session.getTransaction().rollback();
      session.close();
    }
    return null;
  }

}
