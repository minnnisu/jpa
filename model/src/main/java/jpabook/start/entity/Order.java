package jpabook.start.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void setMember(Member member) {
        //기존 관계 제거
        if (this.member != null) {
            this.member.getOrders().remove(this);
        }

        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public Long getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public List<OrderItem> getItems() {
        return orderItems;
    }
    public void setItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
