## ToDo with Scala 3, ZIO, and Cardano Blockchain

### API

| Method | Path        | Description    |
|--------|-------------|----------------|
| GET    | /tasks      | Get all tasks  |
| GET    | /tasks/{id} | Get task by id |
| POST   | /tasks      | Create task    |
| PUT    | /tasks/{id} | Update task    |
| DELETE | /tasks/{id} | Delete task    |

### Internal Error Handling Example

```mermaid
classDiagram
    class DomainError {
        <<trait>>
        +String module
        +String error
    }

    class WithCause {
        <<trait>>
        +Option~Throwable~ cause
    }

    class UuidError {
<<sealed trait>>
+Option~Throwable~ cause()
+override String module = "UuidError"
}

class CannotCreate {
<<case object>>
+override String module = "UuidError"
+override String error = "CannotCreate"
+override Option~Throwable~ cause() = None
}

class CannotParse {
<<case class>>
+override String module = "UuidError"
+override String error = "CannotParse"
+override Option~Throwable~ cause
}

DomainError <|-- UuidError
WithCause <|.. UuidError
UuidError <|-- CannotCreate
UuidError <|-- CannotParse

```
