# RealEstateBazaar
My first spring boot  project with thymeleaf, spring security and data jpa
# Online Real Estate Management System Project
- The project is created with Spring Boot and Thymeleaf, utilizing Spring Security. 
- The project employs the MVC pattern with authentication. 
- Additionally, role-based menu navigation is implemented for authorization.
- Data persistence is achieved using data JPA.
# Requirements
- Real estate description service will be provided for the predefined address regions in the system.
- Members of the system will be able to define their properties they want to rent or sell by selecting the address regions.
- The real estate description will be defined with information such as floor details, number of rooms, facade, etc.
- The system has a two-tier membership.
- Authorized members can add real estate descriptions to the system.
- Regular members can only query the properties.
- Authorization for the privileged membership is subject to approval by the administrator after an online application.
- Lower-level administrators defined in the system will have managerial rights in specific regions. However, a lower-level administrator cannot define another administrator."
# Roles and their rights
- User: search_estate, role_request.
- Authorized User: search_estate, add_estate.
- Local Admin: search_estate, add_estate, delete_estate, search_role_request, update_role (only for authorized user).
- Admin: same as local admin except update_role includes local admin.
