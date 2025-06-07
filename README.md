# 📘 Spring Data JPA


## 📖 Introduction to Spring Data JPA

Spring Data JPA is a high-level abstraction built on top of JPA (Java Persistence API), designed to reduce boilerplate code and streamline interaction with relational databases. It automatically implements repository interfaces and supports powerful query generation through method naming conventions.

### Key Benefits:

* ✅ Rapid development with minimal boilerplate
* ✅ Easy integration with Spring Boot
* ✅ Support for pagination, sorting, and custom queries
* ✅ Improved testability

---

## 📜 Entity Mapping

Entity classes represent your database tables. Spring uses annotations to define how these classes map to the database schema.

### Common Annotations:

* `@Entity`: Marks a class as a JPA entity.
* `@Table(name = "table_name")`: Custom table name.
* `@Id` + `@GeneratedValue`: Primary key generation.
* Relationship mappings:

  * `@OneToOne`, `@OneToMany`, `@ManyToOne`, `@ManyToMany`
  * `@JoinColumn`, `@JoinTable`, `mappedBy`, `cascade`, and `fetch`

### Example:

```java
@Entity
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstName;
  private String lastName;

  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
  private List<Order> orders;
}
```

---

## 📚 Repositories

Spring Data JPA provides built-in repository interfaces to simplify CRUD operations.

### Types:

* `CrudRepository<T, ID>`
* `JpaRepository<T, ID>`
* `PagingAndSortingRepository<T, ID>`

### Example:

```java
public interface CustomerRepository extends JpaRepository<Customer, Long> {
  List<Customer> findByLastName(String lastName);
  boolean existsByEmail(String email);
}
```

Spring automatically implements this interface at runtime.

---

## 🔍 Custom Queries

When method naming isn't enough, you can define custom queries using JPQL or native SQL.

### JPQL:

```java
@Query("SELECT c FROM Customer c WHERE c.age > :age")
List<Customer> findOlderThan(@Param("age") int age);
```

### Native SQL:

```java
@Query(value = "SELECT * FROM customer WHERE status = :status", nativeQuery = true)
List<Customer> findByStatus(@Param("status") String status);
```

---

## 📄 Pagination and Sorting

Manage large datasets efficiently using `Pageable` and `Sort`.

### Example:

```java
Page<Customer> page = customerRepo.findByLastName(
    "Smith",
    PageRequest.of(0, 10, Sort.by("firstName"))
);
```

* `Pageable`: page number, size, and sort.
* `Page<T>`: result set with metadata.
* `Slice<T>`: lightweight pagination without total count.

---

## 🔄 Transaction Management

Ensure data consistency with Spring’s `@Transactional` annotation.

### Example:

```java
@Service
public class OrderService {
  @Transactional
  public Order placeOrder(Customer customer, List<Item> items) {
    // logic to create and save order
    return orderRepository.save(order);
  }
}
```

* Rollbacks on runtime exceptions by default.
* Supports propagation and isolation levels.

---

## 🚀 Best Practices

✅ Use DTOs or Projections in service/controller layers.
✅ Favor lazy loading with selective fetch joins.
✅ Add indexes to frequently queried fields.
✅ Avoid returning JPA entities directly in APIs.
✅ Use `@EntityGraph` or `JOIN FETCH` to solve N+1 query issues.
✅ Enable second-level and query caching where appropriate.
✅ Use connection pooling (HikariCP is default in Spring Boot).
