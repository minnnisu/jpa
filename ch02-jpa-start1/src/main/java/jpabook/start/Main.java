package jpabook.start;

import javax.persistence.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); // 엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            logic(em);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();

        }

        emf.close();
    }

    private static void logic(EntityManager em) {
        Member member = new Member();
        member.setUsername("민수2");
        member.setAge(2);
        member.setRoleType(RoleType.ADMIN);

        // 등록
        em.persist(member);
        // 수정
        member.setAge(25); // JPA는  어떤 엔티티가 변경되었는지 추적하는 기능을 갖추고 있기 때문에 자동으로 업데이터 됨

        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("members.size=" + members.size());

//        em.remove(member);

    }
}