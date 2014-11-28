/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless;

import entity.Feedback;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author wangyan
 */
@Stateless
public class FeedbackSession implements FeedbackSessionLocal {
    @PersistenceContext
    EntityManager em;
    @Override
    public void addFeedback(String feedbackName,String feedbackEmail,String feedbackComponent){
     Feedback feedback =new Feedback();
     feedback.setFeedbackName(feedbackName);
     feedback.setFeedbackEmail(feedbackEmail);
     feedback.setFeedbackComponent(feedbackComponent);
     em.persist(feedback);
     em.flush();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
