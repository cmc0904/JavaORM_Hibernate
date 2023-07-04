package cmc.project.annotations;


import java.io.IOException;
import java.util.List;

import org.hibernate.*;


public class Query1 {

    private static SessionFactory sessionFactory = HibernateAnnotationUtil.getSessionFactory();
    private static WeatherInformation weatherInformation = new WeatherInformation();

    public boolean insertWeatherInformations() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        boolean bool = false;
        try {
            tx = session.beginTransaction();
            for(Weather weather : weatherInformation.getWeatherInformation()) {
                try {
                    session.save(weather);
                } catch (NonUniqueObjectException n ) {

                }
            }
            tx.commit();
            bool = true;
        } catch (HibernateException e) {
            if (tx!=null) {
                tx.rollback();
            }
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            session.close();
            return bool;
        }


//        Session session = sessionFactory.openSession();
//        session.beginTransaction();



//        return true;
    }

    public List<Weather> getAllWeathers() {
        Session session = sessionFactory.openSession();
        List<Weather> weathers = null;
        try {
            weathers = session.createQuery("FROM Weather", Weather.class).list();
            return weathers;
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
            return weathers;
        }
    }

    public List<Weather> getWeatherByCity(String city) {
        Session session = sessionFactory.openSession();
        List<Weather> weathers = null;
        try {
            weathers = session.createQuery(String.format("FROM Weather WHERE city = '%s'", city), Weather.class).list();
            return weathers;
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
            return weathers;
        }
    }

    public static void main( String[] args ) {
        Query1 query1 = new Query1();

        boolean b = query1.insertWeatherInformations();

        if(b) {
            System.out.println("정상적으로 데이터가 데이터 베이스에 추가 되었습니다.");
        }

        for(Weather weather : query1.getAllWeathers()) {
            System.out.println(String.format("지역명 : %s | 날짜 : %s | 날씨 : %s | 최저 온도  : %s | 최고 온도 : %s", weather.getCity(), weather.getDt(), weather.getWf(), weather.getTmn(), weather.getTmx()));
        }

        for(Weather weather : query1.getWeatherByCity("광주")) {
            System.out.println(String.format("지역명 : %s | 날짜 : %s | 날씨 : %s | 최저 온도  : %s | 최고 온도 : %s", weather.getCity(), weather.getDt(), weather.getWf(), weather.getTmn(), weather.getTmx()));
        }
    }
}