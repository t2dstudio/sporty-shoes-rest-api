Github URL:
https://github.com/sonuahluwalia/sporty-shoes.git

Requirements:
1. Admin should be able to login/logout - done
2. Admin should be able to change password - done
3. Admin can add/delete/modify products - done
4. Admin can change product categories -done
5. Admin can view list of all users - done
6. Admin can search users by name - done
7. Admin can view purchase reports - done
8. Admin can filter the purchase reports by date and category - done
9. Test cases of all the features 


Technologies Used:
Spring Boot
MySQL
Lombok
Spring Security
Spring MVC
Thymeleaf
Spring JPA (Hibernate implementation)
Spring REST
Swagger 2
ModelMapper
Apache Commons Validator
Javax Validator API

Login:
Username: admin
Password: admin (store as encrypted in mysql db)
You will see the index.html page where the admin can logout or change password and there is a link for the Swagger UI


Swagger UI
http://localhost:8080/swagger-ui.html


Product API:
add
delete
read(using pagination by giving page number & size)
update

Validations on Product:
Product name cannot be empty during adding
Product id is not null or empty during updation
Product id exists during updation
Get Products by providing valid size and page number
Update valid category


User API:
read(using pagination by giving page number & size)
findByName

Validation on User:
If user is not there, user not found message is given

Purchase API:
read(using pagination by giving page number & size)
findByDate
findByCategory


Some test url:

curl -u admin:admin -X GET "http://localhost:8080/purchase/getPurchaseReportByCreatedDate?createdAt=2020-07-29&page=1&size=1" -H "accept: */*"


curl -u admin:admin -X GET "http://localhost:8080/users/list?page=1&size=2" -H "accept: */*"


curl -u admin:admin -X GET "http://localhost:8080/product/getProducts?page=1&size=2" -H "accept: */*"

curl -u admin:admin -X GET "http://localhost:8080/purchase/getPurchases?page=1&size=2" -H "accept: */*"

curl  -u admin:admin -X GET "http://localhost:8080/purchase/getPurchaseReportByCategory?category=MISC&page=1&size=1" -H "accept: */*"

curl  -u admin:admin -X GET "http://localhost:8080/purchase/getPurchaseReportByCategory?category=MMISC&page=1&size=1" -H "accept: */*"

curl -u admin:admin -X POST "http://localhost:8080/product/add" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"category\": \"sfds\", \"cost\": 0, \"description\": \"string\", \"manufacturerName\": \"string\", \"model\": \"string\", \"productname\": \"string\"}"

curl -u admin:admin -X POST "http://localhost:8080/product/add" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"category\": \"MISC\", \"cost\": 0, \"description\": \"string\", \"manufacturerName\": \"string\", \"model\": \"string\", \"productname\": \"string\"}"