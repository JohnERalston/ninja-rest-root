# ninja-rest-root
A java ninjaframework simple REST template project

## Run
mvn ninja:run

## About
A simple REST project that allows listing/creating/editing/deleting Blog objects.

There is no database, persistence is in memory and lost as soon as the application ends.


## End Points
http://localhost:8080

/listblogs (GET)   
/viewblog/{id} (GET)
//createblog (POST Blog.java)  
/editblog  (POST Blog.java)  
/deleteblog (POST Blog.java) 


## Blog.java
    int id;
    String title = "";
    String content = "";

