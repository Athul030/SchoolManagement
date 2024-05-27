# School Management System (Back end in Spring Boot)
This is a RESTful API for managing students in a school . 
It provides endpoints for adding, retrieving, and updating student information.

### Date Format
Date fields (dateOfBirth and joiningDate) are expected in the format: YYYY-MM-DD.

## Endpoints

1. Add Student: POST /student  (Add a new student to the database)
Request body should be a JSON object.
Example request body:
##
    {
    "name": "John Doe",
    "dateOfBirth": "2000-05-15",
    "joiningDate": "2022-09-01",
    "className": "Class  10"
    }

2. Search Students: GET /search/{searchTerm}  (Retrieve student details based on ID, name or class)
If the search term is numeric, it's considered as an ID. Otherwise, it's considered as a name or class.
Example usage:
##
GET /search/123 - Search by ID.
##
GET /search/John - Search by name.
##
GET /search/Grade%2010 - Search by class.

3. Update Student: PUT /student/{id} (Update details of an existing student)
Path variable {id} represents the ID of the student to be updated.
##
Example request body:
##
{
"name": "Jane Doe",
"joiningDate": "2022-08-01"
}

### Error Handling:
Global exception handling is implemented to return appropriate error responses in case of resource not found from DB.