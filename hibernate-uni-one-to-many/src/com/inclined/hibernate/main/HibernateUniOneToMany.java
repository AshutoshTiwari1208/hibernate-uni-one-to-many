package com.inclined.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.inclined.hibernate.table.relations.Course;
import com.inclined.hibernate.table.relations.Instructor;
import com.inclined.hibernate.table.relations.InstructorDetail;
import com.inclined.hibernate.table.relations.Review;

public class HibernateUniOneToMany {

	public static void main(String[] args) {

		// Session Start
		// Session, SessionFactory implements AutoClosable()
		// try with resources
		try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class).addAnnotatedClass(Review.class).buildSessionFactory();
				Session session = factory.getCurrentSession();) {
			session.beginTransaction();

			Course course = new Course("Namaste Java by IS");
			Review review = new Review("That's a wonderful course");
			Review review2 = new Review("Really liked that course! Yipee");
			Review review3 = new Review("Nah! I don't like this course :/");

			course.addReview(review);
			course.addReview(review2);
			course.addReview(review3);
			
			session.persist(course);

			// commit Transaction
			session.getTransaction().commit();
		}

	}

}
