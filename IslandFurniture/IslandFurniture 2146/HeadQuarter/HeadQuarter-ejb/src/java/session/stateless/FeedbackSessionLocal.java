/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless;

import javax.ejb.Local;

/**
 *
 * @author wangyan
 */
@Local
public interface FeedbackSessionLocal {
    public void addFeedback(String feedbackName,String feedbackEmail,String feedbackComponent);
}
