package com.inclined.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.inclined.hibernate.table.relations.Course;
import com.inclined.hibernate.table.relations.Instructor;
import com.inclined.hibernate.table.relations.InstructorDetail;
import com.inclined.hibernate.table.relations.Review;

public class HibernateUniGetRelatedData {

	public static void main(String[] args) {

		// Session Start
		// Session, SessionFactory implements AutoClosable()
		// try with resources
		try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class).addAnnotatedClass(Review.class).buildSessionFactory();
				Session session = factory.getCurrentSession();) {
			session.beginTransaction();

			Course course = session.get(Course.class, 10);
			
			System.out.println("XX:: Course " + course);
			System.out.println("XX:: Reviews " + course.getReview());
			
//			session.persist(course);

			// commit Transaction
			session.getTransaction().commit();
		}

	}

}
