/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.stateless;

import entity.BOM;
import entity.Furniture;
import entity.MPS;
import entity.MRP;
import entity.Part;
import entity.SalesPlan;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author li
 */
@Stateless
public class MPSSessionBean implements MPSSessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    EntityManager em;
    private Furniture furniture;
    private SalesPlan plan;
    private MPS mps;
    
    @Override
    public SalesPlan viewSalesPlan(String time,String furnitureName) {
        Query q=em.createQuery("SELECT f FROM Furniture f WHERE f.furnitureName=:fName");
        q.setParameter("fName",furnitureName);
        SalesPlan plan1=new SalesPlan();
        if(q.getResultList().isEmpty()){
            System.out.println("there is no existing furniture for the name you provided/ furniture does  not exist ");
            return null;//empty
        }
        else{
            furniture=(Furniture)q.getResultList().get(0);
            long id= furniture.getId();
            Query q2=em.createQuery("SELECT p FROM SalesPlan p WHERE p.furniture.id=:pID AND p.time=:pTime");
            q2.setParameter("pID",id);
            q2.setParameter("pTime",time);
            if(q2.getResultList().isEmpty()) {
                System.out.println("1");
                return null;
            }
            else {
                System.out.println("2");
                plan1 = (SalesPlan) q2.getResultList().get(0);
                int count = 0;
		
	        int month = Integer.parseInt(plan1.getTime().substring(0, 2));
                int year = Integer.parseInt(plan1.getTime().substring(3, 7));
                Calendar cal = new GregorianCalendar();
		//Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(plan1.getTime().substring(3, 7)));
		cal.set(Calendar.MONTH,  month - 1);
		cal.set(Calendar.DATE, 1);

                while( (cal.get(Calendar.MONTH) < month) && (cal.get(Calendar.YEAR)== year)){
			int day = cal.get(Calendar.DAY_OF_WEEK);
			
			if(!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)){
				count++;
			}
			
			cal.add(Calendar.DATE, 1);
		}
	       plan1.setWorkingDays(count);
               System.out.println(count);
               return plan1; 
            }
        }
                      
    }
    
    //((plan1.getWeek()==(i+1))&&(
    
    @Override
    public List<MPS> generateMPS(String time,String furnitureName,String partName ) {
        List<MPS> weeklyPlan =new ArrayList<>();
        MRP mrp2 = new MRP();
        String name= new String();
        plan = viewSalesPlan(time,furnitureName);
        Collection<MPS> plan3 = plan.getMps();
        if(!plan.getMps().isEmpty()) {
           // for(int i=0; i<plan.getMps().size(); i++) {
                 for(Object o:plan.getMps()) {
                            MPS plan1 = (MPS) o;
                            name = plan1.getPartName();
                            System.out.println(name+"  123");
                            if((name==null)||(name.equals(partName))) {
                            weeklyPlan.add(plan1);
                            System.out.println("weeklySize:"+ weeklyPlan.size());
                            //break;
                            }
                 }
            //}
            if(weeklyPlan.size()!=0) {
                List<MPS> weeklyPlan2 =new ArrayList<>();
                 for(int i=0; i<weeklyPlan.size(); i++) {
                     for(int j=0; j<weeklyPlan.size();j++) {
                         if(weeklyPlan.get(j).getWeek()==(i+1)) {
                             weeklyPlan2.add(weeklyPlan.get(j));
                             break;
                         }
                     }
                 }     
                 return weeklyPlan2;
            }
        }
        System.out.println("weeklyplan:"+weeklyPlan.size());
        int count = 0;
        int day;
        int week = 1;
        float dailydemand;
        int weeklydemand;
        int production = 0;
        String periodDate = new String();
		
        periodDate = "";
	int month = Integer.parseInt(plan.getTime().substring(0, 2));
        int year = Integer.parseInt(plan.getTime().substring(3, 7));
        Calendar cal = new GregorianCalendar();
	//Calendar cal = Calendar.getInstance();
	cal.set(Calendar.YEAR, year);
	cal.set(Calendar.MONTH,  month - 1);
	cal.set(Calendar.DATE, 1);
        
        if( plan == null)
            return null;
        else {
            //condition for within a month
            dailydemand = (float)plan.getProductionPlan()/plan.getWorkingDays();
            System.out.println(dailydemand);
            while( (cal.get(Calendar.MONTH) < month) && (cal.get(Calendar.YEAR)== year)){
                 day = cal.get(Calendar.DAY_OF_WEEK);
                 if(count==0) {
                     periodDate = String.valueOf(cal.get(Calendar.DATE))+"/"+String.valueOf(cal.get(Calendar.MONTH)+1);
                 }
                 if(!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)) {
                     count++;
                 }
                 else if(day == Calendar.SUNDAY) {   
                     periodDate = periodDate + "-" + String.valueOf(cal.get(Calendar.DATE))+"/"+String.valueOf(cal.get(Calendar.MONTH)+1);
                     weeklydemand = (int)(dailydemand * count + 0.99);
                     MPS mps1 = new MPS(count,week,weeklydemand,periodDate);
                     count = 0;
                     weeklyPlan.add(mps1);
                     mps1.setPlan(plan);
                     mps1.setMrp(mrp2);

                     week++;
                     em.persist(mps1);

                 }
                 cal.add(Calendar.DATE, 1);                    
             }
             if(count!=0) {
                cal.add(Calendar.DATE, -1);
                periodDate = periodDate + "-" + String.valueOf(cal.get(Calendar.DATE))+"/"+String.valueOf(cal.get(Calendar.MONTH)+1);
                weeklydemand = (int) (dailydemand * count + 0.99);
                MPS mps1 = new MPS(count,week,weeklydemand,periodDate);
                count = 0;
                weeklyPlan.add(mps1); 
                mps1.setPlan(plan);
                mps1.setMrp(mrp2);

                em.persist(mps1);
             }
             for(int i = 0;i < weeklyPlan.size(); i++) {
                 if(i!=(weeklyPlan.size()-1))
                     production = production + weeklyPlan.get(i).getMPSweeklyDemand();
                 else
                 {
                     weeklyPlan.get(i).setMPSweeklyDemand(plan.getProductionPlan()-production);
                     em.persist(weeklyPlan.get(i));
                 }
             }
             List<MPS> plan4 = new ArrayList<MPS>(plan.getMps());
             for(int k=0; k< weeklyPlan.size(); k++) {
                 plan4.add(weeklyPlan.get(k));
             }
             plan.setMps(plan4);
             mrp2.setMps(weeklyPlan);
             em.persist(plan);
             em.persist(mrp2);
             return weeklyPlan;
             }
            
    }

    @Override
    public List<MPS> viewMRP(String time, String furnitureName, String partName) {
        Query q2=em.createQuery("SELECT b FROM MPS b WHERE b.plan.furniture.furnitureName=:bName AND b.partName=:bName2 AND b.plan.time=:bName3");
        q2.setParameter("bName",furnitureName);
        q2.setParameter("bName2",partName);
        q2.setParameter("bName3",time);
        if(q2.getResultList().isEmpty())
            return null;
        else
            return q2.getResultList();
    }
    
    @Override
    public List<MPS> generateMRP(String time,String furnitureName,String partName) {
        int quantity;
        int grossRequir;
        int plannedOrder;
        int leadTime;
        int scheduledReceipts;
        String time1 = new String();
        List<MPS> weeklyPlan =generateMPS(time,furnitureName,partName);
        Query q2=em.createQuery("SELECT b FROM BOM b WHERE b.furniture.furnitureName=:bName AND b.part.partName=:bName2");
        q2.setParameter("bName",furnitureName);
        q2.setParameter("bName2",partName);
        if(q2.getResultList().isEmpty()) {
                System.out.println("no bom");
                return null;
        }
        else
        {
            BOM bom = (BOM) q2.getResultList().get(0);
            quantity = bom.getQuantity();
        
            for(int i=0; i<weeklyPlan.size() ; i++) {
            MPS plan1 = weeklyPlan.get(i);
            
            grossRequir = plan1.getMPSweeklyDemand()*quantity;
            plan1.setGrossRequirment(grossRequir);
            
            plan1.setPartName(partName);
            
            leadTime = bom.getPart().getLeadTime();
            if(i>=leadTime) {
                scheduledReceipts = weeklyPlan.get(i-leadTime).getPlannedOrder();
                plan1.setScheduledReceipts(scheduledReceipts);
                leadTime = 0;
            }
            else {
                leadTime = leadTime - i;
                time1 = plan1.getPlan().getTime();
            }
            while(leadTime!=0) {
                    
                   
                    String timeSplit[] = time1.split("-");
                    int month = Integer.parseInt(timeSplit[0]);
                    int year = Integer.parseInt(timeSplit[1]);
                    if(month==1) {
                        month = 12;
                        year = year - 1;
                    }
                    else
                        month = month - 1;
                    time1 = String.valueOf(month) + "-" + String.valueOf(year);
                    Query q3=em.createQuery("SELECT p FROM SalesPlan p WHERE p.furniture.furnitureName=:pName AND p.time=:pTime");
                    q3.setParameter("pName",furnitureName);
                    q3.setParameter("pTime",time1);
                    if(q3.getResultList().isEmpty()) {
                            System.out.println("no salesplan");
                            return null;
                    }
                    else {
                            System.out.println("has salesplan");
                            SalesPlan plan2 = (SalesPlan) q3.getResultList().get(0);
                            if(plan2.getMps().size()>=leadTime) {
                                List<MPS> weeklyPlan2 =new ArrayList<>();
                                for(int k=0; k<plan2.getMps().size(); k++) {
                                           for(Object o:plan2.getMps()) {
                                                    MPS plan3 = (MPS) o;
                                                    if(plan3.getWeek()==(k+1)) {
                                                    weeklyPlan2.add(plan3);
                                                    break;
                                                    }
                                            }
                                }
                                scheduledReceipts = weeklyPlan2.get(plan2.getMps().size()-leadTime).getPlannedOrder();
                                System.out.println(scheduledReceipts);
                                plan1.setScheduledReceipts(scheduledReceipts);
                                leadTime = 0;
                            }
                            else {
                                leadTime = leadTime - plan2.getMps().size();
                                time1 = plan2.getTime();
                            }
                            
                    
                         }
            }
                    
 
            if(i==0)
                plan1.setOnHandAfter(bom.getPart().getInventory().getQuantity()+plan1.getScheduledReceipts()-grossRequir);
            else
                plan1.setOnHandAfter(weeklyPlan.get(i-1).getOnHandAfter()+plan1.getScheduledReceipts()-grossRequir);
            if(!(plan1.getPlannedOrder()!=0)) {
            if(((grossRequir/bom.getPart().getLotSize())*bom.getPart().getLotSize())==grossRequir)
                plan1.setPlannedOrder(grossRequir);
            else
                plan1.setPlannedOrder((grossRequir/bom.getPart().getLotSize()+1) * bom.getPart().getLotSize());
            }   
          }
        }
        return weeklyPlan;
        
    }
    
    
    @Override
    public void updateMPS(int plannedOrder,long mpsID) {
        mps = em.find(MPS.class,mpsID);
        mps.setPlannedOrder(plannedOrder);
        em.flush();
        em.persist(mps);
    }
    
    @Override
    public List<String> getFurnitures(){
        List<String> furnitures = new ArrayList<String>();
        Furniture furniture = new Furniture();
        Query q3=em.createQuery("SELECT p FROM Furniture p");
        for(int i=0; i<q3.getResultList().size(); i++) {
            furniture = (Furniture) q3.getResultList().get(i);
            furnitures.add(furniture.getFurnitureName());
        }
        return furnitures;
    }
    
    @Override
    public List<String> getParts(String furnitureName) {
        List<String> parts = new ArrayList<String>();
        Part part = new Part();
        Furniture furniture = new Furniture();
        Query q3=em.createQuery("SELECT p FROM Furniture p WHERE p.furnitureName=:pName");
        q3.setParameter("pName",furnitureName);
        furniture = (Furniture) q3.getResultList().get(0);
        Collection<BOM> bom = furniture.getBom();
        for(Object o:bom) {
                BOM bom1 = (BOM) o;
                part = bom1.getPart();
                parts.add(part.getPartName());
            }
        return parts;
        
    }

}
