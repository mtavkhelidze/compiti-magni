## ToDo with Scala 3, ZIO, and Cardano Blockchain

### API

| Method | Path        | Description              |
|--------|-------------|--------------------------|
| GET    | /uuid       | Get UUID Id              |
| GET    | /cardano    | Get Cardano generated Id |

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
