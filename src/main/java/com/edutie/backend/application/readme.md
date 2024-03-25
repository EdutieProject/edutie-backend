# Application layer 

Application layer is a place for orchestrating the use cases using the _API_ provided by the domain. Services
specified in this layer are exposed to the user by the presentation infrastructure. 

## Structure:
- learning/ `Services exposed for students for learning use cases, most of which are read operations.`
    - course/
    - lesson/
    - lessonSegment/
    - resource/
 - creation/ `Services exposed for the educators responsible for study program management`
    - course/
    - lesson/
    - lessonSegment/
- profiles/ `Services responsible for roles (profiles) management`
    - educator/
    - student/
    - admin/ `Note: not implemented yet`