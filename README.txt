Spring Boot – Transaction Management

Dependencies:
JPA
SQL driver

@Transactional annotation is the metadata used for managing transactions in the Spring Boot application. 
To configure Spring Transaction, this annotation can be applied at the class level or method level. In an enterprise application,
a transaction is a sequence of actions performed by the application that together pipelined to perform a single operation. 
For example, booking a flight ticket is also a transaction where the end user has to enter his information and 
then make a payment to book the ticket.

Why Do We Need Transaction Management?
Let’s understand transactions with the above example, if a user has entered his information the user’s information gets stored
 in the user_info table. Now, to book a ticket he makes an online payment and due to some reason(system failure) the payment has
  been canceled so, the ticket is not booked for him. But, the problem is that his information gets stored on the user_info 
  table. On a large scale, more than thousands of these things happen within a single day. So, it is not good practice to 
  store a single action of the transaction(Here, only user info is stored not the payment info).

To overcome these problems, spring provides transaction management, which uses annotation to handle these issues. 
In such a scenario, spring stores the user information in temporary memory and then checks for payment information if the 
payment is successful then it will complete the transaction otherwise it will roll back the transaction and the user information
 will not get stored in the database.

@Transactional Annotation
In Spring Boot, @Transactional annotation is used to manage transactions in a Spring boot application and used to define a 
scope of transaction. This annotation can be applied to the class level or method level. It provides data reliability and
 consistency. When a method is indicated with @Transactional annotation, it indicates that the particular method should be 
 executed within the context of that transaction. If the transaction becomes successful then the changes made to the 
 database are committed, if any transaction fails, all the changes made to that particular transaction can be rollback
  and it will ensure that the database remains in a consistent state.

@EnableTransactionManagement
Note: To use @Transactional annotation, you need to configure transaction management by using @EnableTransactionManagement 
to your main class of Spring Boot application. 


Configure Transaction in Spring Boot
In this example, we will create an application to store user information along with his address information and will use spring
 transaction management to resolve the transaction break problem.

Step By Step Implementation of Transaction Management


Step 3: Configure Database
Now, we will configure the database in our application. 
We will be using the following configurations and add them to our application.properties file.

server.port = 9090
#database configuration
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL57Dialect
#the ddl-auto=update : It will create the entity schema and map it to db automatically
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
Note: Please add your database username & password along with the database path.

Step 4: Create Model Class
In this step, we will create our model class. Here, we will be creating two model classes, 
Employee and 
Address.

While creating the model class we will be using Lombok Library.

Step 5: Create a Database Layer
In this step, we will create a database layer. For this, we will be creating EmployeeRepository and AddressRepository 
and will be extending JpaRepository<T, ID> for performing database-related queries.

EmployeeRepository.java

Step 6: Create a Service Layer
You can use @Transactional annotation in service layer which will result interacting with the database. In this step, 
we will create a service layer for our application and add business logic to it. For this, we will be creating two classes
 EmployeeService and AddressService. In EmployeeService class we are throwing an exception.

EmployeeService.java

Step 7: Create Controller
In this step, we will create a controller for our application. For this, we will create a Controller class and
 add all the mappings to it.

Controller.java

Step 8: Running Our Application
In this step, we will run our application. Once,
 we run our application using hibernate mapping in our database required tables will be created.

 add_info
 emp_info